package spring.approval.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import spring.approval.domain.Approver;
import spring.approval.dto.ApproverResponseDto;

@Slf4j
@Repository
public class ApproverRepository {
    private final JdbcTemplate jdbcTemplate;

    public ApproverRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveApprovers(List<Approver> approvers) {
        String sql = "INSERT INTO approver (docId,memberId,memberName,dept,position,approvalType,approvalOrder,bActiveApprover,status) "
                + "VALUES (?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Approver approver = approvers.get(i);
                ps.setString(1, approver.getDocId());
                ps.setLong(2, approver.getMemberId());
                ps.setString(3, approver.getMemberName());
                ps.setString(4, approver.getDept());
                ps.setString(5, approver.getPosition());
                ps.setString(6, approver.getApprovalType());
                ps.setInt(7, approver.getApprovalOrder());
                ps.setBoolean(8, approver.getBActiveApprover());
                ps.setString(9, approver.getStatus());

            }

            @Override
            public int getBatchSize() {
                return approvers.size();
            }

        });
    }

    /**
     * getApprovers 조회
     * @param docId
     * @return
     */
    public List<ApproverResponseDto> getApprovers(String docId) {
        log.info("[ApproverRepository_getApprovers()] approver.getDicId={} ", docId);
        try {
            return jdbcTemplate.query("select * from approver where docId = ?", approverRowMapper(), docId);
        } catch (Exception e) {
            log.error("❗ Approver 조회 중 오류 발생", e);
            throw e;
        }
    }

    public void setCurrentApproverInactive(String docId, Long memberId) {
        String sql = "UPDATE Approver SET bActiveApprover = false, status = '완료' WHERE docId = ? AND memberId = ?";
        try {
            jdbcTemplate.update(sql, docId, memberId);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void setNextApproverActive(String docId, int approvalOrder) {
        String sql = "UPDATE Approver SET bActiveApprover = true  WHERE docId = ? AND approvalOrder = ?";
        try {
            jdbcTemplate.update(sql, docId, approvalOrder);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public boolean isApprovalGroupComplete(String docId, int approvalOrder) {
        String sql = "SELECT COUNT(*) FROM Approver WHERE docId =? AND approvalOrder=? AND status='대기'";
        try {
            Integer remaining = jdbcTemplate.queryForObject(sql, Integer.class, docId, approvalOrder);
            return remaining == 0;

        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }


    private RowMapper<ApproverResponseDto> approverRowMapper(){
        log.info("[ApproverRepository_approverRowMapper()] 접근");
        return (rs, rowNum) -> {
            ApproverResponseDto approver = new ApproverResponseDto();
            approver.setDocId(rs.getString("docId"));
            approver.setMemberId(rs.getLong("memberId"));
            approver.setMemberName(rs.getString("memberName"));
            approver.setDept(rs.getString("dept"));
            approver.setPosition(rs.getString("position"));
            approver.setApprovalType(rs.getString("approvalType"));
            approver.setApprovalOrder(rs.getInt("approvalOrder"));
            approver.setBActiveApprover(rs.getBoolean("bActiveApprover"));
            approver.setStatus(rs.getString("status"));

            log.info("[ApproverRepository_approverRowMapper()] approver.getDocId={} ", approver.getDocId());
            return approver;
        };

    }
}

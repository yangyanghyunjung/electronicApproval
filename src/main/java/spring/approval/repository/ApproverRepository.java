package spring.approval.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import spring.approval.domain.Approver;
import spring.approval.dto.ApproverResponseDto;

@Slf4j
public class ApproverRepository {
    private final JdbcTemplate jdbcTemplate;

    public ApproverRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveApprovers(List<Approver> approvers) {
        String sql = "INSERT INTO approver (docId,memberId,memberName,dept,position,approvalType,approvalOrder,bCurrentApprover,status) "
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
                ps.setBoolean(8, approver.getBCurrentApprover());
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

    public void setBCurrentApproverFalse(String docId, Long memberId) {
        String sql = "UPDATE Approver SET bCurrentApprover = fasle WHERE docId = ? AND memberId = ?";
        jdbcTemplate.update(sql,docId, memberId);
    }

    public void setBCurrentApproverTrue(String docId, Long memberId) {
        String sql = "UPDATE Approver SET bCurrentApprover = true WHERE docId = ? AND memberId = ?";
        jdbcTemplate.update(sql,docId, memberId);
    }

    public Optional<Long> findNextApprover(String docId) {
        String sql = "SELECT * FROM member WHERE docId = ? AND bCurrentApprover = false AND approvalType = '대기' ORER BY approvalOrder LIMIT 1";
        return jdbcTemplate.query(sql, new Object[]{docId}, (rs, rowNum) -> rs.getLong("docId")).stream().findAny();
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
            approver.setBCurrentApprover(rs.getBoolean("bCurrentApprover"));
            approver.setStatus(rs.getString("status"));

            log.info("[ApproverRepository_approverRowMapper()] approver.getDocId={} ", approver.getDocId());
            return approver;
        };

    }
}

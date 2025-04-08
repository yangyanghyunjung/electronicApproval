package spring.approval.repository.documents;

import java.util.Optional;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;
import spring.approval.domain.ExpenseReport;
import spring.approval.dto.documents.ExpenseReportRequestDto;

@Slf4j
public class ExpenseReportRespository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExpenseReportRespository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 지출결의서 조회
     * @param docId
     * @param FOID
     * @return
     */
    public ExpenseReport getDocument(String docId, String FOID) {
        log.info("[Access-ExpenseReportRepository_getDocument()] docId={}, FOID={}", docId, FOID);
        try {
            ExpenseReport expenseReport = jdbcTemplate.queryForObject("select * from ExpenseReport where docId=?", DocumentMapper(), docId);
            return expenseReport;

        } catch (Exception e) {
            log.error("❗ expenseReport 조회 중 오류 발생", e);
            throw e;
        }
    }

    /**
     * 지출결의서  저장 (기안)
     * @param expenseReport
     * @return
     */
    public ResponseEntity<String> saveDocument(ExpenseReportRequestDto expenseReport) {
        log.info("[Access-ExpenseReportResponseDto_saveDocument()] 진입");
        String sql = "INSERT INTO ExpenseReport (docId,FOID, dept,writer,position,title,create_dt,sdate,edate,"
                + "totalAmount,txtRem,docStatus,requester,expenseDetails,approvalFlow) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            int rowsAffected = jdbcTemplate.update(sql,
                    expenseReport.getDocId(),
                    expenseReport.getFOID(),
                    expenseReport.getDept(),
                    expenseReport.getWriter(),
                    expenseReport.getPosition(),
                    expenseReport.getTitle(),
                    expenseReport.getCreate_dt(),
                    expenseReport.getSdate(),
                    expenseReport.getEdate(),
                    expenseReport.getTotalAmount(),
                    expenseReport.getTxtRem(),
                    expenseReport.getDocStatus(),
                    expenseReport.getRequester(),
                    expenseReport.getExpenseDetails(),
                    expenseReport.getApprovalFlow()
            );
            if (rowsAffected > 0)
            {
                return ResponseEntity.ok("문서 저장 완료");
            } else {
                return ResponseEntity.ok("문서 저장 실패");
            }

        } catch (Exception e) {
            log.error("❗ ExpenseReportResponseDto_saveDocument 조회 중 오류 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<String> updateDocStatus(String docId, String docStatus) {
        String sql = "UPDATE ExpenseReport SET docStatus = ? WHERE docId = ?";
        try {
            jdbcTemplate.update(sql, docStatus , docId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok("문서 업데이트 성공");
    }

    private RowMapper<ExpenseReport> DocumentMapper() {
        return (rs, rowNum) -> {
            ExpenseReport expenseReport = new ExpenseReport();
            expenseReport.setDocId(rs.getString("docId"));
            expenseReport.setFOID(rs.getString("FOID"));
            expenseReport.setTitle(rs.getString("dept"));
            expenseReport.setWriter(rs.getString("writer"));
            expenseReport.setDept(rs.getString("dept"));
            expenseReport.setTitle(rs.getString("title"));
            expenseReport.setPosition(rs.getString("position"));
            expenseReport.setCreate_dt(rs.getDate("create_dt"));
            expenseReport.setSdate(rs.getDate("sdate"));
            expenseReport.setEdate(rs.getDate("edate"));
            expenseReport.setExpenseDetails(rs.getString("expenseDetails"));
            expenseReport.setTotalAmount(rs.getString("totalAmount"));
            expenseReport.setTxtRem(rs.getString("txtRem"));
            expenseReport.setDocStatus(rs.getString("docStatus"));
            expenseReport.setRequester(rs.getString("requester"));
            expenseReport.setApprovalFlow(rs.getString("approvalFlow"));

            return expenseReport;
        };
    }

}

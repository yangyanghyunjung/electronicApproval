package spring.approval.repository.documents;

import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.approval.domain.ExpenseDoc;
import spring.approval.dto.documents.ExpenseReportRequestDto;

@Slf4j
@Repository
public class ExpenseReportRespository {
    private final JdbcTemplate jdbcTemplate;


    public ExpenseReportRespository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 지출결의서 조회
     * @param docId
     * @return
     */
    public ExpenseDoc getDocument(String docId) {
        log.info("[Access-ExpenseReportRepository_getDocument()] docId={}, FOID={}", docId);
        try {
            ExpenseDoc expenseDoc = jdbcTemplate.queryForObject("select * from ExpenseReport where docId=?", DocumentMapper(), docId);
            return expenseDoc;

        } catch (Exception e) {
            log.error("❗ expenseReport 조회 중 오류 발생", e);
            throw e;
        }
    }

    /**
     * 지출결의서  저장 (기안)
     * @param expenseDoc
     * @return
     */
    public ResponseEntity<String> draftDoc(ExpenseDoc expenseDoc) {
        log.info("[Access-ExpenseReportResponseDto_saveDocument()] 진입");
        String sql = "INSERT INTO ExpenseReport (docId,FOID, dept,writer,position,title,create_dt,sdate,edate,"
                + "totalAmount,txtRem,docStatus,requester,expenseDetails,approvalFlow) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            int rowsAffected = jdbcTemplate.update(sql,
                    expenseDoc.getDocId(),
                    expenseDoc.getFOID(),
                    expenseDoc.getDept(),
                    expenseDoc.getWriter(),
                    expenseDoc.getPosition(),
                    expenseDoc.getTitle(),
                    expenseDoc.getCreate_dt(),
                    expenseDoc.getSdate(),
                    expenseDoc.getEdate(),
                    expenseDoc.getTotalAmount(),
                    expenseDoc.getTxtRem(),
                    expenseDoc.getDocStatus(),
                    expenseDoc.getRequester(),
                    expenseDoc.getExpenseDetails(),
                    expenseDoc.getApprovalFlow()
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

    private RowMapper<ExpenseDoc> DocumentMapper() {
        return (rs, rowNum) -> {
            ExpenseDoc expenseDoc = new ExpenseDoc();
            expenseDoc.setDocId(rs.getString("docId"));
            expenseDoc.setFOID(rs.getString("FOID"));
            expenseDoc.setTitle(rs.getString("dept"));
            expenseDoc.setWriter(rs.getString("writer"));
            expenseDoc.setDept(rs.getString("dept"));
            expenseDoc.setTitle(rs.getString("title"));
            expenseDoc.setPosition(rs.getString("position"));
            expenseDoc.setCreate_dt(rs.getDate("create_dt"));
            expenseDoc.setSdate(rs.getDate("sdate"));
            expenseDoc.setEdate(rs.getDate("edate"));
            expenseDoc.setExpenseDetails(rs.getString("expenseDetails"));
            expenseDoc.setTotalAmount(rs.getString("totalAmount"));
            expenseDoc.setTxtRem(rs.getString("txtRem"));
            expenseDoc.setDocStatus(rs.getString("docStatus"));
            expenseDoc.setRequester(rs.getString("requester"));
            expenseDoc.setApprovalFlow(rs.getString("approvalFlow"));

            return expenseDoc;
        };
    }

}

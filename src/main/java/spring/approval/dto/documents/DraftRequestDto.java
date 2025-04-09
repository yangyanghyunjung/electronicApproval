package spring.approval.dto.documents;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import spring.approval.domain.Approver;
import spring.approval.domain.ExpenseDetails;
import spring.approval.domain.ExpenseDoc;
import spring.approval.util.DocumentIdGenerator;

@Data
public class DraftRequestDto {
    // 모든 문서 공통 필드
    private String docId;
    private String FOID;
    private String dept;
    private String writer;
    private String title;
    private String position;
    private Date create_dt;

    private String docStatus;
    private String requester;
    private String approvalFlow; // 굳이?

    private Map<String, Object> content; // 문서 고유 필드들
    private List<Approver> approvers; // 결재선


    private ExpenseDoc toExpenseDoc(DraftRequestDto draftRequestDto) {
        ExpenseDoc expenseDoc = new ExpenseDoc();
        expenseDoc.setDocId(DocumentIdGenerator.generateDocId(draftRequestDto.getFOID()));
        expenseDoc.setFOID(draftRequestDto.getFOID());
        expenseDoc.setDept(draftRequestDto.getDept());
        expenseDoc.setWriter(draftRequestDto.getWriter());
        expenseDoc.setTitle(draftRequestDto.getTitle());
        expenseDoc.setPosition(draftRequestDto.getPosition());
        expenseDoc.setCreate_dt(draftRequestDto.getCreate_dt());
        expenseDoc.setDocStatus(draftRequestDto.getDocStatus());
        expenseDoc.setRequester(draftRequestDto.getRequester());
        expenseDoc.setApprovalFlow(draftRequestDto.getApprovalFlow());

//        expenseDoc.setContent(draftRequestDto.getContent()); // 여기는 어쩌지?
        expenseDoc.setApprovers(draftRequestDto.getApprovers());

        // 👇 content의 값 꺼내서 매핑
        if (content != null) {
            Object reasonObj = content.get("txtRem");
            if (reasonObj instanceof String txtRem) {
                expenseDoc.setTxtRem(txtRem);
            }
            Object totalAmountObj = content.get("totalAmount");
            if (totalAmountObj instanceof String totalAmount) {
                expenseDoc.setTotalAmount(totalAmount);
            }
            Object sDateObj = content.get("sdate");
            if (sDateObj instanceof Date sdate) {
                expenseDoc.setSdate(sdate);
            }
            Object eDateObj = content.get("edate");
            if (eDateObj instanceof Date edate) {
                expenseDoc.setSdate(edate);
            }

        }

        return expenseDoc;
    }
}

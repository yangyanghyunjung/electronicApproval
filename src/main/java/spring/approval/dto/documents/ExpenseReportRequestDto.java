package spring.approval.dto.documents;

import java.util.Date;
import java.util.List;
import lombok.Data;
import spring.approval.domain.Approver;

@Data
public class ExpenseReportRequestDto {
    private String docId;
    private String FOID;
    private String dept;
    private String writer;
    private String title;
    private String position;
    private Date create_dt;
    private Date sdate;
    private Date edate;
    private String expenseDetails;
    private String totalAmount;
    private String txtRem;
    private String docStatus;
    private String requester;
    private String approvalFlow;

    private List<Approver> approvers;

    public ExpenseReportRequestDto() {
    }
}

package spring.approval.domain.documents;

import java.util.Date;
import java.util.List;
import lombok.Data;
import spring.approval.domain.Approver;

@Data
public class ExpenseDoc {
    private String docId;
    private String FOID;
    private String dept;
    private String writer;
    private String position;
    private String title;
    private Date create_dt;
    private String docStatus;
    private String requester;
    private String approvalFlow;

    private Date sdate;
    private Date edate;
    private String expenseDetails;
    private String totalAmount;
    private String txtRem;

    private List<Approver> approvers;
}

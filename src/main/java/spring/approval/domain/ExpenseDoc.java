package spring.approval.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.Data;

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

//    private Map<String, Object> content; // 문서 고유 필드들
    private Date sdate;
    private Date edate;
    private String expenseDetails;
    private String totalAmount;
    private String txtRem;

    private List<Approver> approvers;
}

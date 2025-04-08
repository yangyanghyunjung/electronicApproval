package spring.approval.domain;

import java.util.Date;
import lombok.Data;

@Data
public class ExpenseReport {
    private String docId;
    private String FOID;
    private String dept;
    private String writer;
    private String title;
    private String position;
    private Date create_dt;
    private Date sdate;
    private Date edate;
    private String expenseDetails; // 동적 row => JSON 으로 저장
    private String totalAmount;
    private String txtRem;
    private String docStatus;
    private String requester;
    private String approvalFlow;
}

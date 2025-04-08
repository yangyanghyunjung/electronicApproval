package spring.approval.dto.documents;

import java.util.Date;
import java.util.List;
import lombok.Data;
import spring.approval.domain.ExpenseDetails;

@Data
public class ExpenseReportResponseDto {
    private String docId;
    private String FOID;
    private String dept;
    private String writer;
    private String title;
    private String position;
    private Date create_dt;
    private Date sdate;
    private Date edate;
    private List<ExpenseDetails> expenseDetails;
    private String totalAmount;
    private String txtRem;
    private String docStatus;
    private String requester;
    private String approvalFlow;

    public ExpenseReportResponseDto() {
    }

}

package spring.approval.dto.documents;

import java.util.Date;
import java.util.List;
import spring.approval.domain.ExpenseDetails;

public class ExpenseReportResponseDto {
    private String docId;
    private String FOID;
    private String dept;
    private String writer;
    private String title;
    private String position;
    private Date create_dt;
    private Date sDate;
    private Date eDate;
    private List<ExpenseDetails> expenseDetails;
    private String totalAmount;
    private String txtRem;
    private String docStatus;
    private Long currentApproverId;
    private String currentApproverName;
    private Date request_dt;
    private String requester;



    public ExpenseReportResponseDto() {
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getFOID() {
        return FOID;
    }

    public void setFOID(String FOID) {
        this.FOID = FOID;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getCreate_dt() {
        return create_dt;
    }

    public void setCreate_dt(Date create_dt) {
        this.create_dt = create_dt;
    }

    public Date getsDate() {
        return sDate;
    }

    public void setsDate(Date sDate) {
        this.sDate = sDate;
    }

    public Date geteDate() {
        return eDate;
    }

    public void seteDate(Date eDate) {
        this.eDate = eDate;
    }

    public List<ExpenseDetails> getExpenseDetails() {
        return expenseDetails;
    }

    public void setExpenseDetails(List<ExpenseDetails> expenseDetails) {
        this.expenseDetails = expenseDetails;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTxtRem() {
        return txtRem;
    }

    public void setTxtRem(String txtRem) {
        this.txtRem = txtRem;
    }

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    public Long getCurrentApproverId() {
        return currentApproverId;
    }

    public void setCurrentApproverId(Long currentApproverId) {
        this.currentApproverId = currentApproverId;
    }

    public String getCurrentApproverName() {
        return currentApproverName;
    }

    public void setCurrentApproverName(String currentApproverName) {
        this.currentApproverName = currentApproverName;
    }

    public Date getRequest_dt() {
        return request_dt;
    }

    public void setRequest_dt(Date request_dt) {
        this.request_dt = request_dt;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }
}

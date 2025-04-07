package spring.approval.domain;

import java.util.Date;
import lombok.Data;

@Data
public class DocumentList {
    private String docId;
    private String title;
    private String FOID ;
    private String currentApprover;
    private String docStatus;
    private Date request_dt;
    private String requester;

    public DocumentList() {
    }

    public DocumentList(String docId, String title, String FOID, String currentApprover, String docStatus, Date request_dt, String requester) {
        this.docId = docId;
        this.title = title;
        this.FOID = FOID;
        this.currentApprover = currentApprover;
        this.docStatus = docStatus;
        this.request_dt = request_dt;
        this.requester = requester;
    }

    public String getDocID() {
        return docId;
    }

    public void setDocID(String docID) {
        this.docId = docID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFOID() {
        return FOID;
    }

    public void setFOID(String FOID) {
        this.FOID = FOID;
    }

    public String getCurrentApprover() {
        return currentApprover;
    }

    public void setCurrentApprover(String currentApprover) {
        this.currentApprover = currentApprover;
    }

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
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

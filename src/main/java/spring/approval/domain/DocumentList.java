package spring.approval.domain;

import java.util.Date;
import lombok.Data;

@Data
public class DocumentList {
    private String docId;
    private String title;
    private String FOID ;
    private String docStatus;
    private String requester;

    public DocumentList() {
    }

    public DocumentList(String docId, String title, String FOID, String docStatus, String requester) {
        this.docId = docId;
        this.title = title;
        this.FOID = FOID;
        this.docStatus = docStatus;
        this.requester = requester;
    }

}

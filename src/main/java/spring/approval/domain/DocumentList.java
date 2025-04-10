package spring.approval.domain;

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
}

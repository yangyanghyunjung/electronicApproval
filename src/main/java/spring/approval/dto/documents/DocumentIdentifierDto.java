package spring.approval.dto.documents;

public class DocumentIdentifierDto {
    private String docId;
    private String FOID;

    public DocumentIdentifierDto(String docId, String FOID) {
        this.docId = docId;
        this.FOID = FOID;
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
}

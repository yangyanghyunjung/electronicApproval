package spring.approval.dto.documents;

import lombok.Data;

@Data
public class DocumentIdentifierDto {
    private String docId;
    private String FOID;

    public DocumentIdentifierDto(String docId, String FOID) {
        this.docId = docId;
        this.FOID = FOID;
    }

}

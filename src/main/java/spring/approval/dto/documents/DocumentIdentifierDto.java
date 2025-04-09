package spring.approval.dto.documents;

import lombok.Data;
import spring.approval.domain.EFormType;

@Data
public class DocumentIdentifierDto {
    private String docId;
    private EFormType formType;

}

package spring.approval.dto.documents;

import lombok.Data;
import spring.approval.domain.documents.EFormType;

@Data
public class UpdateDocRequestDto {
    private EFormType formType;
    private String docId;
    private Long memberId;
    private String docStatus;
    private int approvalOrder;
    private String approvalFlow;
}

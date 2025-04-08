package spring.approval.dto.documents;

import lombok.Data;

@Data
public class UpdateDocRequestDto {
    private String docId;
    private Long memberId;
    private String docStatus;
    private int approvalOrder;
    private String approvalFlow;
}

package spring.approval.dto.documents;

import lombok.Data;

@Data
public class UpdateApprovalStateRequestDto {
    private String docId;
    private Long userId;
    private int docStatus; // requestProcess 로 변경할 수도
}

package spring.approval.dto;

import lombok.Data;

@Data
public class ApproverResponseDto {
    private String docId;
    private Long memberId;
    private String memberName;
    private String dept;
    private String position;
    private String approvalType;
    private int approvalOrder ; //group 으로 묶는거 고려 -> 병렬처리
    private Boolean bActiveApprover;
    private String status; // 대기, 완료, 반려


}

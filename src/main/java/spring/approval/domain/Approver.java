package spring.approval.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Approver {
    private String docId;
    private Long memberId;
    private String memberName;
    private String dept;
    private String position;
    private String approvalType; // 기안, 합의, 검토, 승인
    private int approvalOrder ; //group 으로 묶는거 고려 -> 병렬처리
    @JsonProperty("bCurrentApprover")
    private Boolean bCurrentApprover;
    private String status; // 대기, 완료, 반려

    public Approver() {
    }
}

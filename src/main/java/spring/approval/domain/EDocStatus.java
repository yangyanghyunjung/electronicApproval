package spring.approval.domain;

public enum EDocStatus {
    DRAFT("기안"),
    REVIEW("검토"),
    AGREE("합의"),
    APPROVAL("승인"),
    COMTLETED("완료"),
    REJECTED("반려");



    private final String displayName;

    EDocStatus(String displayName) {
        this.displayName = displayName;
    }
}

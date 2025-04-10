package spring.approval.domain.list;

public enum EListType {
    APPROVAL_LIST("APPROVAL_LIST"),
    PROGRESS_LIST("PROGRESS_LIST"),
    COMPLETED_LIST("COMPLETED_LIST"),
    REJECTED_LIST("REJECTED_LIST");

    private final String label;
    EListType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    }

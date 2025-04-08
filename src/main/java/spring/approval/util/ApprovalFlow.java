package spring.approval.util;

import java.util.List;
import java.util.stream.Collectors;
import spring.approval.domain.Approver;

public  class ApprovalFlow {

    public static String generateApprovalFlow(List<Approver> approvers) {
        return approvers.stream()
                .map(Approver::getApprovalType)
                .distinct()
                .collect(Collectors.joining(">"));
    }

    public static String getNextDocStatus(int currentStatus, String approvalFlow) {
        List<String> flow = List.of(approvalFlow.split(">"));
        if (currentStatus == flow.size() - 1) {
            return "완료";
        }
        return flow.get(++currentStatus);
    }
}

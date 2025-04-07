package spring.approval.service.documents;

import java.util.Optional;
import org.springframework.stereotype.Service;
import spring.approval.repository.ApproverRepository;
import spring.approval.repository.documents.ExpenseReportRespository;

@Service
public class ApprovalProcessService {
    private final ExpenseReportRespository expenseReportRespository;
    private final AbsenteeismApprovalService absenteeismApprovalService;
    private final ApproverRepository approverRepository;



    public ApprovalProcessService(ExpenseReportRespository expenseReportRespository,
                                  AbsenteeismApprovalService absenteeismApprovalService,
                                  ApproverRepository approverRepository) {
        this.expenseReportRespository = expenseReportRespository;
        this.absenteeismApprovalService = absenteeismApprovalService;
        this.approverRepository = approverRepository;
    }


    // currentApprover의 status:"완료", bCurrent:false
    // findNextApprover return id의  status:"진행", bCurrent:true
    // 각 문서의 currentApproverId : findNextApprover return id로 바꾸기

    public  void updateStatus(String docId, Long memberId) {
        approverRepository.setBCurrentApproverFalse(docId, memberId);
        Optional<Long> nextApprover = approverRepository.findNextApprover(docId);
        if (nextApprover.isPresent()) {
            approverRepository.setBCurrentApproverTrue(docId, nextApprover.get());
            expenseReportRespository.updateDocCurrentApprover(docId, nextApprover);
        }
        else {
            // 문서 종료 currentApprover 없애기
        }
    }


}

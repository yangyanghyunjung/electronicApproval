package spring.approval.service.documents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.approval.domain.Approver;
import spring.approval.domain.ExpenseDetails;
import spring.approval.domain.ExpenseReport;
import spring.approval.dto.ApproverResponseDto;
import spring.approval.dto.documents.DocumentResponseDto;
import spring.approval.dto.documents.ExpenseReportRequestDto;
import spring.approval.dto.documents.ExpenseReportResponseDto;
import spring.approval.dto.documents.UpdateDocRequestDto;
import spring.approval.repository.ApproverRepository;
import spring.approval.repository.documents.ExpenseReportRespository;
import spring.approval.util.ApprovalFlow;
import spring.approval.util.DocumentIdGenerator;

@Service
@Slf4j
public class ExpenseReportService implements IDocumentService<ExpenseReportRequestDto> {
   private final ExpenseReportRespository expenseReportRespository;
   private final ApproverRepository approverRepository;

   @Autowired
    public ExpenseReportService(ExpenseReportRespository expenseReportRespository,
                                ApproverRepository approverRepository) {
       this.expenseReportRespository = expenseReportRespository;
       this.approverRepository = approverRepository;
   }

    /**
     * 문서 조회
     * @param docId
     * @param FOID
     * @return
     */
    public DocumentResponseDto getDocument(String docId, String FOID) {
        ExpenseReport expenseReport = expenseReportRespository.getDocument(docId, FOID);
       
        List<ApproverResponseDto> approvers = approverRepository.getApprovers(docId);
        List<ExpenseDetails> expenseDetails = mapJsonStrToObject(expenseReport.getExpenseDetails());

        ExpenseReportResponseDto expenseReportResponseDto = new ExpenseReportResponseDto();
        expenseReportResponseDto.setDocId(expenseReport.getDocId());
        expenseReportResponseDto.setFOID(expenseReport.getFOID());
        expenseReportResponseDto.setDept(expenseReport.getDept());
        expenseReportResponseDto.setWriter(expenseReport.getWriter());
        expenseReportResponseDto.setTitle(expenseReport.getTitle());
        expenseReportResponseDto.setPosition(expenseReport.getPosition());
        expenseReportResponseDto.setCreate_dt(expenseReport.getCreate_dt());
        expenseReportResponseDto.setSdate(expenseReport.getSdate());
        expenseReportResponseDto.setEdate(expenseReport.getEdate());
        expenseReportResponseDto.setExpenseDetails(expenseDetails);
        expenseReportResponseDto.setTotalAmount(expenseReport.getTotalAmount());
        expenseReportResponseDto.setTxtRem(expenseReport.getTxtRem());
        expenseReportResponseDto.setDocStatus(expenseReport.getDocStatus());
        expenseReportResponseDto.setRequester(expenseReport.getRequester());
        expenseReportResponseDto.setApprovalFlow(expenseReport.getApprovalFlow());


        DocumentResponseDto documentResponseDto = new DocumentResponseDto(approvers, expenseReportResponseDto);
        return documentResponseDto;
    }


    @Override
    @Transactional
    public ResponseEntity<String> draftDoc(ExpenseReportRequestDto document) {
        // docId generate (공통)
        String docId = DocumentIdGenerator.generateDocId(document.getFOID());

        // docId + approver + documnet
        document.setDocId(docId);

        // approvers의 docId도 변경
        List<Approver> approvers =  document.getApprovers();
        for (Approver approver : approvers) {
            approver.setDocId(docId);
        }

        // approvalFlow
        String approvalFlow = ApprovalFlow.generateApprovalFlow(approvers);
        document.setApprovalFlow(approvalFlow);

        // Approvers
        approverRepository.saveApprovers(document.getApprovers());
        return expenseReportRespository.saveDocument(document);
    }

    @Override
    @Transactional
    public ResponseEntity<String> updateDoc(UpdateDocRequestDto updateDocRequestDto) {
        String docId = updateDocRequestDto.getDocId();
        Long memberId = updateDocRequestDto.getMemberId();
        int approvalOrder = updateDocRequestDto.getApprovalOrder();

        approverRepository.setCurrentApproverInactive(docId, memberId);

        if (approverRepository.isApprovalGroupComplete(docId, approvalOrder)) {
            approverRepository.setNextApproverActive(docId, approvalOrder + 1);
            String nextDocStatus = ApprovalFlow.getNextDocStatus(approvalOrder, updateDocRequestDto.getApprovalFlow());
            expenseReportRespository.updateDocStatus(docId,nextDocStatus);
        }
        return ResponseEntity.ok("업데이트 성공");
    }


    private List<ExpenseDetails> mapJsonStrToObject(String jsonStr) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            List<ExpenseDetails> expenseDetails = mapper.readValue(jsonStr, new TypeReference<List<ExpenseDetails>>(){});

            return expenseDetails;

        } catch (Exception e) {
            throw new RuntimeException("JSON 파싱 실패", e);
        }
    }
}

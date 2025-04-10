package spring.approval.service.documents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.approval.domain.Approver;
import spring.approval.domain.documents.EFormType;
import spring.approval.domain.documents.ExpenseDetails;
import spring.approval.domain.documents.ExpenseDoc;
import spring.approval.dto.ApproverResponseDto;
import spring.approval.dto.documents.DocumentResponseDto;
import spring.approval.dto.documents.DraftRequestDto;
import spring.approval.dto.documents.ExpenseReportResponseDto;
import spring.approval.dto.documents.UpdateDocRequestDto;
import spring.approval.repository.ApproverRepository;
import spring.approval.repository.documents.ExpenseReportRespository;
import spring.approval.service.util.ApprovalFlow;
import spring.approval.service.util.DocumentIdGenerator;

@Slf4j
@Service("EXPENSE")
public class ExpenseDocService implements IDocumentService {
   private final ExpenseReportRespository expenseReportRespository;
   private final ApproverRepository approverRepository;
   @Autowired ObjectMapper objectMapper;

   @Autowired
    public ExpenseDocService(ExpenseReportRespository expenseReportRespository,
                             ApproverRepository approverRepository) {
       this.expenseReportRespository = expenseReportRespository;
       this.approverRepository = approverRepository;
   }

    /**
     * 문서 조회
     * @param docId
     * @return
     */
    public DocumentResponseDto getDocument(String docId) {
        ExpenseDoc expenseDoc = expenseReportRespository.getDocument(docId);
       
        List<ApproverResponseDto> approvers = approverRepository.getApprovers(docId);
        List<ExpenseDetails> expenseDetails = mapJsonStrToObject(expenseDoc.getExpenseDetails());

        ExpenseReportResponseDto expenseReportResponseDto = new ExpenseReportResponseDto();
        expenseReportResponseDto.setDocId(expenseDoc.getDocId());
        expenseReportResponseDto.setFOID(expenseDoc.getFOID());
        expenseReportResponseDto.setDept(expenseDoc.getDept());
        expenseReportResponseDto.setWriter(expenseDoc.getWriter());
        expenseReportResponseDto.setTitle(expenseDoc.getTitle());
        expenseReportResponseDto.setPosition(expenseDoc.getPosition());
        expenseReportResponseDto.setCreate_dt(expenseDoc.getCreate_dt());
        expenseReportResponseDto.setSdate(expenseDoc.getSdate());
        expenseReportResponseDto.setEdate(expenseDoc.getEdate());
        expenseReportResponseDto.setExpenseDetails(expenseDetails);
        expenseReportResponseDto.setTotalAmount(expenseDoc.getTotalAmount());
        expenseReportResponseDto.setTxtRem(expenseDoc.getTxtRem());
        expenseReportResponseDto.setDocStatus(expenseDoc.getDocStatus());
        expenseReportResponseDto.setRequester(expenseDoc.getRequester());
        expenseReportResponseDto.setApprovalFlow(expenseDoc.getApprovalFlow());


        DocumentResponseDto documentResponseDto = new DocumentResponseDto(approvers, expenseReportResponseDto);
        return documentResponseDto;
    }

    @Override
    @Transactional
    public void draftDoc(DraftRequestDto draftRequestDto) {
        //ExpenseDoc 고유 필드
        ExpenseDoc expenseDoc = objectMapper.convertValue(draftRequestDto.getContent(),ExpenseDoc.class);

        // docId generate (공통)
        String docId = DocumentIdGenerator.generateDocId(draftRequestDto.getFOID());
        // DraftDocDto 공통 필드
        expenseDoc.setDocId(docId);
        expenseDoc.setFOID(draftRequestDto.getFOID());
        expenseDoc.setDept(draftRequestDto.getDept());
        expenseDoc.setWriter(draftRequestDto.getWriter());
        expenseDoc.setTitle(draftRequestDto.getTitle());
        expenseDoc.setPosition(draftRequestDto.getPosition());
        expenseDoc.setCreate_dt(draftRequestDto.getCreate_dt());
        expenseDoc.setDocStatus(draftRequestDto.getDocStatus());
        expenseDoc.setRequester(draftRequestDto.getRequester());
        expenseDoc.setApprovers(draftRequestDto.getApprovers());

        // approvers의 docId도 변경
        List<Approver> approvers =  expenseDoc.getApprovers();
        for (Approver approver : approvers) {
            approver.setDocId(docId);
        }

        String approvalFlow = ApprovalFlow.generateApprovalFlow(approvers);
        expenseDoc.setApprovalFlow(approvalFlow);

        // Approvers
        approverRepository.saveApprovers(expenseDoc.getApprovers());
        expenseReportRespository.draftDoc(expenseDoc);
    }

    @Override
    @Transactional
    public void updateDoc(UpdateDocRequestDto updateDocRequestDto) {
        String docId = updateDocRequestDto.getDocId();
        Long memberId = updateDocRequestDto.getMemberId();
        int approvalOrder = updateDocRequestDto.getApprovalOrder();

        approverRepository.setCurrentApproverInactive(docId, memberId);

        if (approverRepository.isApprovalGroupComplete(docId, approvalOrder)) {
            approverRepository.setNextApproverActive(docId, approvalOrder + 1);
            String nextDocStatus = ApprovalFlow.getNextDocStatus(approvalOrder, updateDocRequestDto.getApprovalFlow());
            expenseReportRespository.updateDocStatus(docId,nextDocStatus);
        }
    }

    /**
     * 문서 양식 row들 json > 객체 List로 변환
     * @param jsonStr
     * @return
     */
    private List<ExpenseDetails> mapJsonStrToObject(String jsonStr) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            List<ExpenseDetails> expenseDetails = mapper.readValue(jsonStr, new TypeReference<List<ExpenseDetails>>(){});

            return expenseDetails;

        } catch (Exception e) {
            throw new RuntimeException("JSON 파싱 실패", e);
        }
    }

    @Override
    public EFormType getFormType() {
        return EFormType.EXPENSE;
    }
}

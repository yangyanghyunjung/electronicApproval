package spring.approval.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.approval.dto.documents.DocumentIdentifierDto;
import spring.approval.dto.documents.DocumentResponseDto;
import spring.approval.service.documents.AbsenteeismApprovalService;
import spring.approval.service.documents.ExpenseReportService;

@Slf4j
@RestController
@CrossOrigin("http://localhost:5173")
public class DocumentController {
    private final ExpenseReportService expenseReportService;
    private final AbsenteeismApprovalService absenteeismApprovalService;

    @Autowired
    public DocumentController(ExpenseReportService expenseReportService,
                              AbsenteeismApprovalService absenteeismApprovalService)
    {
        this.expenseReportService = expenseReportService;
        this.absenteeismApprovalService = absenteeismApprovalService;
    }

    @PostMapping("/getDocument")
    public DocumentResponseDto getDocument(@RequestBody DocumentIdentifierDto param) {
        String docId = param.getDocId();
        String FOID = param.getFOID();

        log.info("[DocumentController-DocumentResponseDto()] docId={}, FOID={}", docId, FOID);

        switch (FOID)
        {
            case "A0AF0E1":
                return expenseReportService.getDocument(docId, FOID);
            case "6B1E74F":
                return expenseReportService.getDocument(docId, FOID);
            default:
                throw new IllegalArgumentException("지원하지 않는 FOID입니다: " + FOID);
        }
    }

}

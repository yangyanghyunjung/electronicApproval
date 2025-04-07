package spring.approval.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.approval.dto.documents.ExpenseReportRequestDto;
import spring.approval.dto.documents.UpdateApprovalStateRequestDto;
import spring.approval.service.documents.ExpenseReportService;

@Slf4j
@RestController
@CrossOrigin("http://locahost:5173")
public class ExpenseReportController {

    private ExpenseReportService expenseReportService;

    public ExpenseReportController(ExpenseReportService expenseReportService) {
        this.expenseReportService = expenseReportService;
    }

    @PostMapping("/draft/expenseReport")
    public ResponseEntity<String> draftDoc(@RequestBody ExpenseReportRequestDto expenseReportRequestDto) {
        return expenseReportService.draftDoc(expenseReportRequestDto);
    }

    @PostMapping("/update/expenseReprt")

    public ResponseEntity<String> updateApproval(@RequestBody UpdateApprovalStateRequestDto updateApprovalStateRequestDto) {
        return null;
    }

}

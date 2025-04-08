package spring.approval.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.approval.dto.documents.ExpenseReportRequestDto;
import spring.approval.dto.documents.UpdateDocRequestDto;
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

    @PostMapping("/update/expenseReport")
    public ResponseEntity<String> updateApproval(@RequestBody UpdateDocRequestDto updateApprovalStateRequestDto) {
        return expenseReportService.updateDoc(updateApprovalStateRequestDto);
    }

}

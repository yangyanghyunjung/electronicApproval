package spring.approval.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.approval.dto.documents.DraftRequestDto;
import spring.approval.dto.documents.ExpenseReportRequestDto;
import spring.approval.dto.documents.UpdateDocRequestDto;
import spring.approval.service.documents.ExpenseDocService;

@Slf4j
@RestController
@CrossOrigin("http://locahost:5173")
public class ExpenseReportController {

    private ExpenseDocService expenseDocService;

    public ExpenseReportController(ExpenseDocService expenseDocService) {
        this.expenseDocService = expenseDocService;
    }

    @PostMapping("/draft/expenseReport")
    public void draftDoc(@RequestBody DraftRequestDto draftRequestDto) {
        expenseDocService.draftDoc(draftRequestDto);
    }

    @PostMapping("/update/expenseReport")
    public void updateApproval(@RequestBody UpdateDocRequestDto updateApprovalStateRequestDto) {
        expenseDocService.updateDoc(updateApprovalStateRequestDto);
    }

}

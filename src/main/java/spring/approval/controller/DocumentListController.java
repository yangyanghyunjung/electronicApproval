package spring.approval.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.approval.dto.lists.ListRequestDto;
import spring.approval.dto.lists.ListResponseDto;
import spring.approval.service.DocumentListService;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class DocumentListController {
    private final DocumentListService documentListService;

    @Autowired
    public DocumentListController(DocumentListService documentListService) {
        this.documentListService = documentListService;
    }

    @PostMapping(value = "/ApprovalList")
    public ListResponseDto getApprovalList(@RequestBody ListRequestDto param) {
        log.info("[DocumentController_getApprovalList] userId={}, query={}, startNo={}", param.getUserId(), param.getQuery(), param.getStartNo());

        ListResponseDto documentLists = documentListService.getList(param.getUserId(), param.getQuery(), param.getStartNo());

        return documentLists;
    }
}

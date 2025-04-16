package spring.approval.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.approval.dto.lists.ListRequestDto;
import spring.approval.dto.lists.ListResponseDto;
import spring.approval.service.IDocumentListService;

@Slf4j
@RestController
public class DocumentListController {
    private final IDocumentListService documentListService;

    @Autowired
    public DocumentListController(IDocumentListService documentListService) {
        this.documentListService = documentListService;
    }

    @PostMapping(value = "/getList")
    public ListResponseDto getApprovalList(@RequestBody ListRequestDto param) {
        log.info("[DocumentController_getApprovalList] userId={}, query={}, listType={}, startNo={}", param.getUserId(), param.getQuery(), param.getListType(), param.getStartNo());

        return documentListService.getList(param.getUserId(), param.getQuery(), param.getListType(), param.getStartNo());
    }
}

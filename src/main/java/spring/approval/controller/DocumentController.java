package spring.approval.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.approval.dto.documents.DocumentIdentifierDto;
import spring.approval.dto.documents.DocumentResponseDto;
import spring.approval.dto.documents.DraftRequestDto;
import spring.approval.dto.documents.UpdateDocRequestDto;
import spring.approval.service.DocumentServiceFactory;

@Slf4j
@RestController
public class DocumentController {
    private final DocumentServiceFactory documentServiceFactory;

    @Autowired
    public DocumentController( DocumentServiceFactory documentServiceFactory)
    {
        this.documentServiceFactory = documentServiceFactory;
    }

    @PostMapping("/getDocument")
    public DocumentResponseDto getDocument(@RequestBody DocumentIdentifierDto request) {
        return documentServiceFactory.getService(request.getFormType())
                .getDocument(request.getDocId());
    }
    @PostMapping("/draftDocument")
    public void draftDoc(@RequestBody DraftRequestDto draftRequestDto) {
        documentServiceFactory.getService(draftRequestDto.getFormType())
                .draftDoc(draftRequestDto);
    }

    @PostMapping("/updateDocument")
    public void updateDocument(@RequestBody UpdateDocRequestDto updateDocRequestDto) {
        documentServiceFactory.getService(updateDocRequestDto.getFormType())
                .updateDoc(updateDocRequestDto);
    }


}

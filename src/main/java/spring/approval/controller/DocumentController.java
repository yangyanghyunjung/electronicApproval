package spring.approval.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.approval.dto.documents.DocumentIdentifierDto;
import spring.approval.dto.documents.DocumentResponseDto;
import spring.approval.service.DocumentServiceFactory;
import spring.approval.service.documents.VacationDocService;
import spring.approval.service.documents.ExpenseDocService;

@Slf4j
@RestController
@CrossOrigin("http://localhost:5173")
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

}

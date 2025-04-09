package spring.approval.service.documents;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import spring.approval.domain.EFormType;
import spring.approval.dto.documents.DocumentResponseDto;
import spring.approval.dto.documents.DraftRequestDto;
import spring.approval.dto.documents.ExpenseReportRequestDto;
import spring.approval.dto.documents.UpdateDocRequestDto;

public interface IDocumentService {
    DocumentResponseDto getDocument(String docId);

    @Transactional
    void draftDoc(DraftRequestDto draftRequestDto);

    void updateDoc(UpdateDocRequestDto updateDocRequestDto);

    EFormType getFormType();
}

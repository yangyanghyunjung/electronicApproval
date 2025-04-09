package spring.approval.service.documents;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import spring.approval.dto.documents.DocumentResponseDto;
import spring.approval.dto.documents.DraftRequestDto;
import spring.approval.dto.documents.ExpenseReportRequestDto;
import spring.approval.dto.documents.UpdateDocRequestDto;

public interface IDocumentService {
    DocumentResponseDto getDocument(String docId, String FOID); // FOID는 안 받도록 변경 ==>

    @Transactional
    void draftDoc(DraftRequestDto draftRequestDto);

    void updateDoc(UpdateDocRequestDto updateDocRequestDto);
}

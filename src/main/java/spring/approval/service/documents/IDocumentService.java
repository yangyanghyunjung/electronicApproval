package spring.approval.service.documents;

import spring.approval.domain.documents.EFormType;
import spring.approval.dto.documents.DocumentResponseDto;
import spring.approval.dto.documents.DraftRequestDto;
import spring.approval.dto.documents.UpdateDocRequestDto;

public interface IDocumentService {
    DocumentResponseDto getDocument(String docId);

    void draftDoc(DraftRequestDto draftRequestDto);

    void updateDoc(UpdateDocRequestDto updateDocRequestDto);

    EFormType getFormType();
}

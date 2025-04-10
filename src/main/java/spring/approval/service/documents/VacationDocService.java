package spring.approval.service.documents;

import org.springframework.stereotype.Service;
import spring.approval.domain.documents.EFormType;
import spring.approval.dto.documents.DocumentResponseDto;
import spring.approval.dto.documents.DraftRequestDto;
import spring.approval.dto.documents.UpdateDocRequestDto;

@Service("VACATION")
public class VacationDocService implements IDocumentService{

    @Override
    public DocumentResponseDto getDocument(String docId) {
        return null;
    }

    @Override
    public void draftDoc(DraftRequestDto draftRequestDto) {

    }

    @Override
    public void updateDoc(UpdateDocRequestDto updateDocRequestDto) {

    }

    @Override
    public EFormType getFormType() {
        return EFormType.VACATION;
    }
}

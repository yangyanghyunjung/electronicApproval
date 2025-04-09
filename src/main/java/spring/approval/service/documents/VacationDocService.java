package spring.approval.service.documents;

import org.springframework.stereotype.Service;
import spring.approval.dto.documents.DocumentResponseDto;
import spring.approval.dto.documents.DraftRequestDto;
import spring.approval.dto.documents.UpdateDocRequestDto;
import spring.approval.dto.documents.VacationDocDto;

@Service
public class VacationDocService implements IDocumentService{

    @Override
    public DocumentResponseDto getDocument(String docId, String FOID) {
        return null;
    }

    @Override
    public void draftDoc(DraftRequestDto draftRequestDto) {

    }

    @Override
    public void updateDoc(UpdateDocRequestDto updateDocRequestDto) {

    }
}

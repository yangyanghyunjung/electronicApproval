package spring.approval.service.documents;

import org.springframework.http.ResponseEntity;
import spring.approval.dto.documents.UpdateDocRequestDto;

public interface IDocumentService<T> {
    ResponseEntity<String> draftDoc(T document);
    ResponseEntity<String> updateDoc(UpdateDocRequestDto updateDocRequestDto);
}

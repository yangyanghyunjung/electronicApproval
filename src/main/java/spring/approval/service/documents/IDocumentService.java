package spring.approval.service.documents;

import org.springframework.http.ResponseEntity;

public interface IDocumentService<T> {
//    T getDocument(String docId, String FOID);

    ResponseEntity<String> draftDoc(T document);
}

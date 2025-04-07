package spring.approval.repository.documents;

public interface IDocumentRepository<T> {
//    Document getDocument(String docId, String FOID);
    T getDocument(String docId, String FOID);

    String saveDocument();

    int getTotalCount(Long userId, String query, int startNo);
}

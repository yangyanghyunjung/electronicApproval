package spring.approval.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import spring.approval.domain.list.DocumentList;
import spring.approval.domain.list.EListType;

@Repository
public interface IDocumentListRepository {

    List<DocumentList> getList(Long userId, String query, EListType listType, int startNo);

    int getTotalCount(Long userId, String query, EListType listType);
}

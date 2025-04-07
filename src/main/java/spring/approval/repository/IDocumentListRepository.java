package spring.approval.repository;

import java.util.List;
import spring.approval.domain.DocumentList;
import spring.approval.dto.lists.ListResponseDto;

public interface IDocumentListRepository {

    List<DocumentList> findList(Long userId, String query, int startNo);

    ListResponseDto getList(Long userId, String query, int startNo);

    int getTotalCount(Long userId, String query, int startNo);
}

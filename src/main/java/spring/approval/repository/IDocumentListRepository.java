package spring.approval.repository;

import spring.approval.domain.list.EListType;
import spring.approval.dto.lists.ListResponseDto;

public interface IDocumentListRepository {

    ListResponseDto getList(Long userId, String query, EListType listType, int startNo);

    int getTotalCount(Long userId, String query, EListType listType);
}

package spring.approval.repository;

import spring.approval.dto.lists.ListResponseDto;

public interface IDocumentListRepository {

    ListResponseDto getList(Long userId, String query, int startNo);

    int getTotalCount(Long userId, String query, int startNo);
}

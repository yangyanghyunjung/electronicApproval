package spring.approval.service;

import spring.approval.domain.list.EListType;
import spring.approval.dto.lists.ListResponseDto;

public interface IDocumentListService {
    public ListResponseDto<?> getList(Long userId, String query, EListType listType, int startNo);

}

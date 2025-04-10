package spring.approval.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import spring.approval.domain.list.EListType;
import spring.approval.dto.lists.ListResponseDto;
import spring.approval.repository.IDocumentListRepository;

@Slf4j
public class DocumentListService {
    private final IDocumentListRepository iDocumentListRepository;

    @Autowired
    public DocumentListService(IDocumentListRepository IDocumentListRepository) {
        this.iDocumentListRepository = IDocumentListRepository;
    }

    /**
     * 전체 List 조회
     * @param userId
     * @param query
     * @param startNo
     * @return
     */
    public ListResponseDto getList(Long userId, String query, EListType listType, int startNo) {
        return iDocumentListRepository.getList(userId, query, listType, startNo);
    }


}

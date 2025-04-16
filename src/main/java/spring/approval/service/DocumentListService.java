package spring.approval.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.approval.domain.list.DocumentList;
import spring.approval.domain.list.EListType;
import spring.approval.dto.lists.ApprovalListDto;
import spring.approval.dto.lists.CompletedListDto;
import spring.approval.dto.lists.ListResponseDto;
import spring.approval.dto.lists.ProgressListDto;
import spring.approval.dto.lists.RejectedListDto;
import spring.approval.repository.IDocumentListRepository;

@Slf4j
@Service
public class DocumentListService implements IDocumentListService{
    private final IDocumentListRepository iDocumentListRepository;

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
    public ListResponseDto<?> getList(Long userId, String query, EListType listType, int startNo) {
        List<DocumentList> list = iDocumentListRepository.getList(userId, query, listType, startNo);
        int totalCount = iDocumentListRepository.getTotalCount(userId, query, listType);

        // 매핑
        List<?> mappedList = switch (listType) {
            case APPROVAL_LIST -> list.stream().map(this::toMapApproval).toList();
            case PROGRESS_LIST -> list.stream().map(this::toMapProgress).toList();
            case COMPLETED_LIST -> list.stream().map(this::toMapCompelte).toList();
            case REJECTED_LIST -> list.stream().map(this::toMapReject).toList();
        };
        // totalCount 까지 합치기
        return new ListResponseDto<>(mappedList, totalCount);
    }

    private ApprovalListDto toMapApproval(DocumentList l) {
        return new ApprovalListDto(l.getDocId(), l.getTitle(), l.getFOID(), l.getDocStatus(), l.getRequester(), l.getCreate_dt());
    }
    private ProgressListDto toMapProgress(DocumentList l) {
        return new ProgressListDto(l.getDocId(), l.getTitle(), l.getFOID(), l.getDocStatus(), l.getRequester(), l.getCreate_dt());
    }
    private CompletedListDto toMapCompelte(DocumentList l) {
        return new CompletedListDto(l.getDocId(), l.getTitle(), l.getFOID(), l.getDocStatus(), l.getRequester(), l.getCreate_dt());
    }
    private RejectedListDto toMapReject(DocumentList l) {
        return new RejectedListDto(l.getDocId(), l.getTitle(), l.getFOID(), l.getDocStatus(), l.getRequester(), l.getCreate_dt());
    }


}

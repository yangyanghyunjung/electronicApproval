package spring.approval.dto.lists;

import java.util.List;
import lombok.Data;
import spring.approval.domain.list.DocumentList;

@Data
public class ListResponseDto {
    private List<DocumentList> list;
    private int totalCount;

    public ListResponseDto(List<DocumentList> list, int totalCount) {
        this.list = list;
        this.totalCount = totalCount;
    }
}

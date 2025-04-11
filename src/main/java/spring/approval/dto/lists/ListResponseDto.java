package spring.approval.dto.lists;

import java.util.List;
import lombok.Data;

@Data
public class ListResponseDto<T> {
    private List<T> list;
    private int totalCount;

    public ListResponseDto(List<T> list, int totalCount) {
        this.list = list;
        this.totalCount = totalCount;
    }
}

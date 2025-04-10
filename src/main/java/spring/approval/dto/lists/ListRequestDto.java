package spring.approval.dto.lists;

import lombok.Data;
import lombok.NoArgsConstructor;
import spring.approval.domain.list.EListType;

@Data
@NoArgsConstructor
public class ListRequestDto {
    private Long userId;
    private String query="";
    private EListType listType;
    private Integer startNo = 0; //기본값

}

package spring.approval.dto.lists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListRequestDto {
    private Long userId;
    private String query="";
    private Integer startNo = 0; //기본값

}

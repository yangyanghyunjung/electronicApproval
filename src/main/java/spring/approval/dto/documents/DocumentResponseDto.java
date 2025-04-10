package spring.approval.dto.documents;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import spring.approval.dto.ApproverResponseDto;

@Data
public class DocumentResponseDto<T> {

    private List<ApproverResponseDto> approvers = new ArrayList<>();

    private T content;

    public DocumentResponseDto(List<ApproverResponseDto> approvers, T content) {
        this.approvers = approvers;
        this.content = content;
    }
}

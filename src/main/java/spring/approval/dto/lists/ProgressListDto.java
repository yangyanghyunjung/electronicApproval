package spring.approval.dto.lists;

import lombok.Data;

@Data
public class ProgressListDto {
    private String docId;
    private String title;
    private String FOID ;
    private String docStatus;
    private String requester;
}

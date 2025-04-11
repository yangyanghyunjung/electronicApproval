package spring.approval.dto.lists;

import lombok.Data;

@Data
public class ComletedListDto {
    private String docId;
    private String title;
    private String FOID ;
    private String docStatus;
    private String requester;
}

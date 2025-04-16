package spring.approval.dto.lists;

import java.util.Date;
import lombok.Data;

@Data
public class CompletedListDto {
    private String docId;
    private String title;
    private String FOID ;
    private String docStatus;
    private String requester;
    private Date create_dt;

    public CompletedListDto(String docId, String title, String FOID, String docStatus, String requester,
                            Date create_dt) {
        this.docId = docId;
        this.title = title;
        this.FOID = FOID;
        this.docStatus = docStatus;
        this.requester = requester;
        this.create_dt = create_dt;
    }
}

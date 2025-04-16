package spring.approval.domain.list;

import java.util.Date;
import lombok.Data;

@Data
public class DocumentList {
    private String docId;
    private String title;
    private String FOID ;
    private String docStatus;
    private String requester;
    private Date create_dt;

    public DocumentList() {
    }
}

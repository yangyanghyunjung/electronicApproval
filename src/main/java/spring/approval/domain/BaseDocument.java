package spring.approval.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.Data;
import spring.approval.dto.ApproverResponseDto;

@Data
public class BaseDocument {
    private String docId;
    private String FOID; // 예: vacation, expense, etc
    private String docStatus;
    private String title;
    private Long writerId;
    private String writerName;
    private String writerDept;
    private String position;
    private Date create_dt;


}

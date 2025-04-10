package spring.approval.dto.documents;

import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.Data;
import spring.approval.domain.Approver;
import spring.approval.domain.documents.EFormType;

@Data
public class DraftRequestDto {
    // 모든 문서 공통 필드
    private EFormType formType;
    private String FOID;
    private String dept;
    private String writer;
    private String title;
    private String position;
    private Date create_dt;

    private String docStatus;
    private String requester;

    private Map<String, Object> content; // 문서 고유 필드들
    private List<Approver> approvers; // 결재선


}

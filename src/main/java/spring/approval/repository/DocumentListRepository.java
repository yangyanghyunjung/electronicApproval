package spring.approval.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import spring.approval.domain.list.DocumentList;
import spring.approval.domain.list.EListType;
import spring.approval.dto.lists.ListResponseDto;

@Slf4j
public class DocumentListRepository implements IDocumentListRepository {
    private final JdbcTemplate jdbcTemplate;

    public DocumentListRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ListResponseDto getList(Long userId, String filter_search_condition, EListType listType, int startNo) {
        log.info("[Access-JdbcDocumnetListRepository] userId={}, filter_search_condition={}, startNo={}", userId, filter_search_condition, startNo);
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("getApprovalList")
                .returningResultSet("result", (rs, rowNum) -> {
                    DocumentList documentList = new DocumentList();
                    documentList.setDocId(rs.getString("docId"));
                    documentList.setTitle(rs.getString("title"));
                    documentList.setFOID(rs.getString("FOID"));
                    documentList.setDocStatus(rs.getString("docStatus"));
                    documentList.setRequester(rs.getString("requester"));
                    return documentList;
                });

        // 파라미터 설정
        Map<String, Object> inParams = new HashMap<>();
        inParams.put("userId", userId);
        inParams.put("filter_search_condition", filter_search_condition);
        inParams.put("listType", listType.getLabel());
        inParams.put("startNo", startNo);

        Map<String, Object> result = null;
        try {
            result = jdbcCall.execute(inParams);
        }
        catch (Exception e)
        {
            log.error("[getApproval] Exception: {}", e.getMessage(), e);
        }
        return new ListResponseDto((List<DocumentList>) result.get("result"),getTotalCount(userId, filter_search_condition, listType));
    }

    @Override
    public int getTotalCount(Long userId, String filter_search_condition, EListType listType) {
        String sql = "CALL getTotalCount(?, ?, ?)"; // 프로시저 호출 쿼리
        return jdbcTemplate.queryForObject(sql, new Object[]{userId, filter_search_condition, listType.getLabel()}, Integer.class);
    }

}

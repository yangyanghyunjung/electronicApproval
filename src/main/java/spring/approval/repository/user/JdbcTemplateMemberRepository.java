package spring.approval.repository.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import spring.approval.domain.Member;
import spring.approval.dto.user.UserInfoResponseDto;
import spring.approval.repository.user.MemberRepository;

public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns(("id"));

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", member.getEmail());
        parameters.put("name", member.getName());
        parameters.put("password", member.getPassword());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id=?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name=?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        List<Member> result = jdbcTemplate.query("select * from member where email=?", memberRowMapper(), email);
        return result.stream().findAny();
    }

    @Override
    public List<UserInfoResponseDto> findAll() {
        return jdbcTemplate.query("select * from member", membersRowMapper());
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {

            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setEmail(rs.getString("email"));
            member.setName(rs.getString("name"));
            member.setPassword(rs.getString("password"));
            member.setDept(rs.getString("dept"));
            member.setPosition(rs.getString("position"));

            return member;
        };
    }

    private RowMapper<UserInfoResponseDto> membersRowMapper() {
        return (rs, rowNum) -> {

            UserInfoResponseDto userInfoResponseDto = new UserInfoResponseDto();
            userInfoResponseDto.setId(rs.getLong("id"));
            userInfoResponseDto.setEmail(rs.getString("email"));
            userInfoResponseDto.setName(rs.getString("name"));
            userInfoResponseDto.setDept(rs.getString("dept"));
            userInfoResponseDto.setPosition(rs.getString("position"));

            return userInfoResponseDto;
        };
    }
}

package spring.approval.repository.user;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import spring.approval.domain.Member;
import spring.approval.dto.user.UserInfoResponseDto;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    Optional<Member> findByEmail(String email);

    List<UserInfoResponseDto> findAll();
}

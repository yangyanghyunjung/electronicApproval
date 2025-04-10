package spring.approval.repository.user;

import java.util.List;
import java.util.Optional;
import spring.approval.domain.user.Member;
import spring.approval.dto.user.UserInfoResponseDto;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByEmail(String email);

    List<UserInfoResponseDto> findAll();
}

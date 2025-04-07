package spring.approval.service;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import spring.approval.domain.Member;
import spring.approval.dto.LoginFormDto;
import spring.approval.dto.user.UserInfoResponseDto;
import spring.approval.repository.user.MemberRepository;

@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        log.info("[join] email={}, name={}, password={}", member.getEmail(), member.getName(), member.getPassword());

        return member.getId();
    }

    /**
     * 회원가입 중복검증
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<UserInfoResponseDto> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    /**
     * 로그인
     * @param loginFormDto
     * @return
     */
    public Member login(LoginFormDto loginFormDto) {
        return memberRepository.findByEmail(loginFormDto.getEmail())
                .filter(m -> m.getPassword().equals(loginFormDto.getPassword()))
                .orElse(null);

    }

}

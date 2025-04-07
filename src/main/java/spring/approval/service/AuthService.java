package spring.approval.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import spring.approval.domain.Member;
import spring.approval.dto.LoginFormDto;
import spring.approval.repository.user.MemberRepository;

@Slf4j
public class AuthService {
    private final MemberRepository memberRepository;

    @Autowired
    public AuthService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    public Member login(LoginFormDto loginFormDto) {
//        return memberRepository.findByEmail(loginFormDto.getEmail())
//                .filter(m -> m.getPassword().equals(loginFormDto.getPassword()))
//                .orElse(null);
//    }

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

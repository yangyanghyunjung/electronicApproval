package spring.approval.controller;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.approval.domain.user.Member;
import spring.approval.domain.user.MemberForm;
import spring.approval.dto.user.LoginFormDto;
import spring.approval.dto.user.UserInfoResponseDto;
import spring.approval.service.MemberService;

@Slf4j
@RestController
@RequestMapping("/user")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    /**
     * 회원가입
     * @param form
     * @return
     */
    @PostMapping(value = "/new")
    public ResponseEntity<String> create(@RequestBody MemberForm form) {

        log.info("[create] email={}, name={}, password={}", form.getEmail(), form.getName(), form.getPassword());
        Member member = new Member();
        member.setEmail(form.getEmail());
        member.setName(form.getName());
        member.setPassword(form.getPassword());
        Long memberId = memberService.join(member);

        return ResponseEntity.ok("회원 가입 성공: ID = " + memberId);
    }



    /**
     * 로그인
     * @param loginForm
     * @param session
     * @return
     */
    @PostMapping(value = "/login")
    // 굳이 UserInfoDto로 return할 필요가 있나?
    public UserInfoResponseDto logIn(@RequestBody LoginFormDto loginForm, HttpSession session) {
        Member member = memberService.login(loginForm);

        UserInfoResponseDto userInfo = new UserInfoResponseDto(
                member.getId(), member.getEmail(), member.getName(), member.getDept(), member.getPosition());
        session.setAttribute("userInfo", userInfo);

        return userInfo;
    }

    /**
     * 로그아웃
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        log.info("[AuthController-logout()] userInfo= {}", session.getAttribute("userInfo"));
        session.invalidate();
        return ResponseEntity.ok("Logged out");
    }

    /**
     * user 정보 반환
     * @param session
     * @return
     */
    @GetMapping("/userInfo")
    public UserInfoResponseDto getUserInfo(HttpSession session) {
        UserInfoResponseDto userInfo = (UserInfoResponseDto) session.getAttribute("userInfo");
        log.info("[AuthController-getUserInfo()] userInfo= {}", userInfo);
        if (userInfo == null) {
            return null;
        }
        return userInfo;
    }

    /**
     * all user 정보 반환
     * @return
     */
    @GetMapping("/allUserInfo")
    public List<UserInfoResponseDto> gtetAllUserInfo() {
        List<UserInfoResponseDto> members = memberService.findMembers();

        return members;
    }
}

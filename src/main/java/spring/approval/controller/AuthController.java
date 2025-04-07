package spring.approval.controller;


import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.approval.domain.Member;
import spring.approval.dto.LoginFormDto;
import spring.approval.dto.user.UserInfoResponseDto;
import spring.approval.service.AuthService;

@Slf4j
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
    private final AuthService authService;
//    private static final String SECRET_KEY = "916549462156";

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * 로그인
     * @param loginForm
     * @param session
     * @return
     */
    @PostMapping(value = "/login")
    public UserInfoResponseDto logIn(@RequestBody LoginFormDto loginForm, HttpSession session) {
        Member member = authService.login(loginForm);

        UserInfoResponseDto userInfo = new UserInfoResponseDto(
                member.getId(), member.getEmail(), member.getName(), member.getDept(), member.getPosition());
        session.setAttribute("userInfo", userInfo);
        log.info("[AuthController-logIn()] userInfo= {}", userInfo);
        log.info("[AuthController-logIn()] session.getAttribute= {}", session.getAttribute("userInfo"));

        return userInfo;
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
     * 로그인
     * @param loginFormDto
     * @return
     */
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, String>> login(@RequestBody LoginFormDto loginFormDto) {
//        Member member = authService.login(loginFormDto);
//
//        if (member != null) {
//            String token = Jwts.builder()
//                    .setSubject(loginFormDto.getEmail())
//                    .claim("role", "role")
//                    .setIssuedAt(new Date())
//                    .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1시간
//                    .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
//                    .compact();
//
//            Map<String, String> response = new HashMap<>();
//            response.put("token", token);
//            return ResponseEntity.ok(response);
//        }
//
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//    }
//
//
//    @GetMapping("/userInfo")
//    public ResponseEntity<Map<String, String>> getUserInfo(@RequestHeader("Authorization") String token) {
//        try {
//            Jws<Claims> claims = Jwts.parserBuilder()
//                    .setSigningKey(SECRET_KEY.getBytes())
//                    .build()
//                    .parseClaimsJws(token.replace("Bearer", ""));
//
//            String userEmail = claims.getBody().getSubject();
//
//            Map<String, String> userInfo = new HashMap<>();
//            userInfo.put("email", userEmail);
//            return ResponseEntity.ok(userInfo);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//        }
//
//    }


}

package spring.approval.domain.user;

import lombok.Data;

@Data
public class MemberForm {
    private String email;
    private String name;
    private String password;
}

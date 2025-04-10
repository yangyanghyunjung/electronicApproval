package spring.approval.domain.user;

import lombok.Data;

@Data
public class Member {
    Long id;
    String email;
    String name;
    String password;
    String dept;
    String position;

    public Member() {
    }

}

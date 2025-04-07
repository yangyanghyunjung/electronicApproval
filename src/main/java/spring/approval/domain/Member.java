package spring.approval.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
//@Getter @Setter
public class Member {
    Long id;
    String email;
    String name;
    String password;
    String dept;
    String position;

    public Member() {
    }

    public Member(Long id, String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }
}

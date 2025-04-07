package spring.approval.dto.user;

import lombok.Data;

@Data
public class UserInfoResponseDto {
    private Long id;
    private String email;
    private String name;
    private String dept;
    private String position;


    public UserInfoResponseDto() {
    }

    public UserInfoResponseDto(Long id, String email, String name, String dept, String position) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.dept = dept;
        this.position = position;
    }

}

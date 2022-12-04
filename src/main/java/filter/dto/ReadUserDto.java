package filter.dto;

import entity.Role;
import lombok.*;
//import lombok.Getter;

@Value
@Builder
//@AllArgsConstructor
public class ReadUserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Role role;
//    public ReadUserDto(Integer id, String username, String email, String password, Role role) {
//        this.id = id;
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.role = role;
//    }
}

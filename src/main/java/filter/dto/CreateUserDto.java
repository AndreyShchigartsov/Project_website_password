package filter.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
@Value
@Builder
public class CreateUserDto {
        String login;
        String name;
        String password;
        String role;

        public CreateUserDto(String login, String name, String password, String role) {
                this.login = login;
                this.name = name;
                this.password = password;
                this.role = role;
        }
}

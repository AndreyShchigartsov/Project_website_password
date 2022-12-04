package filter.dto;

import entity.User;
import lombok.*;

import java.util.Optional;

@Value
@AllArgsConstructor
@Builder
public class CreateStorageDto {
    User userId;
    String password;
    String website;
    String comment;

}

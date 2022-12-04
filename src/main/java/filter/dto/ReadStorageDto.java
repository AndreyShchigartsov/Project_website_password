package filter.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
@Builder
public class ReadStorageDto {
    Integer id;
    Integer userId;
    String password;
    String website;
    String comment;

    public ReadStorageDto(Integer id,Integer userId, String password, String website, String comment) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.website = website;
        this.comment = comment;
    }
}

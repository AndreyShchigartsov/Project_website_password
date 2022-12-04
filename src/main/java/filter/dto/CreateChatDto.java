package filter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class CreateChatDto {
    String title;
    String loginCreation;
}

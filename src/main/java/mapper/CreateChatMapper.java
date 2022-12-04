package mapper;

import filter.dto.CreateChatDto;
import entity.Chat;
import entity.Type;

import java.time.LocalDate;

public class CreateChatMapper implements Mapper<CreateChatDto, Chat>{
    @Override
    public Chat mapFrom(CreateChatDto object) {
        return Chat.builder()
                .title(object.getTitle())
                .timeCreating(LocalDate.of(2001,12,14))
                .loginCreation(object.getLoginCreation())
                .type(Type.PRIVATE)
                .build();
    }
}

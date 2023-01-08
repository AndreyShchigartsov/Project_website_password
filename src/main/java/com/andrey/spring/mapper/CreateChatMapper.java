package com.andrey.spring.mapper;

import com.andrey.spring.filter.dto.ChatCreateDto;
import com.andrey.spring.database.entity.Chat;
import com.andrey.spring.database.entity.Type;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CreateChatMapper implements Mapper<ChatCreateDto, Chat>{
    @Override
    public Chat mapFrom(ChatCreateDto object) {
        return Chat.builder()
                .title(object.getTitle())
                .timeCreating(LocalDate.of(2001,12,14))
                .loginCreating(object.getLoginCreation())
                .type(Type.PRIVATE)
                .build();
    }
}

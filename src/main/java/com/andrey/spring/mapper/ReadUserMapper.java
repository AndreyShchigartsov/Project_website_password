package com.andrey.spring.mapper;

import com.andrey.spring.filter.dto.UserReadDto;
import com.andrey.spring.database.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class ReadUserMapper implements Mapper<User, UserReadDto>{

    @Override
    public UserReadDto mapFrom(User object) {
        return UserReadDto.builder()
                .id(object.getId())
                .login(object.getLogin())
                .email(object.getEmail())
                .role(object.getRole())
                .build();
    }
}

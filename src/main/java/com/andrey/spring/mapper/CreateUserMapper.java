package com.andrey.spring.mapper;

import com.andrey.spring.filter.dto.UserCreateDto;
import com.andrey.spring.database.entity.Role;
import com.andrey.spring.database.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateUserMapper implements Mapper<UserCreateDto, User>{

    private final PasswordEncoder passwordEncoder;

    @Override
    public User mapFrom(UserCreateDto object) {
        User user = new User();
        user.setLogin(object.getLogin());
        user.setEmail(object.getEmail());
        user.setBirthday(object.getBirthDate());
        user.setRole(object.getRole());

        Optional.ofNullable(object.getRawPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);

        return user;
//        return User.builder()
//                .login(object.getLogin())
//                .email(object.getEmail())
//                .password(Optional.ofNullable(object.getRawPassword())
//                        .filter(StringUtils::hasText)
//                        .map(passwordEncoder::encode).get())
//                .birthday(object.getBirthDate())
//                .role(object.getRole())
//                .build();
    }
}

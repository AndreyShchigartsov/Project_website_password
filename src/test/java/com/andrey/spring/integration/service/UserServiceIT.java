package com.andrey.spring.integration.service;

import com.andrey.spring.ApplicationRunner;
import com.andrey.spring.database.entity.Role;
import com.andrey.spring.database.entity.User;
import com.andrey.spring.database.repository.UserRepository;
import com.andrey.spring.filter.dto.UserCreateDto;
import com.andrey.spring.filter.dto.UserReadDto;
import com.andrey.spring.integration.IntegrationTestBase;
import com.andrey.spring.integration.annotation.IT;
import com.andrey.spring.mapper.CreateUserMapper;
import com.andrey.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {

    private final UserService userService;
    private final UserRepository userRepository;
    private final CreateUserMapper createUserMapper;

    @Test
    void chechCreate(){
        var users = userRepository.findAll();
        Assertions.assertThat(users).hasSize(3);
        var user = UserCreateDto.builder()
                .login("Diman")
                .email("diman@yandex.com")
                .rawPassword("Dima")
                .birthDate(LocalDate.now())
                .role(Role.USER)
                .build();

        var actualResult = userService.create(user);
        assertEquals(user.getLogin(), actualResult.getLogin());
        assertEquals(user.getEmail(), actualResult.getEmail());
        var usersNew = userRepository.findAll();
        Assertions.assertThat(usersNew).hasSize(4);
    }
}

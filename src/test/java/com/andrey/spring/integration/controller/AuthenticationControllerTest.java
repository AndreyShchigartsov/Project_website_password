package com.andrey.spring.integration.controller;

import com.andrey.spring.filter.dto.UserCreateDto;
import com.andrey.spring.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.andrey.spring.filter.dto.UserCreateDto.*;
import static com.andrey.spring.filter.dto.UserCreateDto.Fields.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class AuthenticationControllerTest extends IntegrationTestBase {

    private final MockMvc mockMvc;

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/registration")
                .param(login, "Login")
                .param(email, "Email@email.com")
                .param(password, "pass")
                .param(birthDate,"1999-08-09")
                .param(role, "USER")
        )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrl("/login")
                );
    }

}
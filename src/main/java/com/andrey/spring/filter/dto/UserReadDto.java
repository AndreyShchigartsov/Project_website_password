package com.andrey.spring.filter.dto;

import com.andrey.spring.database.entity.Role;
import lombok.*;

import java.time.LocalDate;
//import lombok.Getter;

@Value
@Builder
public class UserReadDto {
    Long id;
    String login;
    String email;
    LocalDate birthDate;
    Role role;
}

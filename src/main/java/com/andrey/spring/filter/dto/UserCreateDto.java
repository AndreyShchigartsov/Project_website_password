package com.andrey.spring.filter.dto;

import com.andrey.spring.database.entity.Role;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.hibernate.validator.constraints.UniqueElements;
import org.postgresql.util.LruCache;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
@Builder
@FieldNameConstants
public class UserCreateDto {

        @Size(min = 3, max=64)
        @NotNull
        String login;

        @Email
        String email;

//        @NotBlank(groups = CreateAction.class)
        @NotNull
        String rawPassword;

        @NotNull
        LocalDate birthDate;

        Role role;
}

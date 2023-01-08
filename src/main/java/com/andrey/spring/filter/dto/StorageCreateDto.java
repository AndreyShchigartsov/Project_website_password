package com.andrey.spring.filter.dto;

import com.andrey.spring.database.entity.User;
import lombok.*;

@Value
@AllArgsConstructor
@Builder
public class StorageCreateDto {
    User userId;
    String password;
    String website;
    String comment;
}

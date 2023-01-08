package com.andrey.spring.filter.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StorageReadDto {
    Long id;
    Long userId;
    String password;
    String website;
    String comment;
}

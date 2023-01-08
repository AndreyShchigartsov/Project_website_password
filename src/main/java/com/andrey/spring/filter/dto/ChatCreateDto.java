package com.andrey.spring.filter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class ChatCreateDto {
    String title;
    String loginCreation;
}

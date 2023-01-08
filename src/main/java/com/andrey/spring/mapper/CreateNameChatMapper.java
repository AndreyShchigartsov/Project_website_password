package com.andrey.spring.mapper;

import org.springframework.stereotype.Component;

@Component
public class CreateNameChatMapper {
    public String mapFrom(String str1 , String str2) {
        return str1 +"_" + str2;
    }
}

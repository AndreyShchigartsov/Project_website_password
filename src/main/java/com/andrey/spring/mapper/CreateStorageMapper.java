package com.andrey.spring.mapper;

import com.andrey.spring.filter.dto.StorageCreateDto;
import com.andrey.spring.database.entity.Storage;
import org.springframework.stereotype.Component;

@Component
public class CreateStorageMapper implements Mapper<StorageCreateDto, Storage>{

    @Override
    public Storage mapFrom(StorageCreateDto object) {
        return Storage.builder()
                .user(object.getUserId())
                .password(object.getPassword())
                .website(object.getWebsite())
                .comment(object.getComment())
                .build();
    }
}

package com.andrey.spring.mapper;

import com.andrey.spring.filter.dto.StorageReadDto;
import com.andrey.spring.database.entity.Storage;
import org.springframework.stereotype.Component;

@Component
public class ReadStorageMapper implements Mapper<Storage, StorageReadDto>{
    @Override
    public StorageReadDto mapFrom(Storage object) {
//        return new ReadStorageDto(object.getId(),object.getUserId(), object.getPassword(), object.getWebsite(), object.getComment());
        return StorageReadDto.builder()
                .id(object.getId())
                .userId(object.getId())
                .password(object.getPassword())
                .website(object.getWebsite())
                .comment(object.getComment())
                .build();
    }
}

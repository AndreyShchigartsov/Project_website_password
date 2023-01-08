package com.andrey.spring.filter.dto;

import com.andrey.spring.database.entity.User;

public record StorageFilter(String password,
                            String website,
                            String comment) {

}

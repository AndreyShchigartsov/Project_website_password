package com.andrey.spring.database.repository;

import com.andrey.spring.database.entity.User;
import com.andrey.spring.filter.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);
}

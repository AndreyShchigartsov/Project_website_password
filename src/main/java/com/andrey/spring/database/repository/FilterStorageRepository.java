package com.andrey.spring.database.repository;

import com.andrey.spring.database.entity.User;
import com.andrey.spring.filter.dto.StorageFilter;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FilterStorageRepository {

    List<User> findAllStorageFilter(StorageFilter filter, User user, Pageable pageable);
}

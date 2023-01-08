package com.andrey.spring.database.repository;

import com.andrey.spring.database.entity.QStorage;
import com.andrey.spring.database.entity.User;
import com.andrey.spring.database.querydsl.QPredicates;
import com.andrey.spring.filter.dto.StorageFilter;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.andrey.spring.database.entity.QStorage.*;
import static com.andrey.spring.database.entity.QUser.user;

public class FilterStorageRepositoryImpl implements FilterStorageRepository{

    private static EntityManager entityManager;

    @Override
    public List<User> findAllStorageFilter(StorageFilter filter, User user, Pageable pageable) {
//        var predicate = QPredicates.builder()
//                .add(filter.password(), storage.password::containsIgnoreCase)
//                .add(filter.website(), storage.website::containsIgnoreCase)
//                .add(filter.comment(), storage.comment::containsIgnoreCase)
//                .build();
//
//        return new JPAQuery<User>(entityManager)
//                .select(storage)
//                .from(storage)
//                .where(predicate)
//                .;
        return null;
    }
}

package com.andrey.spring.database.repository;

import com.andrey.spring.database.entity.User;
import com.andrey.spring.database.querydsl.QPredicates;
import com.andrey.spring.filter.dto.UserFilter;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static com.andrey.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    public final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.login(), user.login::containsIgnoreCase)
                .add(filter.email(), user.email::containsIgnoreCase)
                .add(filter.birthday(), user.birthday::before)
                .build();

        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();
    }
}

package com.andrey.spring.database.repository;

import com.andrey.spring.database.entity.Chat;
import com.andrey.spring.database.entity.User;
import com.andrey.spring.database.entity.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserChatRepository extends JpaRepository<UserChat, Long>, QuerydslPredicateExecutor<UserChat> {
//    void save(User user1, User user2, Chat chat);
}
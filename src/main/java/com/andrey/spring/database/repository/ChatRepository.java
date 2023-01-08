package com.andrey.spring.database.repository;

import com.andrey.spring.database.entity.Chat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> , QuerydslPredicateExecutor<Chat> {

    /*
    /Достать все смс из конкретного чата
    */
//    @EntityGraph(attributePaths = {"listSms"})
    @Query("select c from Chat c where c.title = :titleChat")
    Page<Chat> findAllSmsByChat(String titleChat, Pageable pageable);

    /*
    /Создать чат
    */
    Chat save(Chat chat);

    /*
    /Найти чат по названию
    */
    @Query("select c from Chat c where c.title = :title")
    Optional<Chat> findChatByTitle(String title);

    /*
    /Найти чат по id
    */
    @Query("select c from Chat c where c.id = :id")
    Optional<Chat> findChatById(Long id);

    /*
    /Достать все чаты
    */
    List<Chat> findAll();

    /*
   /Достать все чаты
   */
    List<Chat> findAllBy(Pageable pageable);

    /*
    /Удалить чат
    */
    void delete(Chat chat);


    /*
    /Обновить чат
    */
//    public boolean update(Chat value);

}
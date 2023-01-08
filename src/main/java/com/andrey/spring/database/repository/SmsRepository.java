package com.andrey.spring.database.repository;

import com.andrey.spring.database.entity.Chat;
import com.andrey.spring.database.entity.Sms;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface SmsRepository extends
        JpaRepository<Sms,Long>,
        QuerydslPredicateExecutor<Sms> {

    /*
   /Достать все смс из конкретного чата
   */
    @EntityGraph(attributePaths = {"chat"})
    @Query("select s from Sms s where s.chat = :chat")
    List<Sms> findSmsTitleChat(Chat chat);//StackOverflowError исправить ошибку(Тест проходит)

    /*
    /Достать лист смс из конкретного чата
    */
    @EntityGraph(attributePaths = {"chat"})
    @Query("select s from Sms s where s.chat = :chat")
    Page<Sms> findSmsTitleChat(Chat chat, Pageable pageable);//StackOverflowError исправить ошибку(Тест проходит)

    /*
    /Сохранить смс
    */
    Sms save(Sms sms);
}






    /*
    /Сохранить смс в конкретный чат
    */
//    Sms saveSmsInChat(Sms sms, Chat chat);

//    public List<Sms> findSmsTitleChat(Chat chat) {
//        var session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        var list = session.createQuery("select s from Sms s where s.chat.id = :title", Sms.class)
//                .setParameter("title", chat.getId())
//                .list();
//        session.getTransaction().commit();
//        return list;
//    }
//public void addSms(Sms sms) {
//    var session = sessionFactory.getCurrentSession();
//    session.beginTransaction();
//    session.persist(sms);
//    session.getTransaction().commit();
//}
package dao;

import entity.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.time.LocalDate;

@RequiredArgsConstructor
public class UserChatDao {
    private final SessionFactory sessionFactory;

    public static void main(String[] args) {
//        var chat = Chat.builder()
//                .title("title4")
//                .timeCreating(LocalDate.of(2000,12,14))
//                .loginCreation("Andrey")
//                .type(Type.PRIVATE)
//                .build();
//        var user1 = User.builder()
//                .login("Test1")
//                .email("Anton")
//                .password("12134")
//                .birthday(LocalDate.of(2000,1,19))
//                .role(Role.USER)
//                .build();
//        var user2 = User.builder()
//                .login("Test2")
//                .email("Anton")
//                .password("12134")
//                .birthday(LocalDate.of(2000,1,19))
//                .role(Role.USER)
//                .build();
        try (var sessionFactory = HibernateUtil.buildSessionFactory()) {
            var session = sessionFactory.getCurrentSession();
            session.beginTransaction();
//
            var user1 = session.get(User.class,5);
            var user2 = session.get(User.class,6);
            var chat1 = session.get(Chat.class,1);

            UserChatDao userChatDao = new UserChatDao(sessionFactory);
            userChatDao.save(user1,user2,chat1);
            session.getTransaction().commit();
        }
    }

    public void save(User user1,User user2, Chat chat){
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var UserChat1 = UserChat.builder()
                .user(user1)
                .chat(chat)
                .createdAt(LocalDate.of(2000,10,20))
                .build();
//        UserChat1.setChat1(chat);
//        UserChat1.setUser1(user1);
        session.persist(UserChat1);

        var UserChat2 = UserChat.builder()
                .createdAt(LocalDate.of(2000,10,20))
                .user(user2)
                .chat(chat)
                .build();
//        UserChat2.setUser1(user2);
//        UserChat2.setChat1(chat);
        session.persist(UserChat2);
        session.getTransaction().commit();
    }
}

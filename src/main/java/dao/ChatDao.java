package dao;

import entity.Chat;
import entity.Sms;
import entity.Type;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ChatDao implements Dao<Long, Chat>{
    private final SessionFactory sessionFactory;

    public static void main(String[] args) {
        var chat = Chat.builder()
                .title("title5")
                .timeCreating(LocalDate.of(2000,12,14))
                .loginCreation("Andrey")
                .type(Type.PRIVATE)
                .build();
        var sms = Sms.builder()
                .chat(chat)
                .sms("sms1")
                .time(LocalDate.of(2000,12,14))
                .loginSender("andrey")
                .build();
        try (var sessionFactory = HibernateUtil.buildSessionFactory()) {
//            var session = sessionFactory.getCurrentSession();

            ChatDao dialogue = new ChatDao(sessionFactory);

            var bool = dialogue.findChat("title2");
            System.out.println(bool);
//            session.getTransaction().commit();
        }
    }
    @Override
    public Chat save(Chat chat){
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(chat);
        session.getTransaction().commit();
        return chat;
    }

    public Optional<Chat> findChat(String title) {
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var bool = Optional.ofNullable(session.createQuery("SELECT c from Chat c where c.title = :title",Chat.class)
                .setParameter("title", title)
                .uniqueResult());
        session.getTransaction().commit();
        return bool;
    }
    @Override
    public Optional<Chat> findId(Long key) {
        return Optional.empty();
    }

    @Override
    public List<Chat> findAll() {
        return null;
    }

    @Override
    public boolean update(Chat value) {
        return false;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }


}


package dao;

import entity.Chat;
import entity.Sms;
import entity.Storage;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class SmsDao {
    private final SessionFactory sessionFactory;

    public static void main(String[] args) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory()) {
            var session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Chat chat = session.get(Chat.class,1);
            var sms = Sms.builder()
                    .chat(chat)
                    .sms("sms1")
                    .time(LocalDate.of(2000, 12, 14))
                    .loginSender("andrey")
                    .build();
            SmsDao smsDao = new SmsDao(HibernateUtil.buildSessionFactory());
            smsDao.addSms(sms);

            session.getTransaction().commit();
        }
    }

    public List<Sms> findSmsTitleChat(Chat chat) {
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var list = session.createQuery("select s from Sms s where s.chat.id = :title", Sms.class)
                .setParameter("title", chat.getId())
                .list();
        session.getTransaction().commit();
        return list;
    }
    public void addSms(Sms sms) {
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(sms);
        session.getTransaction().commit();
    }
}

package service;

import dao.SmsDao;
import dao.UserDao;
import entity.Chat;
import entity.Sms;
import util.HibernateUtil;

import java.util.List;

public class SmsService {
    private static final SmsService INSTANCE = new SmsService();
    private final SmsDao smsDao = new SmsDao(HibernateUtil.buildSessionFactory());
    public List<Sms> findSms(Chat chat) {
        List<Sms> listSms = smsDao.findSmsTitleChat(chat);
        return listSms;
    }
    public void addSms(String sms, String chatTitle) {
        //Создать smsMapper
        //Добавить смс в бд

//        listSms = smsDao.findSmsTitleChat(sms);
//        return listSms;
//        return null;
    }

    public static SmsService getInstance(){
        return INSTANCE;
    }
}

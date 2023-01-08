package com.andrey.spring.service;

import com.andrey.spring.database.repository.SmsRepository;
import com.andrey.spring.database.entity.Chat;
import com.andrey.spring.database.entity.Sms;

import java.util.List;

public class SmsService {
    private static final SmsService INSTANCE = new SmsService();
    private SmsRepository smsRepository;
    public List<Sms> findSms(Chat chat) {
//        List<Sms> listSms = smsRepository.findSmsTitleChat(chat);
//        return listSms;
        return null;
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

package com.andrey.spring.integration.repository;

import com.andrey.spring.database.entity.Chat;
import com.andrey.spring.database.entity.Sms;
import com.andrey.spring.database.repository.SmsRepository;
import com.andrey.spring.integration.IntegrationTestBase;
import com.andrey.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class SmsRepositoryTest extends IntegrationTestBase {

    private final EntityManager entityManager;
    private final SmsRepository smsRepository;

    @Test
    void checkAudit(){
        var sms = smsRepository.findById(1L).get();
        sms.setSms("Hello");

        smsRepository.flush();

        var andrey = sms.getModifiedBy();
        var newSms = sms.getSms();

        assertEquals(andrey,"Andrey");
        assertEquals(newSms,"Hello");
    }

    @Test
    void checkFindSmsTitleChatPageable(){
        Chat chat = entityManager.find(Chat.class, 1L);
        var pageable = PageRequest.of(0, 2, Sort.by("id"));
        var smsPage = smsRepository.findSmsTitleChat(chat, pageable);
        Assertions.assertThat(smsPage).hasSize(2);

        while (smsPage.hasNext()){
            smsPage = smsRepository.findSmsTitleChat(chat, smsPage.nextPageable());
            Assertions.assertThat(smsPage).hasSize(1);
        }
    }

    @Test
    void checkSave(){
        Chat chat = entityManager.find(Chat.class, 1L);
        Sms sms = new Sms();
        sms.setSms("Hello");
        sms.setChat(chat);
        sms.setTime(LocalDate.now());
        sms.setLoginSender("Andrey");
        var smsSave = smsRepository.save(sms);
        assertNotNull(smsSave);
        assertEquals("Hello", smsSave.getSms());
        var smsList = smsRepository.findAll();
        Assertions.assertThat(smsList).hasSize(15);
    }

    @Test
    void checkFindSmsTitleChat(){
        Chat chat = entityManager.find(Chat.class, 1L);
        var allSmsChat = smsRepository.findSmsTitleChat(chat);
        Assertions.assertThat(allSmsChat).hasSize(3);
        var sms = allSmsChat.get(1);
        assertEquals("hello chatUser1", sms.getSms());
    }
}
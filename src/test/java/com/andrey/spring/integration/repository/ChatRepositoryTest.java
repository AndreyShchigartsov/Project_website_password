package com.andrey.spring.integration.repository;

import com.andrey.spring.database.entity.Chat;
import com.andrey.spring.database.entity.Type;
import com.andrey.spring.database.repository.ChatRepository;
import com.andrey.spring.database.repository.UserRepository;
import com.andrey.spring.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class ChatRepositoryTest extends IntegrationTestBase {

    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    @Test
    void checkAudit(){
        var chat = chatRepository.findById(1L).get();
        chat.setType(Type.PUBLIC);

        userRepository.flush();

        var andrey = chat.getModifiedBy();
        var type = chat.getType();

        assertEquals(andrey,"Andrey");
        assertEquals(Type.PUBLIC, type);
    }

    @Test
    @Disabled
    void checkFindAllSmsByChat(){
        var pageable = PageRequest.of(0, 2, Sort.by("id"));
        Chat chat = entityManager.find(Chat.class, 1L);
        chat.setType(Type.PUBLIC);
        entityManager.flush();
        var readSms = chatRepository.findAllSmsByChat(chat.getTitle(), pageable);
        readSms.forEach(user -> System.out.println(user.getListSms()));
        Assertions.assertThat(readSms).hasSize(3);
        System.out.println();
    }

    @Test
    void checkSave(){
        Chat chat = new Chat();
        var user = userRepository.findById(1L).get();
        chat.setLoginCreating(user.getLogin());
        chat.setTimeCreating(LocalDate.now());
        chat.setTitle("ChatUser6");
        chat.setType(Type.PRIVATE);
        var save1 = chatRepository.save(chat);
        assertNotNull(save1);
        var chatAll1 = chatRepository.findAll();
        Assertions.assertThat(chatAll1).hasSize(6);
        chatRepository.save(chat);
        var chatAll2 = chatRepository.findAll();
        Assertions.assertThat(chatAll2).hasSize(6);
//        Chat chatNew = new Chat();
//        chatNew.setTitle("newChat");
//        chatNew.setUserChats();
    }

    @Test
    void checkFindChatByTitle(){
        Chat chat = entityManager.find(Chat.class,1L);
        var chatByTitle = chatRepository.findChatByTitle(chat.getTitle());
        assertNotNull(chatByTitle);
        assertEquals(chatByTitle.get().getTitle(), "chatUser1");
    }

    @Test
    void checkFindChatById(){
        var chat = chatRepository.findChatById(1L);
        assertEquals(chat.get().getTitle(), "chatUser1");
    }

    @Test
    void checkFindAll(){
        var chatList = chatRepository.findAll();
        Assertions.assertThat(chatList).hasSize(5);
    }

    @Test
    void checkDelete(){
        Chat chat = entityManager.find(Chat.class, 1L);
        chatRepository.delete(chat);
        Chat chatNew = entityManager.find(Chat.class, 1L);
        assertNull(chatNew);
//        var chatList = chatRepository.findAll(); //Выдает ошибку
    }

    @Test
    void checkPageableFindAll(){
        var pageable = PageRequest.of(0, 2, Sort.by("id"));
        var result = chatRepository.findAllBy(pageable);
        Assertions.assertThat(result).hasSize(2);
    }

}
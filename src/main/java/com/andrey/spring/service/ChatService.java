package com.andrey.spring.service;

import com.andrey.spring.database.repository.ChatRepository;
import com.andrey.spring.database.repository.UserChatRepository;
import com.andrey.spring.database.repository.UserRepository;
import com.andrey.spring.filter.dto.ChatCreateDto;
import com.andrey.spring.database.entity.Chat;
import com.andrey.spring.mapper.CreateChatMapper;
import com.andrey.spring.mapper.CreateNameChatMapper;

import java.util.Optional;

public class ChatService {
    private static final ChatService INSTANCE = new ChatService();
    private final CreateNameChatMapper createNameChatMapper = new CreateNameChatMapper();
    private final CreateChatMapper createChatMapper = new CreateChatMapper();
    private ChatRepository chatRepository;
    private UserRepository userRepository;
    private UserChatRepository userChatRepository;

    public Optional<Chat> findChat(String login1, String login2){
        String name1 = createNameChatMapper.mapFrom(login1,login2);
        String name2 = createNameChatMapper.mapFrom(login2,login1);
//        var chat1 = chatRepository.findChatBy(name1);
//        var chat2 = chatRepository.findChatBy(name2);
//        if(!chat1.isEmpty()){
//            return chat1;
//        }
//        if(!chat2.isEmpty()){
//            return chat2;
//        }
        return null;
    }
    public void createChat(String login1, String login2){
        String title = createNameChatMapper.mapFrom(login1,login2);
        var createChatDto = new ChatCreateDto(title,login1);
        var chat = createChatMapper.mapFrom(createChatDto);
//        var optionalUser1 = userRepository.findLogin(login1);
//        var optionalUser2 = userRepository.findLogin(login2);
//        if(optionalUser1.isEmpty() && optionalUser2.isEmpty()){
//            throw new RuntimeException();
//        }

//        var user1 = optionalUser1.get();
//        var user2 = optionalUser2.get();
//        var Chat = chatRepository.save(chat);
//        userChatRepository.save(user1,user2,Chat);
//        chatDao.saveUserChat();
    }
    public static ChatService getInstance(){
        return INSTANCE;
    }

}

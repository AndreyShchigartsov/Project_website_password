package service;

import dao.ChatDao;
import dao.UserChatDao;
import dao.UserDao;
import filter.dto.CreateChatDto;
import entity.Chat;
import mapper.CreateChatMapper;
import mapper.CreateNameChatMapper;
import util.HibernateUtil;

import java.util.Optional;

public class ChatService {
    private static final ChatService INSTANCE = new ChatService();
    private final CreateNameChatMapper createNameChatMapper = new CreateNameChatMapper();
    private final CreateChatMapper createChatMapper = new CreateChatMapper();
    private final ChatDao chatDao = new ChatDao(HibernateUtil.buildSessionFactory());
    private final UserDao userDao = new UserDao(HibernateUtil.buildSessionFactory());
    private final UserChatDao userChatDao = new UserChatDao(HibernateUtil.buildSessionFactory());

    public Optional<Chat> findChat(String login1, String login2){
        String name1 = createNameChatMapper.mapFrom(login1,login2);
        String name2 = createNameChatMapper.mapFrom(login2,login1);
        var chat1 = chatDao.findChat(name1);
        var chat2 = chatDao.findChat(name2);
        if(!chat1.isEmpty()){
            return chat1;
        }
        if(!chat2.isEmpty()){
            return chat2;
        }
        return null;
    }
    public void createChat(String login1, String login2){
        String title = createNameChatMapper.mapFrom(login1,login2);
        var createChatDto = new CreateChatDto(title,login1);
        var chat = createChatMapper.mapFrom(createChatDto);
        var optionalUser1 = userDao.findLogin(login1);
        var optionalUser2 = userDao.findLogin(login2);
        if(optionalUser1.isEmpty() && optionalUser2.isEmpty()){
            throw new RuntimeException();
        }

        var user1 = optionalUser1.get();
        var user2 = optionalUser2.get();
        var Chat = chatDao.save(chat);
        userChatDao.save(user1,user2,Chat);
//        chatDao.saveUserChat();
    }
    public static ChatService getInstance(){
        return INSTANCE;
    }

}

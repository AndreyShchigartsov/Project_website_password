package service;


import dao.UserDao;
import filter.dto.CreateUserDto;
import filter.dto.ReadUserDto;
import entity.Role;
import entity.User;
import exeption.ValidationException;
import mapper.CreateUserMapper;
import mapper.ReadUserMapper;
import util.HibernateUtil;
import validator.CreateUserValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private static final UserDao userDao = new UserDao(HibernateUtil.buildSessionFactory());
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
//    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final ReadUserMapper readUserMapper = ReadUserMapper.getInstance();


    private UserService() {
    }
    public User userId(Long id){
//        var user = userDao.findId(id);
//        if(user.isEmpty()){
//            return user.get();
//        }else{
//
//        }
        return userDao.findId(id).get();
    }
    public Optional<ReadUserDto> login(String username, String password){
        return userDao.findByUsernameAndPassword(username,password)
                .map(readUserMapper::mapFrom);
//                .map(object -> readUserMapper.mapFrom(object))
    }
    public Long create(CreateUserDto userDto){
        var validator = createUserValidator.isValid(userDto);
        if(!validator.isValid()){
            throw new ValidationException(validator.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(userDto);
        userDao.save(userEntity);
        return userEntity.getId();
    }
    public List<ReadUserDto> findAllUsers(Role role){
        return userDao.findRoleAll(role)
                .stream().map(readUserMapper::mapFrom)
                .collect(Collectors.toList());
    }
    public List<ReadUserDto> findAllUsersExceptForMyself(String login){
        return userDao.findUserAll(login)
                .stream().map(readUserMapper::mapFrom)
                .collect(Collectors.toList());
    }
    public static UserService getInstance(){
        return INSTANCE;
    }
}

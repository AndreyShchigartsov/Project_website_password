package com.andrey.spring.service;


import com.andrey.spring.database.repository.UserRepository;
import com.andrey.spring.filter.dto.UserCreateDto;
import com.andrey.spring.filter.dto.UserDto;
import com.andrey.spring.filter.dto.UserReadDto;
import com.andrey.spring.database.entity.Role;
import com.andrey.spring.database.entity.User;
import com.andrey.spring.exeption.ValidationException;
import com.andrey.spring.mapper.CreateUserMapper;
import com.andrey.spring.mapper.ReadUserMapper;
import com.andrey.spring.validator.CreateUserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final CreateUserValidator createUserValidator;
    private final CreateUserMapper createUserMapper;
    private final ReadUserMapper readUserMapper;

    public User userLogin(String login){
        return userRepository.findByLogin(login)
                .orElseThrow(RuntimeException::new);
//        return new User();
    }
    public Optional<UserReadDto> login(String username, String password){
//        return USER_REPOSITORY.findByUsernameAndPassword(username,password)
//                .map(readUserMapper::mapFrom);
//                .map(object -> readUserMapper.mapFrom(object))
        return null;
    }

    @Transactional
    public UserReadDto create(UserCreateDto userDto){
        return Optional.of(userDto)
                .map(createUserMapper::mapFrom)
                .map(userRepository::save)
                .map(readUserMapper::mapFrom)
                .orElseThrow();
    }

    public List<UserReadDto> findAllUsers(Role role){
        return userRepository.findRoleAll(role)
                .stream().map(readUserMapper::mapFrom)
                .collect(Collectors.toList());
    }

    public List<UserDto> findAllUsersExceptForMyself(String login){
        return userRepository.findUserAll(login);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getLogin(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrive user: " + username));
    }


}

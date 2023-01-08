package com.andrey.spring.validator;

import com.andrey.spring.database.repository.UserRepository;
import com.andrey.spring.filter.dto.UserCreateDto;
import com.andrey.spring.database.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class CreateUserValidator implements Validator<UserCreateDto> {

    @Override
    public ValidatorResult isValid(UserCreateDto object) {
        var validationResult = new ValidatorResult();
//        if(Role.valueOf(object.getRole()) == null){
//            validationResult.add(new Error("Invalid.role" ," Role is invalid"));
//        }
        if(object.getLogin().equals("")){
            validationResult.add(new Error("Invalid.login" ," login is invalid"));
        }
        if(object.getEmail().equals("")){
            validationResult.add(new Error("Invalid.name" ," name is invalid"));
        }
        if(object.getRawPassword().equals("")){
            validationResult.add(new Error("Invalid.password" ," password is invalid"));
        }

        return validationResult;
    }
}

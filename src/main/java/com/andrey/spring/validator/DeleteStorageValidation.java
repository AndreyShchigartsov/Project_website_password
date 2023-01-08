package com.andrey.spring.validator;

import org.springframework.stereotype.Component;

@Component
public class DeleteStorageValidation implements Validator<Boolean>{

    @Override
    public ValidatorResult isValid(Boolean object) {
        var validatorResult = new ValidatorResult();
        if(!object){
            validatorResult.add(new Error("Delete invalid","delete not"));
        }
        return validatorResult;
    }
}

package com.andrey.spring.validator;

import com.andrey.spring.filter.dto.StorageCreateDto;
import org.springframework.stereotype.Component;

@Component
public class CreateStorageValidator implements Validator<StorageCreateDto> {
    @Override
    public ValidatorResult isValid(StorageCreateDto object) {
        var validatorResult = new ValidatorResult();
        if(object.getPassword().equals("")){
            validatorResult.add(new Error("Invalid password","password is empty "));
        }
        if(object.getWebsite().equals("")){
            validatorResult.add(new Error("Invalid website","website is empty "));
        }
        return validatorResult;
    }

}

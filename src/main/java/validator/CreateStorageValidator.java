package validator;

import filter.dto.CreateStorageDto;

public class CreateStorageValidator implements Validator<CreateStorageDto> {
    private static final CreateStorageValidator INSTANCE = new CreateStorageValidator();
    @Override
    public ValidatorResult isValid(CreateStorageDto object) {
        var validatorResult = new ValidatorResult();
        if(object.getPassword().equals("")){
            validatorResult.add(new Error("Invalid password","password is empty "));
        }
        if(object.getWebsite().equals("")){
            validatorResult.add(new Error("Invalid website","website is empty "));
        }
        return validatorResult;
    }

    public static CreateStorageValidator getInstance() {
        return INSTANCE;
    }
}

package validator;

public class DeleteStorageValidation implements Validator<Boolean>{
    private static final DeleteStorageValidation INSTANCE = new DeleteStorageValidation();

    @Override
    public ValidatorResult isValid(Boolean object) {
        var validatorResult = new ValidatorResult();
        if(!object){
            validatorResult.add(new Error("Delete invalid","delete not"));
        }
        return validatorResult;
    }

    public static DeleteStorageValidation getInstance() {
        return INSTANCE;
    }
}

package validator;

import dao.UserDao;
import filter.dto.CreateUserDto;
import entity.Role;
import util.HibernateUtil;

public class CreateUserValidator implements Validator<CreateUserDto> {
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();
    private static final UserDao userDao = new UserDao(HibernateUtil.buildSessionFactory());
    @Override
    public ValidatorResult isValid(CreateUserDto object) {
        var validationResult = new ValidatorResult();
        if(Role.valueOf(object.getRole()) == null){
            validationResult.add(new Error("Invalid.role" ," Role is invalid"));
        }
        if(object.getLogin().equals("")){
            validationResult.add(new Error("Invalid.login" ," login is invalid"));
        }
        if(object.getName().equals("")){
            validationResult.add(new Error("Invalid.name" ," name is invalid"));
        }
        if(object.getPassword().equals("")){
            validationResult.add(new Error("Invalid.password" ," password is invalid"));
        }
        if(!userDao.findLogin(object.getLogin()).isEmpty()){
            validationResult.add(new Error("Invalid.loginRepeat" ," login is not unique"));
        }
        return validationResult;
//        return null;
    }
    public static CreateUserValidator getInstance(){
        return INSTANCE;
    }
}

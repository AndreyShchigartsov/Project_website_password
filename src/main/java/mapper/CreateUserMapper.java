package mapper;

import filter.dto.CreateUserDto;
import entity.Role;
import entity.User;

public class CreateUserMapper implements Mapper<CreateUserDto, User>{
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    @Override
    public User mapFrom(CreateUserDto object) {
//        return new Users(object.getLogin(),object.getName(),object.getPassword(),Role.valueOf(object.getRole()));
        return User.builder()
                .login(object.getLogin())
                .email(object.getName())
                .password(object.getPassword())
                .role(Role.valueOf(object.getRole()))
                .build();
    }
    public static final CreateUserMapper getInstance(){
        return INSTANCE;
    }
}

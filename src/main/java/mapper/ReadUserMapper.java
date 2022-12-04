package mapper;

import filter.dto.ReadUserDto;
import entity.User;


public class ReadUserMapper implements Mapper<User, ReadUserDto>{

    private static final ReadUserMapper INSTANCE = new ReadUserMapper();

    @Override
    public ReadUserDto mapFrom(User object) {
//        return new ReadUserDto(object.getId(), object.getLogin(), object.getName(), object.getPassword(), object.getRole());
        return ReadUserDto.builder()
                .id(object.getId())
                .username(object.getLogin())
                .email(object.getEmail())
                .password(object.getPassword())
                .role(object.getRole())
                .build();
//        return null;
    }
    private ReadUserMapper(){

    }

    public static ReadUserMapper getInstance(){
        return INSTANCE;
    }
}

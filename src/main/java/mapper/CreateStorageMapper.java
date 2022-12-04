package mapper;

import filter.dto.CreateStorageDto;
import entity.Storage;

public class CreateStorageMapper implements Mapper<CreateStorageDto, Storage>{
    public static final CreateStorageMapper INSTANCE = new CreateStorageMapper();

    @Override
    public Storage mapFrom(CreateStorageDto object) {
//        return new Storage(object.getUserId(), object.getPassword(), object.getWebsite(), object.getComment());
        return Storage.builder()
                .user(object.getUserId())
                .password(object.getPassword())
                .website(object.getWebsite())
                .comment(object.getComment())
                .build();
    }

    public static CreateStorageMapper getInstance() {
        return INSTANCE;
    }

}

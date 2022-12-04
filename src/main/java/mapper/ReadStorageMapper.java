package mapper;

import filter.dto.ReadStorageDto;
import entity.Storage;

public class ReadStorageMapper implements Mapper<Storage,ReadStorageDto>{
    private static final ReadStorageMapper INSTANCE = new ReadStorageMapper();
    @Override
    public ReadStorageDto mapFrom(Storage object) {
//        return new ReadStorageDto(object.getId(),object.getUserId(), object.getPassword(), object.getWebsite(), object.getComment());
        return ReadStorageDto.builder()
                .id(object.getId())
                .userId(object.getId())
                .password(object.getPassword())
                .website(object.getWebsite())
                .comment(object.getComment())
                .build();
    }
    public static ReadStorageMapper getInstance(){
        return INSTANCE;
    }
}

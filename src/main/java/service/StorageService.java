package service;

import dao.StorageDao;
import filter.dto.CreateStorageDto;
import filter.dto.ReadStorageDto;
import exeption.ValidationException;
import mapper.CreateStorageMapper;
import mapper.ReadStorageMapper;
import util.HibernateUtil;
import validator.CreateStorageValidator;
import validator.DeleteStorageValidation;

import java.util.List;
import java.util.stream.Collectors;

public class StorageService {
    private static final StorageService INSTANCE = new StorageService();
    private final CreateStorageValidator createStorageValidator = CreateStorageValidator.getInstance();
    private final DeleteStorageValidation deleteStorageValidator = DeleteStorageValidation.getInstance();
    private final CreateStorageMapper createStorageMapper = CreateStorageMapper.getInstance();
    private final ReadStorageMapper readStorageMapper = ReadStorageMapper.getInstance();
    private final StorageDao storageDao = new StorageDao(HibernateUtil.buildSessionFactory());
    public Integer create(CreateStorageDto storageDto) {
        var valid = createStorageValidator.isValid(storageDto);
        if(!valid.isValid()){
            throw new ValidationException(valid.getErrors());
        }
        var storageEntity = createStorageMapper.mapFrom(storageDto);
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            storageDao.save(storageEntity);

            session.getTransaction().commit();
        }

        return storageEntity.getId();
    }

    public boolean delete(Integer id){
        var delete = storageDao.delete(id);
        var valid = deleteStorageValidator.isValid(delete);
        if(!valid.isValid()){
            throw new ValidationException(valid.getErrors());
        }
        return delete;
    }

    public List<ReadStorageDto> read(Long userId) {
        return storageDao.findAllUserId(userId).stream()
                .map(readStorageMapper::mapFrom)
//                .map(storage -> readStorageMapper.mapFrom(storage))
                .collect(Collectors.toList());
    }

    public static StorageService getInstance(){
        return INSTANCE;
    }
}

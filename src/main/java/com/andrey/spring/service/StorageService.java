package com.andrey.spring.service;

import com.andrey.spring.database.entity.QStorage;
import com.andrey.spring.database.entity.Storage;
import com.andrey.spring.database.entity.User;
import com.andrey.spring.database.querydsl.QPredicates;
import com.andrey.spring.database.repository.StorageRepository;
import com.andrey.spring.filter.dto.StorageCreateDto;
import com.andrey.spring.filter.dto.StorageFilter;
import com.andrey.spring.filter.dto.StorageReadDto;
import com.andrey.spring.exeption.ValidationException;
import com.andrey.spring.mapper.CreateStorageMapper;
import com.andrey.spring.mapper.ReadStorageMapper;
import com.andrey.spring.util.HibernateUtil;
import com.andrey.spring.validator.CreateStorageValidator;
import com.andrey.spring.validator.DeleteStorageValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.andrey.spring.database.entity.QUser.user;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StorageService {
    private final CreateStorageValidator createStorageValidator;
    private final DeleteStorageValidation deleteStorageValidator;
    private final CreateStorageMapper createStorageMapper;
    private final ReadStorageMapper readStorageMapper;
    private final StorageRepository storageRepository;

    @Transactional
    public Long create(StorageCreateDto storageDto) {
        return Optional.ofNullable(storageDto)
                .map(createStorageMapper::mapFrom)
                .map(storageRepository::save)
                .orElseThrow().getId();
    }

    @Transactional
    public void delete(Long id){
        storageRepository.delete(id);
    }

    public List<StorageReadDto> read(User userId) {
        return storageRepository.findAllUserId(userId).stream()
                .map(readStorageMapper::mapFrom)
                .collect(Collectors.toList());
    }


    public Page<StorageReadDto> read(StorageFilter filter, Pageable page) {
        var predicate = QPredicates.builder()
                .add(filter.password(), QStorage.storage.password::containsIgnoreCase)
                .add(filter.website(), QStorage.storage.website::containsIgnoreCase)
                .add(filter.comment(), QStorage.storage.comment::containsIgnoreCase)
                .build();

        return storageRepository.findAll(predicate, page)
//                .filter(x -> x.getUser() == userId)
                .map(readStorageMapper::mapFrom);
    }
}

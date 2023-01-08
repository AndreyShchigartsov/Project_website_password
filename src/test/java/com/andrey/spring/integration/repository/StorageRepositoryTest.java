package com.andrey.spring.integration.repository;

import com.andrey.spring.database.entity.Storage;
import com.andrey.spring.database.entity.User;
import com.andrey.spring.database.repository.StorageRepository;
import com.andrey.spring.integration.IntegrationTestBase;
import com.andrey.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class StorageRepositoryTest extends IntegrationTestBase {

    private static final Long ANDREY_ID = 1L;
    private final EntityManager entityManager;

    private final StorageRepository storageRepository;

    @Test
    void checkAudit(){
        var storage = storageRepository.findById(1L).get();
        storage.setComment("Hello");

        storageRepository.flush();

        var comment = storage.getComment();
        var andrey = storage.getModifiedBy();

        assertEquals(comment, "Hello");
        assertEquals(andrey, "Andrey");
    }

    @Test
    void checkFindAllPageable(){
        var pageable1 = PageRequest.of(0, 3, Sort.by("id").descending());
        var storageList1 = storageRepository.findAllBy(pageable1);
        Assertions.assertThat(storageList1).hasSize(3);

        var pageable2 = PageRequest.of(0, 10, Sort.by("id").descending());
        var storageList2 = storageRepository.findAllBy(pageable2);
        Assertions.assertThat(storageList2).hasSize(6);

        int i = 0;

        while (storageList1.hasNext()){
            i++;
            storageList1 = storageRepository.findAllBy(storageList1.nextPageable());
            Assertions.assertThat(storageList1).hasSize(3);
        }
        assertEquals(i,1);
    }

    @Test
    void checkSave(){
        var user = entityManager.find(User.class, ANDREY_ID);
        Storage storage = new Storage(user, "1234", "1234", "1234");
        var storageSave = storageRepository.save(storage);
        assertNotNull(storageSave);
        var storage1 = entityManager.find(Storage.class, storageSave.getId());
        assertNotNull(storage1);
    }

    @Test
    void checkFindById(){
        var storage = storageRepository.findById(1L);
        assertTrue(storage.isPresent());
        String google = storage.get().getWebsite();
        assertEquals(google,"Google");
    }

    @Test
    void checkFindAllUserId(){
        User user = entityManager.find(User.class, 1L);
        var storageList = storageRepository.findAllUserId(user);
        assertNotNull(storageList);
        Assertions.assertThat(storageList).hasSize(2);
    }

    @Test
    void checkFindAll(){
        var storageList = storageRepository.findAll();
        Assertions.assertThat(storageList).hasSize(6);
    }



    @Test
    void checkDelete(){
        var all = storageRepository.findAll();
        Assertions.assertThat(all).hasSize(6);
        storageRepository.delete(1L);
        var allNew = storageRepository.findAll();
        Assertions.assertThat(allNew).hasSize(5);
    }

    @Test
    void checkFindAllUserPageable(){
        User user = entityManager.find(User.class, 1L);

        var pageable1 = PageRequest.of(1, 2, Sort.by("id"));
        var storageList1 = storageRepository.findAllUserId(user, pageable1);
        Assertions.assertThat(storageList1).hasSize(0);

        var pageable2 = PageRequest.of(0, 2, Sort.by("id"));
        var storageList2 = storageRepository.findAllUserId(user, pageable2);
        Assertions.assertThat(storageList2).hasSize(2);
    }
}
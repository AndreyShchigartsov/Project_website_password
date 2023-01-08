package com.andrey.spring.integration.repository;

import com.andrey.spring.database.entity.Role;
import com.andrey.spring.database.entity.User;
import com.andrey.spring.database.repository.UserRepository;
import com.andrey.spring.filter.dto.UserDto;
import com.andrey.spring.integration.IntegrationTestBase;
import com.andrey.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class UserRepositoryTest extends IntegrationTestBase {

    private static final Long ANDREY_ID = 1L;
    private final EntityManager entityManager;
    private final UserRepository userRepository;

    @Test
//    @Commit
    void checkAudit(){
        var user = userRepository.findById(1L).get();
        user.setBirthday(user.getBirthday().plusYears(1L));

        userRepository.flush();

        var andrey = user.getModifiedBy();
        assertEquals(andrey,"Andrey");
    }

    @Test
    void checkFindAllByPageable(){
        var pageable = PageRequest.of(0, 2, Sort.by("id"));
        var page = userRepository.findAllBy(pageable);
        var userStream = page.get().map(User::getLogin).toList();
        assertEquals(userStream.get(0),"Andrey");
        Assertions.assertThat(page).hasSize(2);

        while(page.hasNext()){
            page = userRepository.findAllBy(page.nextPageable());
            Assertions.assertThat(page).hasSize(1);
        }
    }

    @Test
    void checkFindRoleAll(){
        var usersRole1 = userRepository.findRoleAll(Role.USER);
        Assertions.assertThat(usersRole1).hasSize(2);
        var usersRole2 = userRepository.findRoleAll(Role.ADMIN);
        Assertions.assertThat(usersRole2).hasSize(1);
    }

    @Test
    void checkFindRoleAllPageable(){
        var pageable = PageRequest.of(0, 2, Sort.by("id"));

        var usersRole2 = userRepository.findRoleAll(Role.ADMIN, pageable);
        assertEquals(usersRole2.get(0).getLogin(), "Arsen");
        Assertions.assertThat(usersRole2).hasSize(1);

        var usersRole1 = userRepository.findRoleAll(Role.USER, pageable);
        assertEquals(usersRole1.get(0).getLogin(), "Andrey");
        Assertions.assertThat(usersRole1).hasSize(2);
    }

    @Test
    void checkFindUsersAllNotUsersPageable(){
        User user = entityManager.find(User.class, 1L);

        var pageable1 = PageRequest.of(1, 2, Sort.by("id"));
        var pageUsers1 = userRepository.findUserAll(user.getLogin(), pageable1);
        Assertions.assertThat(pageUsers1).hasSize(0);

        var pageable2 = PageRequest.of(0, 2, Sort.by("id"));
        var pageUsers2 = userRepository.findUserAll(user.getLogin(), pageable2);
        assertEquals(pageUsers2.get(0).getLogin(),"Arsen");
        Assertions.assertThat(pageUsers2).hasSize(2);
    }

    @Test
    void checkFindAll(){
        var allUser = userRepository.findAll();
        assertFalse(allUser.isEmpty());
        Assertions.assertThat(allUser).hasSize(3);
    }

    @Test
    void checkFindUserAll(){
        var userAll = userRepository.findUserAll("Andrey");
        Assertions.assertThat(userAll).hasSize(2);
    }

    @Test
    void checkDelete(){
        var user = userRepository.findById(ANDREY_ID);
        assertTrue(user.isPresent());
        user.ifPresent(userRepository::delete);
//        userRepository.flush();//выдает ошибку
        assertTrue(userRepository.findById(ANDREY_ID).isEmpty());
    }

    @Test
    void checkUserByLoginAndPassword(){
        var user = userRepository.findUserByLoginAndPassword("Andrey", "647881");
        assertTrue(user.isPresent());
        var userEmpty = userRepository.findUserByLoginAndPassword("AndreyNull", "647881");
        assertTrue(userEmpty.isEmpty());
    }

    @Test
    void checkDeleteById(){
        var user = userRepository.findById(3L);
        assertTrue(user.isPresent());
        userRepository.deleteById(3L);
        var userDelete = userRepository.findById(3L);
        assertTrue(userDelete.isEmpty());
    }

    @Test
//    @Disabled
    void checkSave(){
        var user = new User(4L, "Artem", "Artem@artem.com", "647881", LocalDate.parse("1998-04-13"), Role.USER, null, null);
        assertTrue(userRepository.findUserByLogin("Artem").isEmpty());
        userRepository.save(user);
        userRepository.flush();
        assertTrue(userRepository.findUserByLogin("Artem").isPresent());
    }

    @Test
    void findAllBy(){
        var user = entityManager.find(User.class, 1L);
        assertNotNull(user);
        Assertions.assertThat(user.getStorages()).hasSize(2);
    }

    @Test
    void checkFindUserByLogin(){
        var user = userRepository.findUserByLogin("Andrey");
        assertTrue(user.isPresent());
        var userEmpty = userRepository.findUserByLogin("Andrey1");
        assertTrue(userEmpty.isEmpty());
    }
}
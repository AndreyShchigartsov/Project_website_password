package com.andrey.spring.database.repository;

import com.andrey.spring.database.entity.Role;
import com.andrey.spring.database.entity.User;
import com.andrey.spring.filter.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
//import lombok.*;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends
        JpaRepository<User, Long>,
        FilterUserRepository,
        RevisionRepository<User, Long, Integer>,
        QuerydslPredicateExecutor<User> {

    /*
    /Сохраняем пользователя
    */
    User save(User entity);

    /*
    /Удалить пользователя по ключу
    */
    void delete(User user);

    /*
    /Удалить пользователя по ключу
     */
    void deleteById(Long key);

    /*
   /Достаем пользователя по id
   */
//    Optional<User> findById(Long key);

    /*
    /Проверяем есть ли пользователь с таким логиным и паролем
    */
    Optional<User> findUserByLoginAndPassword(String login, String password);

    /*
    /Найти пользователя по логину
    */
    Optional<User> findUserByLogin(String login);

    /*
    /Достать всех ползователей кроме текущего
     */
    @Query(value = "select login, email, birth_day birthDate from users where login != :login",
    nativeQuery = true)
    List<UserDto> findUserAll(String login);

    /*
    /Достать страницу ползователей кроме текущего
   */
    @Query(value = "select login, email, birth_day birthDate from users where login != :login",
            nativeQuery = true)
    List<UserDto> findUserAll(String login, Pageable pageable);

    /*
    /Достать всех ползователей
    */
    List<User> findAll();

    /*
    /Достать страницу ползователей
    */
    Page<User> findAllBy(Pageable pageable);

    /*
    /Достать всех пользователей по роли
    */
    @Query("select u from User u where u.role = :role")
    List<User> findRoleAll(Role role);

    /*
    /Достать страницу пользователей по роли
     */
    @Query("select u from User u where u.role = :role")
    List<User> findRoleAll(Role role, Pageable pageable);

    Optional<User> findByLogin(String username);

//    public boolean update(User value);
}


//    public User save(User entity) {
//        var session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        session.persist(entity);
//        session.getTransaction().commit();
//        return entity;
//    }

//    public Optional<User> findByUsernameAndPassword(String login, String password) {
//        var session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        var user = Optional.ofNullable(session.createQuery("select u from User u where u.login = :login and u.password = :password", User.class)
//                .setParameter("login", login)
//                .setParameter("password", password)
//                .uniqueResult());
//        session.getTransaction().commit();
//        return user;
//    }

//    public Optional<User> findId(Long key) {
//        var session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        var user = Optional.ofNullable(session.get(User.class,key));
//        session.getTransaction().commit();
//        return user;
//    }

//    public Optional<User> findLogin(String login) {
//        var session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        var user = Optional.ofNullable(session.createQuery("select u from User u where u.login = :login",User.class)
//                .setParameter("login",login)
//                .uniqueResult());
//        session.getTransaction().commit();
//        return user;
//    }

//    public List<User> findUserAll(String login){
//        var session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        var users =  session.createQuery("select u from User u where u.login != :login", User.class)
//                .setParameter("login", login)
//                .list();
//        session.getTransaction().commit();
//        return users;
//    }

//    public List<User> findAll() {
//        var session = sessionFactory.getCurrentSession();
//        return session.createQuery("select u from User u", User.class).list();
//    }

//    public List<User> findRoleAll(Role role) {
//        var session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        var user = session.createQuery("select u from User u where role = :role", User.class)
//                .setParameter("role", role)
//                .list();
//        session.getTransaction().commit();
//        return user;
//    }

//    public boolean delete(Long key) {
//        var session = sessionFactory.getCurrentSession();
////        var user = session.createQuery("delete from User u where u.id = :id",User.class)
////                .setParameter("id",key)
////                .executeUpdate();
//        var user = session.get(User.class,key);
//        session.remove(user);
//        return true;
//    }

//    public static UserDao getInstance() {
//        return INSTANCE;
//    }

//    private Users findUser(ResultSet resultSet) throws SQLException {
//        return new Users(
//                resultSet.getObject("id", Integer.class),
//                resultSet.getObject("login",String.class),
//                resultSet.getObject("email", String.class),
//                resultSet.getObject("password",String.class),
//                Role.valueOf(resultSet.getObject("role", String.class))
//        );
//    }

//        try (Connection connection = ConnectionManager.get();
//            var prepareStatement = connection.prepareStatement(DELETE_ID_SQL)) {
//            prepareStatement.setObject(1,key);
//            return prepareStatement.executeUpdate() > 0;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        try (Connection connection = ConnectionManager.get();
//             var prepareStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
//            prepareStatement.setObject(1, entity.getLogin());
//            prepareStatement.setObject(2, entity.getEmail());
//            prepareStatement.setObject(3, entity.getPassword());
//            prepareStatement.setObject(4, entity.getRole().name());
//
//            var result = prepareStatement.executeUpdate();
//
//            var generatedKey = prepareStatement.getGeneratedKeys();
//            generatedKey.next();
//            entity.setId(generatedKey.getObject("id",Integer.class));
//
//            return entity;
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        try (Connection connection = ConnectionManager.get();
//            var prepareStatement = connection.prepareStatement(UPDATE_SQL)) {
//            prepareStatement.setObject(1,value.getLogin());
//            prepareStatement.setObject(2,value.getEmail());
//            prepareStatement.setObject(3,value.getPassword());
//            prepareStatement.setObject(4,value.getRole().name());
//            prepareStatement.setObject(5,value.getId());
//            prepareStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        try (Connection connection = ConnectionManager.get();
//             var prepareStatement = connection.prepareStatement(FIND_ALL_ROLE_SQL)) {
//            prepareStatement.setObject(1,role.name());
//            var resultSet = prepareStatement.executeQuery();
//            List<Users> userList = new ArrayList<>();
//            while (resultSet.next()){
//                userList.add(findUser(resultSet));
//            }
//            for(Users user : userList){
//                System.out.println(user.getName());
//            }
//            return userList;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        try (Connection connection = ConnectionManager.get();
//             var prepareStatement = connection.prepareStatement(FIND_ALL_SQL)) {
//            var resultSet = prepareStatement.executeQuery();
//            List<Users> userList = new ArrayList<>();
//            while (resultSet.next()){
//                userList.add(findUser(resultSet));
//            }
////            for(Users user : userList){
////                System.out.println(user.getName());
////            }
//            return userList;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        try (Connection connection = ConnectionManager.get();
//             var prepareStatement = connection.prepareStatement(FIND_LOGIN_SQL)) {
//            prepareStatement.setObject(1,value);
//            var resultSet = prepareStatement.executeQuery();
//            Users user = null;
//            if(resultSet.next()){
//                user = findUser(resultSet);
//            }
//            return Optional.ofNullable(user);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        try (Connection connection = ConnectionManager.get();
//             var prepareStatement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD_SQL)) {
//            prepareStatement.setObject(1, login);
//            prepareStatement.setObject(2, password);
//            System.out.println(prepareStatement);
//            var resultSet = prepareStatement.executeQuery();
////            System.out.println(resultSet);
//            Users user = null;
//            if(resultSet.next()){
//                user = findUser(resultSet);
//            }
//            return Optional.ofNullable(user);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        try (Connection connection = ConnectionManager.get();
//             var prepareStatement = connection.prepareStatement(FIND_ID_SQL)) {
//            prepareStatement.setObject(1,key);
//            var resultSet = prepareStatement.executeQuery();
//            Users user = null;
//            if(resultSet.next()){
//                user = findUser(resultSet);
//            }
//            return Optional.ofNullable(user);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
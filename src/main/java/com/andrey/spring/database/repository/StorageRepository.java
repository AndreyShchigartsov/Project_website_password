package com.andrey.spring.database.repository;

import com.andrey.spring.database.entity.Storage;
import com.andrey.spring.database.entity.User;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;
public interface StorageRepository extends
        JpaRepository<Storage,Integer>,
        QuerydslPredicateExecutor<Storage> {

    /*
    /Сохранить пароль
    */
    Storage save(Storage entity);

    /*
    /Достать пароль по Id
    */
    Optional<Storage> findById(Long key);

    /*
    /Достать все пароли данного пользователя
    */
    @Query("select s from Storage s where s.user = :user")
    List<Storage> findAllUserId(User user);

    /*
    /Достать лист паролей данного пользователя
    */
    @Query("select s from Storage s where s.user = :user")
    List<Storage> findAllUserId(User user, Pageable pageable);

    /*
    /Достать лист паролей данного пользователя в офильтрованном виде
    */
//    @Query("select s from Storage s where s.user = :user")
//    Page<Storage> findAllUserId(User user, Predicate predicate, Pageable pageable);

    /*
    /Достать все пароли
    */
    List<Storage> findAll();

    /*
    /Достать лист паролей
    */
    Page<Storage> findAllBy(Pageable pageable);


//    public boolean update(Storage value);

    /*
    /Удалить пароль по Id
    */
    @Query("delete from Storage where id = :id")
//    @Modifying(clearAutomatically = true)
    @Modifying(flushAutomatically = true)
    void delete(Long id);
}




//    public boolean delete(Integer id) {
//        var session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        var storage = session.get(Storage.class,id);
//        session.remove(storage);
//        session.getTransaction().commit();
//        return false;
//    }

//    public List<Storage> findAll() {
//        var session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        var listStorage = session.createQuery("select s from Storage s", Storage.class).list();
//        session.getTransaction().commit();
//        return listStorage;
//    }

//    public List<Storage> findAllUserId(Long userId) {
//        var session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        var list = session.createQuery("select s from Storage s where s.user.id = :userid", Storage.class)
//                .setParameter("userid", userId)
//                .list();
//        session.getTransaction().commit();
//        return list;
//    }

//    public Optional<Storage> findId(Integer key) {
//        return Optional.empty();
//    }

//    /*
//       /Сохранить пароль
//        */
//    @Override
//    public Storage save(Storage entity) {
//        var session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        session.persist(entity);
//        session.getTransaction().commit();
//        return entity;
//    }

//    public static StorageDao getInstance() {
//        return INSTANCE;
//    }

//    public static final StorageDao INSTANCE = new StorageDao();
//        StorageDao storageDao = new StorageDao();
//        var entities = storageDao.findAllUserId(1);
//        for(Storage entity : entities){
//            System.out.println(entity.getWebsite());
//        }

//        try (Connection connection = ConnectionManager.get();
//             var prepareStatement = connection.prepareStatement(DELETE_ID_SQL)) {
//            prepareStatement.setObject(1,id);
//            return prepareStatement.executeUpdate() > 0;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try (Connection connection = ConnectionManager.get();
//             var prepareStatement = connection.prepareStatement(FIND_ALL_USER_ID_SQL)) {
//            prepareStatement.setObject(1, userId);
//            var resultSet = prepareStatement.executeQuery();
//
//            List<Storage> storageList = new ArrayList<>();
//            while(resultSet.next()){
//                storageList.add(findStorage(resultSet));
//            }
//            return storageList;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try (var connection = ConnectionManager.get();
//            var prepareStatement = connection.prepareStatement(SAVE_PASSWORD_SQL, Statement.RETURN_GENERATED_KEYS)) {
//            prepareStatement.setObject(1,entity.getUserId());
//            prepareStatement.setObject(2,entity.getPassword());
//            prepareStatement.setObject(3,entity.getWebsite());
//            prepareStatement.setObject(4,entity.getComment());
////            System.out.println(prepareStatement);
//            var result = prepareStatement.executeUpdate();
//
//            var key = prepareStatement.getGeneratedKeys();
//            if(key.next()){
//                entity.setId(key.getObject("id",Integer.class));
//            }
//            return entity;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        try (Connection connection = ConnectionManager.get();
//             var prepareStatement = connection.prepareStatement(FIND_ALL_SQL)) {
//            var resultSet = prepareStatement.executeQuery();
//            List<Storage> userList = new ArrayList<>();
//            while (resultSet.next()){
//                userList.add(findStorage(resultSet));
//            }
////            for(Storage user : userList){
////                System.out.println(user.getUserId());
////            }
//            return userList;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }


//    private Storage findStorage(ResultSet resultSet) throws SQLException {
//        return new Storage(
//                resultSet.getObject("id", Integer.class),
//                resultSet.getObject("userId",Integer.class),
//                resultSet.getObject("password", String.class),
//                resultSet.getObject("website",String.class),
//                resultSet.getObject("comment", String.class)
//        );
//    }

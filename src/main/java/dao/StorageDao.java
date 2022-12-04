package dao;

import entity.Role;
import entity.Storage;
import entity.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class StorageDao implements Dao<Integer, Storage>{
    private final SessionFactory sessionFactory;

    public static void main(String[] args) {
//        var user = User.builder()
//                .login("3And11")
//                .email("Anton")
//                .password("12134")
//                .birthday(LocalDate.of(2000,1,19))
//                .role(Role.USER)
//                .build();

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            var session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            StorageDao storageDao = new StorageDao(sessionFactory);
//            var user = session.get(User.class,1);
//            var Storage = entity.Storage.builder()
//                    .user(user)
//                    .password("Password")
//                    .website("Website")
//                    .comment("Comment")
//                    .build();
//            storageDao.save(Storage);
//            var userAll = storageDao.findAll();
            storageDao.delete(4);
            session.getTransaction().commit();
        }
    }
//public static final String SAVE_PASSWORD_SQL = "INSERT INTO storage(userId, password, website, comment) VALUES (?,?,?,?)";
    /*
    /Сохранить пароль
     */
    @Override
    public Storage save(Storage entity) {
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public Optional<Storage> findId(Integer key) {
        return Optional.empty();
    }

//private static final String FIND_ALL_USER_ID_SQL = "SELECT id, userId, password, website, comment FROM storage WHERE userId = ?";
    /*
    /Достать пароли для пользователя
    */
    public List<Storage> findAllUserId(Long userId) {
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var list = session.createQuery("select s from Storage s where s.user.id = :userid", Storage.class)
                .setParameter("userid", userId)
                .list();
        session.getTransaction().commit();
        return list;
    }
//для администратора
//private static final String FIND_ALL_SQL = "SELECT id, userId, password, website, comment FROM storage";
    /*
    /Достать все пароли
     */
    @Override
    public List<Storage> findAll() {
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var listStorage = session.createQuery("select s from Storage s", Storage.class).list();
        session.getTransaction().commit();
        return listStorage;
    }

    @Override
    public boolean update(Storage value) {
        return false;
    }
//private static final String DELETE_ID_SQL = "DELETE FROM storage WHERE id = ?";
    /*
    /Удаляет пароль по Id
    */
    @Override
    public boolean delete(Integer id) {
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var storage = session.get(Storage.class,id);
        session.remove(storage);
        session.getTransaction().commit();
        return false;
    }

}
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

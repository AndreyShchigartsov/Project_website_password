package dao;

import entity.Role;
import entity.User;
//import lombok.*;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class UserDao implements Dao<Long, User>{
    private final SessionFactory sessionFactory;
//    private static final UserDao INSTANCE = new UserDao();
    private static final String UPDATE_SQL = """
            UPDATE users SET
            username = ?,
            lastname = ?,
            password = ?,
            role = ?
            WHERE id = ?
            """;
    public static void main(String[] args) {
//        var user1 = User.builder()
//                .login("Test1")
//                .email("Anton")
//                .password("12134")
//                .birthday(LocalDate.of(2000,1,19))
//                .role(Role.USER)
//                .build();
//        var user2 = User.builder()
//                .login("Test2")
//                .email("Anton")
//                .password("12134")
//                .birthday(LocalDate.of(2000,1,19))
//                .role(Role.USER)
//                .build();
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()){
            var session = sessionFactory.getCurrentSession();
           session.beginTransaction();
            UserDao userDao = new UserDao(sessionFactory);
//            var user1 = userDao.findLogin("user").get();
//            var user2 = session.get(User.class,4);
//            System.out.println(user1);
//            System.out.println(user2);
            session.getTransaction().commit();
        }
    }

//    private static final String SAVE_SQL = "INSERT INTO users(login, name, password, birth_day, role) VALUES (?,?,?,?)";
    /*
    /Сохраняем пользователя
    */
    @Override
    public User save(User entity) {
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        return entity;
    }

    // private static final String GET_BY_EMAIL_AND_PASSWORD_SQL = "SELECT id, login, name, password, birth_day, role FROM users WHERE login = ? AND password = ?";
    /*
    /Проверяем есть ли пользователь с таким логиным и паролем
    */
    public Optional<User> findByUsernameAndPassword(String login, String password) {
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var user = Optional.ofNullable(session.createQuery("select u from User u where u.login = :login and u.password = :password", User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .uniqueResult());
        session.getTransaction().commit();
        return user;
    }

//private static final String FIND_ID_SQL = "SELECT id, login, name, password, birth_day, role FROM users WHERE id = ?";
    /*
    /Достаем пользователя по id
    */
    @Override
    public Optional<User> findId(Long key) {
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var user = Optional.ofNullable(session.get(User.class,key));
        session.getTransaction().commit();
        return user;
    }

// private static final String FIND_LOGIN_SQL = "SELECT id, login, email, password, birth_day, role FROM users WHERE login = ?";
    /*
    /Найти пользователя по логину
    */
    public Optional<User> findLogin(String login) {
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var user = Optional.ofNullable(session.createQuery("select u from User u where u.login = :login",User.class)
                .setParameter("login",login)
                .uniqueResult());
        session.getTransaction().commit();
        return user;
    }
    /*
    /Достать всех ползователей кроме текущего
     */
    public List<User> findUserAll(String login){
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var users =  session.createQuery("select u from User u where u.login != :login", User.class)
                .setParameter("login", login)
                .list();
        session.getTransaction().commit();
        return users;
    }
    //private static final String FIND_ALL_SQL = "SELECT id, login, email, password, birth_day ,role FROM users";
    /*
    /Достать всех ползователей
     */
    @Override
    public List<User> findAll() {
        var session = sessionFactory.getCurrentSession();
        return session.createQuery("select u from User u", User.class).list();
    }

//private static final String FIND_ALL_ROLE_SQL = "SELECT id, login, name, password, birth_day, role FROM users WHERE role = ?";
    /*
    /Достать пользователей по ролям
    */
    public List<User> findRoleAll(Role role) {
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var user = session.createQuery("select u from User u where role = :role", User.class)
                .setParameter("role", role)
                .list();
        session.getTransaction().commit();
        return user;
    }


//private static final String DELETE_ID_SQL = "DELETE FROM users WHERE id = ?";
    /*
    /Удалить пользователя по ключу
     */
    @Override
    public boolean delete(Long key) {
        var session = sessionFactory.getCurrentSession();
//        var user = session.createQuery("delete from User u where u.id = :id",User.class)
//                .setParameter("id",key)
//                .executeUpdate();
        var user = session.get(User.class,key);
        session.remove(user);
        return true;
    }

    @Override
    public boolean update(User value) {
        return false;
    }
}



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
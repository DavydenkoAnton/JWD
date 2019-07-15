package by.davydenko.petbook.dao;

import by.davydenko.petbook.controller.PetBookServlet;
import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.dao.pool.MyConnectionPool;
import by.davydenko.petbook.dao.pool.PetBookConnection;
import by.davydenko.petbook.entity.Pet;
import by.davydenko.petbook.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoMySqlImpl implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoMySqlImpl.class.getName());
    String url = "jdbc:mysql://localhost:3306/petbook?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    @Override
    public List<User> readUsers() throws DaoMySqlException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT name,email FROM petbook.users ORDER BY id";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, "root", "admin");
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoMySqlException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DaoMySqlException(e);
        }
        return users;
    }

    @Override
    public Integer create(User user) {

        String sql = "INSERT INTO petbook.users" +
                " (`login`, `password`, `name`, `email`, `phoneNumber`, `age`)" +
                " VALUES (?, ?, ?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement st = null;
        MyConnectionPool myConnectionPool = MyConnectionPool.getInstance();

        try {
            con = myConnectionPool.takeConnection();
            con.setAutoCommit(false);

            st = con.prepareStatement(sql);

            st.setString(1, user.getLogin());
            st.setString(2, user.getPassword());
            st.setString(3, user.getName());
            st.setString(4, user.getEmail());
            st.setInt(5, user.getPhoneNumber());
            st.setInt(6, user.getAge());

            if(st.executeUpdate() > 0) {
                con.commit();
                return 0;
            }

        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error("ConnectionPoolException or SQLException in SQLUserDAO/addUser()", e);
            try {
                con.rollback();
            } catch (SQLException e1) {
                LOGGER.error("SQLException in catch-block SQLUserDAO/addUser()", e1);
            }
            myConnectionPool.closeConnection(con, st);
        }

        return 1;

















//        System.out.println("daomysql");
//        String sql = "INSERT INTO petbook.users" +
//                " (`login`, `password`, `name`, `email`, `phoneNumber`, `age`)" +
//                " VALUES (?, ?, ?, ?, ?, ?)";
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        Connection connection = null;
//
//        try {
//            connection = ConnectionPool.getInstance().getConnection();
//        } catch (ConnectionPoolException e) {
//            e.printStackTrace();
//        }
//
//       try {
//            connection.setAutoCommit(false);
//        } catch (SQLException e) {
//            //logger.error("It is impossible to turn off autocommiting for database connection", e);
//            e.printStackTrace();
//        }
//
//        try {
//            statement = connection.prepareStatement(sql);
//
//            statement.setString(1, user.getLogin());
//            statement.setString(2, user.getPassword());
//            statement.setString(3, user.getName());
//            statement.setString(4, user.getEmail());
//            statement.setInt(5, user.getPhoneNumber());
//            statement.setInt(6, user.getAge());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//
//            connection.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return 1;
    }

    @Override
    public User read(Integer id) throws DaoMySqlException {
        User user = new User();
        String sql = "SELECT name,email FROM petbook.users WHERE id=? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, "root", "admin");
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoMySqlException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            while (resultSet.next()) {
                if (resultSet.getInt(id) == id) {
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    break;
                }
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DaoMySqlException(e);
        }
        return user;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Integer identity) {

    }


    private void connection() throws DaoMySqlException {

    }


}

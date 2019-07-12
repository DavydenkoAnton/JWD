package by.davydenko.petbook.dao;

import by.davydenko.petbook.controller.PetBookServlet;
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
        String sql = "SELECT `id`, `login`, `password` FROM users ORDER BY `login`";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
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
                user.setId(resultSet.getInt("id"));
                //user.setName(resultSet.getString("name"));
               // user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setLogin(resultSet.getString("login"));
               // user.setPhoneNumber(resultSet.getInt("phoneNumber"));
                //user.setAge(resultSet.getInt("age"));
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
    public Integer create(User entity) {
        String sql = "INSERT INTO `users` (`login`, `password`, `role`) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        return null;
    }

    @Override
    public User read(Integer identity) {
        return null;
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

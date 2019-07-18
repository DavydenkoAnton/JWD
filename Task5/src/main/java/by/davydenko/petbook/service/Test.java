package by.davydenko.petbook.service;

import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static final String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/petbook?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "admin";
    public static final int DB_POOL_START_SIZE = 1;
    public static final int DB_POOL_MAX_SIZE = 10;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 50;
    private static final String COMMAND = "command";

    private static Logger logger = LogManager.getLogger(Test.class);

    public static void main(String[] args) {


        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try {
            connectionPool.init();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            int temp = 100 + i;
            threads.add(new Thread(() -> {
                User user = new User();
                UserServiceImpl service = new UserServiceImpl();
                user.setLogin(String.valueOf(temp));
                user.setPassword(String.valueOf(temp));
                user.setName(String.valueOf(temp));
                user.setEmail(String.valueOf(temp));
                user.setPhoneNumber(temp);
                user.setAge(temp);
                service.addUser(user);
            }));
        }
        threads.forEach(Thread::start);


    }

}

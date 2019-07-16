package by.davydenko.petbook.service;

import by.davydenko.petbook.dao.DaoMySqlException;
import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import by.davydenko.petbook.dao.pool.MyConnectionPool;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.UserServiceImpl;


import org.junit.Test;

public class UserServiceImplTest {


    ReentrantLock locker = new ReentrantLock();
    public static final String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/petbook?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "admin";
    public static final int DB_POOL_START_SIZE = 1;
    public static final int DB_POOL_MAX_SIZE = 5;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 1000;


    @Test
    public void ConnectionPoolTest() {

        MyConnectionPool myConnectionPool = MyConnectionPool.getInstance();
        try {
            myConnectionPool.initPoolData();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        ReentrantLock lock = new ReentrantLock();


        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int temp = 100 + 10;
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(temp);
                    User user = new User();
                    UserServiceImpl service = new UserServiceImpl();
                    System.out.println(111);
                    user.setLogin(String.valueOf(temp));
                    user.setPassword(String.valueOf(temp));
                    user.setName(String.valueOf(temp));
                    user.setEmail(String.valueOf(temp));
                    user.setPhoneNumber(temp);
                    user.setAge(temp);
                    System.out.println(112);
                    service.addUser(user);
                    System.out.println(120);
                }
            }));
        }
        threads.forEach(Thread::start);

    }

}

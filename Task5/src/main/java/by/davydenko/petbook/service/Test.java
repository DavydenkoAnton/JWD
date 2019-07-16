package by.davydenko.petbook.service;

import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.dao.pool.MyConnectionPool;
import by.davydenko.petbook.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

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


        try {
            ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        MyConnectionPool myConnectionPool = MyConnectionPool.getInstance();
        myConnectionPool.init();
        ReentrantLock lock = new ReentrantLock();


        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int temp = 100 + i;
            threads.add(new Thread(() -> {
                logger.error("run");
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
            }));
        }
        threads.forEach(Thread::start);


    }

}

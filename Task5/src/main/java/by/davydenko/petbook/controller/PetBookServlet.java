package by.davydenko.petbook.controller;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.dao.DaoMySqlException;
import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@MultipartConfig
public class PetBookServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(PetBookServlet.class.getName());

    public static final String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/petbook?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "admin";
    public static final int DB_POOL_START_SIZE = 10;
    public static final int DB_POOL_MAX_SIZE = 1000;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 50;

    private List<User> users;
    private Command command;
    private final CommandProvider commandProvider = new CommandProvider();


    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
        } catch (ConnectionPoolException e) {
            destroy();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestParameter = request.getParameter("action");


        switch (requestParameter) {
            case "get_users":
                command = commandProvider.getCommand(requestParameter);
                try {
                    users = command.getList();
                } catch (DaoMySqlException e) {
                    e.printStackTrace();
                }
                request.setAttribute("users", users);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;

//                default:
//                    users=appController.getUsers();
//                    request.setAttribute("users", users);
//                    request.getRequestDispatcher("/index.jsp").forward(request, response);
//                    break;
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String fName = request.getParameter("fname");
        int x = 0;
    }
}

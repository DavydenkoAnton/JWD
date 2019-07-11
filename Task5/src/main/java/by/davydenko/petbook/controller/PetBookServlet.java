package by.davydenko.petbook.controller;

import by.davydenko.petbook.controller.command.Command;
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
public class PetBookServlet extends HttpServlet implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(PetBookServlet.class.getName());

    public static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/library_db?useUnicode=true&characterEncoding=UTF-8";
    public static final String DB_USER = "library_user";
    public static final String DB_PASSWORD = "library_password";
    public static final int DB_POOL_START_SIZE = 10;
    public static final int DB_POOL_MAX_SIZE = 1000;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;

    private List<User> users;
    private Command command;
    private final CommandProvider commandProvider = new CommandProvider();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            //ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
        } catch (Exception e) {
            LOGGER.error(e);
            destroy();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        //users=appController.getUsers();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestParameter = request.getParameter("action");


            switch (requestParameter) {
                case "get_users":
                    command = commandProvider.getCommand(requestParameter);
                    users = command.getUsers(requestParameter);
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

        if ("submit".equals(action)) {

        }

    }
}

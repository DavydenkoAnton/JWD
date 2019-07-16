package by.davydenko.petbook.controller;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@MultipartConfig
public class PetBookServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(PetBookServlet.class);

    public static final String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/petbook?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "admin";
    public static final int DB_POOL_START_SIZE = 1;
    public static final int DB_POOL_MAX_SIZE = 10;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 50;
    private static final String COMMAND = "command";

    @Override
    public void init(ServletConfig config) {
        try {
            ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
        } catch (ConnectionPoolException e) {
            logger.error("Cannot start application" + e);
            destroy();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        process(request, response);
    }


    private void process(HttpServletRequest request, HttpServletResponse response) {

        String commandName = request.getParameter(COMMAND);
        Command command = CommandProvider.getInstance().getCommand(commandName);

        try {
            command.execute(request, response);
        } catch (Exception e) {
            logger.error("Exception in Controller" + e);
            //response.sendError(ERROR_NUMBER_500);
        }

    }
}

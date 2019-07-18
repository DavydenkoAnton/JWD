package by.davydenko.petbook.controller;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.dao.DaoMySqlException;
import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;


@MultipartConfig
public class PetBookServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(PetBookServlet.class);
    private static final String COMMAND = "command";
    private static final int ERROR_500 = 500;
    private Command command;


    @Override
    public void init(ServletConfig config) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            connectionPool.init();
        } catch (ConnectionPoolException e) {
            logger.error("Cannot start application", e);
            destroy();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String commandName = request.getParameter(COMMAND);

        command = CommandProvider.getInstance().getCommand(commandName);
        try {
            command.execute(request, response);
        } catch (DaoMySqlException e) {
            logger.error("Wrong get command" + e);
            try {
                response.sendError(ERROR_500);
            } catch (IOException ex) {
                logger.error(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        int x=1;
        logger.error("_____DO POST _______");
        String commandName = request.getParameter(COMMAND);

        command = CommandProvider.getInstance().getCommand(commandName);
        try {
            command.execute(request, response);
        } catch (DaoMySqlException e) {
            logger.error("Wrong post command" + e);
            try {
                response.sendError(ERROR_500);
            } catch (IOException ex) {
                logger.error(e);
            }
        }
    }


}

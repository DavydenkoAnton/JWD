package by.davydenko.petbook.controller;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) {
        String commandName = (String) request.getAttribute(COMMAND);
        command = CommandProvider.getInstance().getCommand(commandName);
        command.execute(request, response);
    }
}

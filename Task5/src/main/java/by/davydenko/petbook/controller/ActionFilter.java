package by.davydenko.petbook.controller;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * ActionFilter реализует интерфейс Filter
 */
@WebFilter("/ActionFilter")
public final class ActionFilter implements Filter {

    private static Logger logger = LogManager.getLogger(ActionFilter.class);

    private CommandProvider commandProvider;

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig fConfig) {
        filterConfig = fConfig;
        commandProvider = CommandProvider.getInstance();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {

        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String command = commandProvider.getCommandNameFromUri(httpRequest);
            httpRequest.setAttribute("command", command);

            try {
                filterChain.doFilter(request, response);
            } catch (IOException | ServletException e) {
                logger.error(e);
            }
        } else {
            logger.error("It is impossible to use HTTP filter");
            //TODO forward to error page
        }
    }

    @Override
    public void destroy() {

    }


}

package by.davydenko.petbook.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * SecurityUriFilter implements Filter
 * @see Filter
 */
@WebFilter("/SecurityUriFilter")
public class SecurityUriFilter implements Filter {

    private static Logger logger = LogManager.getLogger(SecurityUriFilter.class);

    private FilterConfig filterConfig;
    private final static String LOGIN_PAGE_COMMAND = "login";
    private final static String COMMAND = "command";
    private final static String FILTER_CONFIG_INIT = "security";
    private final static String FILTER_CONFIG_PARAMETER = "true";
    private String commandName;
    private static List<String> adminCommands;
    private static List<String> userAuthorizedCommands;
    private static List<String> userNotAuthorizedCommands;
    private HttpSession httpSession;
    private HttpServletRequest httpRequest;

    static {
        adminCommands = new ArrayList<>();
        adminCommands.add("admin");
        adminCommands.add("deleteUser");

        userAuthorizedCommands = new ArrayList<>();
        userAuthorizedCommands.add("user");
        userAuthorizedCommands.add("logout");
        userAuthorizedCommands.add("message");
        userAuthorizedCommands.add("sendMessage");

        userNotAuthorizedCommands = new ArrayList<>();
        userNotAuthorizedCommands.add("main");
        userNotAuthorizedCommands.add("login");
        userNotAuthorizedCommands.add("register");
        userNotAuthorizedCommands.add("registration");
        userNotAuthorizedCommands.add("login");
        userNotAuthorizedCommands.add("loginUser");
        userNotAuthorizedCommands.add("locale");
        userNotAuthorizedCommands.add("pagingUsersNext");
        userNotAuthorizedCommands.add("pagingUsersPrev");
    }

    /**
     * Конструктор по умолчанию
     */
    public SecurityUriFilter() {
    }

    /**
     * Method filter uri request.
     * If user not authorized, filter redirect him to login page.
     *
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {

        if (filterConfig.getInitParameter(FILTER_CONFIG_INIT).equals(FILTER_CONFIG_PARAMETER)) {
            if (request instanceof HttpServletRequest) {

                httpRequest = (HttpServletRequest) request;
                commandName = getCommandNameFromUri(httpRequest);
                httpSession = httpRequest.getSession();


                if (adminCommands.contains(commandName) && isAdmin()) {
                    httpRequest.setAttribute(COMMAND, commandName);
                }else if (userAuthorizedCommands.contains(commandName) && (isUser()||isAdmin())) {
                    httpRequest.setAttribute(COMMAND, commandName);
                } else if(userNotAuthorizedCommands.contains(commandName)) {
                    httpRequest.setAttribute(COMMAND, commandName);
                } else {
                    httpRequest.setAttribute(COMMAND, LOGIN_PAGE_COMMAND);
                }


                try {
                    filterChain.doFilter(request, response);
                } catch (IOException e) {
                    logger.error("IOException", e);
                } catch (ServletException e) {
                    logger.error("ServletException", e);
                }
            } else {
                logger.error("It is impossible to use HTTP filter");
            }
        }
    }


    /**
     * Method init FilterConfig of SecurityUriFilter.class
     *
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Метод освобождения ресурсов
     *
     * @see Filter#destroy()
     */
    @Override
    public void destroy() {
        filterConfig = null;
    }

    private boolean isAdmin() {
        String role;
        boolean check = false;
        role = (String) httpSession.getAttribute("role");
        if (role != null) {
            if (role.equals("admin")) {
                check = true;
            }
        }
        return check;
    }

    private boolean isUser() {
        String role;
        boolean check = false;
        role = (String) httpSession.getAttribute("role");
        if (role != null) {
            if (role.equals("user")) {
                check = true;
            }
        }
        return check;
    }


    private String getCommandNameFromUri(HttpServletRequest httpRequest) {
        String command = null;
        // Раскладываем адрес на составляющие
        String[] list = httpRequest.getRequestURI().split("/");

        if (list[list.length - 1].indexOf(".html") > 0) {
            command = list[list.length - 1];
        }
        command = command.replace(".html", "");
        return command;
    }

}

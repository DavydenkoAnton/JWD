package by.davydenko.petbook.controller;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * AuthorizedUriFilter реализует интерфейс Filter
 */
@WebFilter("/AuthorizedUriFilter")
public class AuthorizedUriFilter implements Filter {

    private static Logger logger = LogManager.getLogger(AuthorizedUriFilter.class);

    private FilterConfig filterConfig;
    private List<String> pages;
    private String loginPage;
    private String registerPage;
    private Command command;
    private UserServiceImpl userService;

    /**
     * Конструктор по умолчанию
     */
    public AuthorizedUriFilter() {
    }

    /**
     * Метод инициализации фильтра
     *
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig fConfig) {
        filterConfig = fConfig;
        userService = new UserServiceImpl();
        pages = new ArrayList<>();
        pages.add("main.html");
        loginPage = "login.html";
        registerPage = "registration.html";
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


    /**
     * Метод фильтрации
     *
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)  {
        String commandName=null;
        String page = null;


//
//        if (request instanceof HttpServletRequest) {
//            // Если фильтр активной, то выполнить проверку
//            if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {
//                HttpServletRequest req = (HttpServletRequest) request;
//                HttpServletResponse resp = (HttpServletResponse) response;
//                HttpSession session = req.getSession();
//                String authorized = (String) session.getAttribute("authorized");
//
//                // Раскладываем адрес на составляющие
//                String[] list = req.getRequestURI().split("/");
//
//                // Извлекаем наименование страницы
//                if (list[list.length - 1].indexOf(".html") > 0) {
//                    page = list[list.length - 1];
//                }
//
//                if (page != null) {
//                    // if url is correct and user is authorized
//                    if (pages.contains(page)&&authorized!=null ) {
//                        command = CommandProvider.getInstance().getCommand(page);
//                        command.execute(req, resp);
//                    }
//                    // if user is not authorized
//                    // go to login page
//                    if (page.equalsIgnoreCase(loginPage)&&authorized==null) {
//                        command = CommandProvider.getInstance().getCommand(loginPage);
//                        command.execute(req, resp);
//                    }
//                    // if user is not authorized
//                    // go to registration page
//                    if (page.equalsIgnoreCase(registerPage)&&authorized==null) {
//                        command = CommandProvider.getInstance().getCommand(registerPage);
//                        command.execute(req, resp);
//                    }
//                }
//            }
//        } else {
//            logger.error("It is impossible to use HTTP filter");
//            //request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
//        }
        try {
            filterChain.doFilter(request, response);
        } catch (IOException | ServletException e) {
            logger.error(e);
        }
    }

}

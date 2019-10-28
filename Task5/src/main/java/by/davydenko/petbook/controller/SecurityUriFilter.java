package by.davydenko.petbook.controller;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.davydenko.petbook.entity.Role.ADMIN;
import static by.davydenko.petbook.entity.Role.USER;

/**
 * SecurityUriFilter implements Filter
 *
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
    private static List<String> adminCommands;
    private static List<String> userAuthorizedCommands;
    private static List<String> userNotAuthorizedCommands;
    private HttpSession httpSession;

    static {
        adminCommands = new ArrayList<>();
        adminCommands.add("admin");
        adminCommands.add("deleteByLogin");
        adminCommands.add("pagingFirstAdminUsers");
        adminCommands.add("pagingNextAdminUsers");
        adminCommands.add("pagingPrevAdminUsers");
        adminCommands.add("users");
        adminCommands.add("changeUserRole");
        adminCommands.add("adminArticles");
        adminCommands.add("settings");
        adminCommands.add("addArticlePage");
        adminCommands.add("addArticle");
        adminCommands.add("addArticleCommand");
        adminCommands.add("editArticlePage");
        adminCommands.add("editArticle");
        adminCommands.add("editArticleCommand");
        adminCommands.add("editAdminLogin");
        adminCommands.add("editAdminPassword");
        adminCommands.add("deleteArticle");

        userAuthorizedCommands = new ArrayList<>();
        userAuthorizedCommands.add("addPetPhoto");
        userAuthorizedCommands.add("deletePetPhotos");
        userAuthorizedCommands.add("user");
        userAuthorizedCommands.add("photos");
        userAuthorizedCommands.add("pet");
        userAuthorizedCommands.add("logout");
        userAuthorizedCommands.add("messages");
        userAuthorizedCommands.add("sendMessage");
        userAuthorizedCommands.add("editUserAvatar");
        userAuthorizedCommands.add("editUserName");
        userAuthorizedCommands.add("editPetName");
        userAuthorizedCommands.add("editPetAvatar");
        userAuthorizedCommands.add("editPetAge");
        userAuthorizedCommands.add("editPetBreed");
        userAuthorizedCommands.add("editPetType");
        userAuthorizedCommands.add("profile");
        userAuthorizedCommands.add("getChatMessages");
        userAuthorizedCommands.add("pagingPetPhotoNext");
        userAuthorizedCommands.add("pagingPetPhotoPrev");
        userAuthorizedCommands.add("sendMessagePage");
        userAuthorizedCommands.add("visit");

        userNotAuthorizedCommands = new ArrayList<>();
        userNotAuthorizedCommands.add("main");
        userNotAuthorizedCommands.add("articles");
        userNotAuthorizedCommands.add("article");
        userNotAuthorizedCommands.add("login");
        userNotAuthorizedCommands.add("pets");
        userNotAuthorizedCommands.add("getAllPets");
        userNotAuthorizedCommands.add("getPetsByType");
        userNotAuthorizedCommands.add("registerUser");
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

                HttpServletRequest httpRequest = (HttpServletRequest) request;
                String commandName = getCommandNameFromUri(httpRequest);
                httpSession = httpRequest.getSession();

                if (adminCommands.contains(commandName) && isAdmin()) {
                    httpRequest.setAttribute(COMMAND, commandName);
                } else if (userAuthorizedCommands.contains(commandName) && (isUser() || isAdmin())) {
                    httpRequest.setAttribute(COMMAND, commandName);
                } else if (userNotAuthorizedCommands.contains(commandName)) {
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
        Role role;
        role = (Role) httpSession.getAttribute(Attribute.ROLE);
        if (role != null) {
            return role.equals(ADMIN);
        }
        return false;
    }

    private boolean isUser() {
        Role role;
        role = (Role) httpSession.getAttribute(Attribute.ROLE);
        if (role != null) {
            return role.equals(USER);
        }
        return false;
    }


    private String getCommandNameFromUri(HttpServletRequest httpRequest) {
        String command = "";
        // Раскладываем адрес на составляющие
        String[] list = httpRequest.getRequestURI().split("/");

        if (list[list.length - 1].indexOf(".html") > 0) {
            command = list[list.length - 1];
        }

        command = command.replace(".html", "");
        return command;
    }

}

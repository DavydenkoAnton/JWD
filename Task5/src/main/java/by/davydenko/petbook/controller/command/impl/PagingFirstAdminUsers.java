package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PagingFirstAdminUsers implements by.davydenko.petbook.controller.command.Command {
    private static Logger logger = LogManager.getLogger(PagingFirstAdminUsers.class);
    private static final String ADMIN_USERS_PAGE = "http://localhost:8080/pb/pagingNextAdminUsers.html";
    private static final int PAGING_START = 0;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(Attribute.ADMIN_PAGING_NEXT_USERS_KEY,PAGING_START);
        request.getSession().setAttribute(Attribute.ADMIN_PAGING_PREV_USERS_KEY,PAGING_START);
        Object searchUserValue = request.getParameter(Attribute.SEARCH_USER_VALUE);
        String searchUserValueStr=searchUserValue==null?"":searchUserValue.toString();
        request.getSession().setAttribute(Attribute.SEARCH_USER_VALUE,searchUserValueStr);
        redirectPagingNextAdminCommand(response);
    }

    private void redirectPagingNextAdminCommand(HttpServletResponse response) {
        try {
            response.sendRedirect(ADMIN_USERS_PAGE);
        } catch (IOException e) {
            logger.error(e);
        }
    }
}

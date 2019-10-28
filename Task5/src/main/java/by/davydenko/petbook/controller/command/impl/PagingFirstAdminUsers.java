package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.service.util.XSSFilter;
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
        request.getSession().setAttribute(Attribute.PAGING_NEXT, PAGING_START);
        request.getSession().setAttribute(Attribute.PAGING_PREV, PAGING_START);
        String searchValue = getSearchValue(request);
        request.getSession().setAttribute(Attribute.SEARCH_USER_VALUE, searchValue);
        redirectPagingNextAdminCommand(response);
    }

    private void redirectPagingNextAdminCommand(HttpServletResponse response) {
        try {
            response.sendRedirect(ADMIN_USERS_PAGE);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    private String getSearchValue(HttpServletRequest request) {
        Object searchValue = request.getParameter(Attribute.SEARCH_USER_VALUE);
        return searchValue == null ? "" : XSSFilter.SCRIPT(searchValue.toString()) ? "" : searchValue.toString();
    }
}

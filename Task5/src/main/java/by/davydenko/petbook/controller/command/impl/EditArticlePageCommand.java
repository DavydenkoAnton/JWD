package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditArticlePageCommand implements Command {
    private static Logger logger = LogManager.getLogger(EditArticlePageCommand.class);
    private static final String ADMIN_ADD_ARTICLE_PAGE = "http://localhost:8080/pb/editArticle.html";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String id=request.getParameter("articleId");
        request.getSession().setAttribute("articleId",id);
        redirectToAddArticlesPage(response);
    }

    private void redirectToAddArticlesPage(HttpServletResponse response) {
        try {
            response.sendRedirect(ADMIN_ADD_ARTICLE_PAGE);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }
}
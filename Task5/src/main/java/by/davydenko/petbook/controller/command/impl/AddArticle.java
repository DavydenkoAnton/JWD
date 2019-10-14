package by.davydenko.petbook.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddArticle implements by.davydenko.petbook.controller.command.Command {
    private static Logger logger = LogManager.getLogger(AddArticle.class);
    private static final String ADMIN_ADD_ARTICLE_PAGE = "/WEB-INF/jsp/admin/add_article.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
            forwardToAddArticlePage(request, response);
    }

    private void forwardToAddArticlePage(HttpServletRequest request, HttpServletResponse response)  {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(ADMIN_ADD_ARTICLE_PAGE);
            dispatcher.forward(request, response);
        }catch(ServletException|IOException e){
            logger.error(e);
        }
    }
}

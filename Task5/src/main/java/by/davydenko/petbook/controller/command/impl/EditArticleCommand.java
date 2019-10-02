package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.service.ArticleService;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditArticleCommand implements by.davydenko.petbook.controller.command.Command {
    private static Logger logger = LogManager.getLogger(AddArticleCommand.class);
    private static final String ADMIN_ARTICLES_PAGE = "http://localhost:8080/pb/adminArticles.html";
    private ArticleService articleService;

    public EditArticleCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        articleService = serviceFactory.getArticleService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("adminArticleTitle");
        String description = request.getParameter("adminArticleDescription");
        String text = request.getParameter("adminArticleText");
        String type = request.getParameter("adminPetType");
        String id=request.getParameter("adminArticleId");
        try {
            articleService.updateArticle(title, description, text, type,id);
        } catch (ServiceException e) {
            logger.error(e);
        }
        redirectToRegistrationPage(response);
    }

    private void redirectToRegistrationPage(HttpServletResponse response) {
        response.setContentType("articles.jsp");
        try {
            response.sendRedirect(ADMIN_ARTICLES_PAGE);
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
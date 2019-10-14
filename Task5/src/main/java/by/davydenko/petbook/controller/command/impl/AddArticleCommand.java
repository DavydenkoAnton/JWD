package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.service.ArticleService;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddArticleCommand implements by.davydenko.petbook.controller.command.Command {
    private static Logger logger = LogManager.getLogger(AddArticleCommand.class);
    private static final String ADMIN_ARTICLES_PAGE = "http://localhost:8080/pb/adminArticles.html";
    private ArticleService articleService;

    public AddArticleCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        articleService = serviceFactory.getArticleService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("adminArticleTitle");
        String description = request.getParameter("adminArticleDescription");
        String text = request.getParameter("adminArticleText");
        String type = request.getParameter("adminPetType");
        try {
            articleService.addArticle(title, description, text, type);
        } catch (ServiceException e) {
            logger.error(e);
        }
        redirectToRegistrationPage(response);
    }

    private void redirectToRegistrationPage(HttpServletResponse response) {
        try {
            response.sendRedirect(ADMIN_ARTICLES_PAGE);
        } catch (IOException e) {
            logger.error(e);
        }
    }
}

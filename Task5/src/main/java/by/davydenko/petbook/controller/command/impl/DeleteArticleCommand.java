package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.Message;
import by.davydenko.petbook.entity.Pet;
import by.davydenko.petbook.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class DeleteArticleCommand implements by.davydenko.petbook.controller.command.Command {

    private static Logger logger = LogManager.getLogger(GetChatMessages.class);
    private static final String REDIRECT_MESSAGES_PAGE_URL = "http://localhost:8080/pb/adminArticles.html";
    private ArticleService articleService;

    public DeleteArticleCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        articleService=serviceFactory.getArticleService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String id=request.getParameter("articleId");
        try {
            articleService.deleteById(id);
        } catch (ServiceException e) {
            logger.error(e);
        }
        redirectToMessagesPage(response);
    }

    private void redirectToMessagesPage(HttpServletResponse response) {
        response.setContentType("articles.jsp");
        try {
            response.sendRedirect(REDIRECT_MESSAGES_PAGE_URL);
        } catch (IOException e) {
            logger.error(e);
        }
    }
}

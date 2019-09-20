package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.Article;
import by.davydenko.petbook.service.ArticleService;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public final class ArticlesPageCommand implements by.davydenko.petbook.controller.command.Command {
    private static Logger logger = LogManager.getLogger(ArticlesPageCommand.class);
    private static final String USER_PAGE = "/WEB-INF/jsp/articles.jsp";
    private ArticleService articleService;

    public ArticlesPageCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        articleService = serviceFactory.getArticleService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String petType = request.getParameter(Attribute.PET_TYPE);
        try {
            Optional<List<Article>> optionalArticles = articleService.getArticles(petType);
            if (optionalArticles.isPresent()) {
                List<Article> articles = optionalArticles.get();
                request.getSession().setAttribute(Attribute.ARTICLES, articles);
                request.getSession().setAttribute(Attribute.PET_TYPE, articles.get(0).getPetType());
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
        forwardToArticlesPage(request, response);
    }


    private void forwardToArticlesPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(USER_PAGE);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e);
        }
    }
}

package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
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

public class ArticlePageCommand implements Command {
    private static Logger logger = LogManager.getLogger(ArticlePageCommand.class);
    private static final String ARTICLE_PAGE = "/WEB-INF/jsp/article.jsp";
    private static final String ARTICLES_PAGE = "/WEB-INF/jsp/articles.jsp";
    private static final String ERROR_PAGE = "/WEB-INF/jsp//error.jsp";
    private ArticleService articleService;

    public ArticlePageCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        articleService = serviceFactory.getArticleService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<Article> optionalArticle;
        String articleTitle = request.getParameter(Attribute.ARTICLE_TITLE);
        try {
            optionalArticle = articleService.getArticle(articleTitle);
            if (optionalArticle.isPresent()) {
                Article article = optionalArticle.get();
                request.getSession().setAttribute(Attribute.ARTICLE, article);
                forwardToArticlePage(request, response);
                return;
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
        forwardToArticlesPage(request, response);
    }




    private void forwardToArticlePage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(ARTICLE_PAGE);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("[ ServletException ]", e);
        } catch (IOException e) {
            logger.error("[ IOException ]", e);
        }
    }

    private void forwardToArticlesPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(ARTICLES_PAGE);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("[ ServletException ]", e);
        } catch (IOException e) {
            logger.error("[ IOException ]", e);
        }
    }

    private void forwardToErrorPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(ERROR_PAGE);
            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.error("not redirected", e);
        }
    }

}

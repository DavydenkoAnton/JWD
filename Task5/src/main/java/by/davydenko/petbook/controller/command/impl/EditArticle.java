package by.davydenko.petbook.controller.command.impl;

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
import java.util.Optional;

public class EditArticle implements by.davydenko.petbook.controller.command.Command {
    private static Logger logger = LogManager.getLogger(EditArticle.class);
    private static final String ADMIN_EDIT_ARTICLE_PAGE = "/WEB-INF/jsp/admin/edit_article.jsp";
    private ArticleService articleService;

    public EditArticle() {
        ServiceFactory serviceFactory=ServiceFactory.getInstance();
        articleService=serviceFactory.getArticleService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String id = (String) request.getSession().getAttribute("articleId");
        try {
            Optional<Article> optionalArticle=articleService.getArticleById(id);
            if(optionalArticle.isPresent()){
                Article article=optionalArticle.get();
                request.getSession().setAttribute("adminArticle",article);
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
        forwardToAddArticlePage(request, response);
    }

    private void forwardToAddArticlePage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(ADMIN_EDIT_ARTICLE_PAGE);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e);
        }
    }
}
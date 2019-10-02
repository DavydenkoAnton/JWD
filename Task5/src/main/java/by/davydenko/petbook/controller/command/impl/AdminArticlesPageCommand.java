package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.Article;
import by.davydenko.petbook.entity.Pet;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AdminArticlesPageCommand implements Command {

        private static Logger logger = LogManager.getLogger(AdminArticlesPageCommand.class);
        private static final String ADMIN_ARTICLES_PAGE = "/WEB-INF/jsp/admin/articles.jsp";
        private ArticleService articleService;

    public AdminArticlesPageCommand() {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            articleService = serviceFactory.getArticleService();
        }

        @Override
        public void execute(HttpServletRequest request, HttpServletResponse response) {
            try {
                Optional<List<Article>> optionalArticles = articleService.getAllArticles();
                if (optionalArticles.isPresent()) {
                    List<Article> articles = optionalArticles.get();
                    request.getSession().setAttribute(Attribute.ADMIN_ARTICLES, articles);
                }
            } catch (ServiceException e) {
                logger.error(e);
            }
            forwardToAdminArticlesPage(request, response);
        }

        private void forwardToAdminArticlesPage(HttpServletRequest request, HttpServletResponse response) {
            try {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(ADMIN_ARTICLES_PAGE);
                dispatcher.forward(request, response);
            } catch (IOException | ServletException e) {
                logger.error(e);
            }
        }
    }


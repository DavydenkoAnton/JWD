package by.davydenko.petbook.service.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.dao.ArticleDao;
import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.DaoFactory;
import by.davydenko.petbook.entity.Article;
import by.davydenko.petbook.entity.ArticleType;
import by.davydenko.petbook.service.ArticleService;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import by.davydenko.petbook.service.util.creator.ArticleCreator;
import by.davydenko.petbook.service.util.creator.CreatorException;
import by.davydenko.petbook.service.util.creator.CreatorFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class ArticleServiceImpl implements ArticleService {

    private ArticleCreator articleCreator;
    private ArticleDao articleDao;

    public ArticleServiceImpl() {
        CreatorFactory creatorFactory = CreatorFactory.getInstance();
        DaoFactory daoFactory = DaoFactory.getInstance();
        articleCreator = creatorFactory.getArticleCreator();
        articleDao = daoFactory.getArticleDao();
    }

    @Override
    public Optional<List<Article>> getArticles(HttpServletRequest request) throws ServiceException {
        Optional<List<Article>> optionalArticles;
        ArticleType articleType = articleCreator.createType(request);
        try {
            optionalArticles = articleDao.readByType(articleType);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalArticles;
    }

    @Override
    public Optional<Article> getArticle(HttpServletRequest request) throws ServiceException {
        Optional<Article> optionalArticle;
        String articleTitle = null;
        try {
            articleTitle = articleCreator.createTitle(request);
        } catch (CreatorException e) {
            throw new ServiceException(e);
        }
        try {
            optionalArticle = articleDao.readByTitle(articleTitle);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalArticle;
    }
}

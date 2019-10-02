package by.davydenko.petbook.service.impl;

import by.davydenko.petbook.dao.ArticleDao;
import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.DaoFactory;
import by.davydenko.petbook.entity.Article;
import by.davydenko.petbook.entity.PetType;
import by.davydenko.petbook.service.ArticleService;
import by.davydenko.petbook.service.ServiceException;
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
    public Optional<List<Article>> getArticles(String type) throws ServiceException {
        Optional<List<Article>> optionalArticles;
        try {
            PetType petType = articleCreator.createType(type);
            optionalArticles = articleDao.readByType(petType);
        } catch (DaoException | CreatorException e) {
            throw new ServiceException(e);
        }
        return optionalArticles;
    }

    @Override
    public Optional<Article> getArticle(String articleTitle) throws ServiceException {
        Optional<Article> optionalArticle;

        try {
            articleTitle = articleCreator.createTitle(articleTitle);
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

    @Override
    public Optional<List<Article>> getAllArticles() throws ServiceException {
        Optional<List<Article>> optionalArticles;
        try {
            optionalArticles = articleDao.readAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalArticles;
    }

    @Override
    public Optional<Article> getArticleById(String id) throws ServiceException {
        Optional<Article> optionalArticle;
          try {
            int idTemp=articleCreator.creatId(id);
            optionalArticle=articleDao.read(idTemp);
        } catch (CreatorException|DaoException e) {
            throw new ServiceException(e);
        }
        return optionalArticle;
    }

    @Override
    public void addArticle(String title, String description, String text, String type) throws ServiceException {
        Article article = new Article();
        try {
            article.setTitle(articleCreator.createTitle(title));
            article.setDescription(articleCreator.createDescription(description));
            article.setText(articleCreator.createText(text));
            article.setPetType(articleCreator.createType(type));
            articleDao.create(article);
        } catch (CreatorException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateArticle(String title, String description, String text, String type, String id) throws ServiceException {
        Article article = new Article();
        try {
            article.setTitle(articleCreator.createTitle(title));
            article.setDescription(articleCreator.createDescription(description));
            article.setText(articleCreator.createText(text));
            article.setPetType(articleCreator.createType(type));
            article.setId(articleCreator.creatId(id));
            articleDao.update(article);
        } catch (CreatorException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteById(String id) throws ServiceException {
        try {
            int idTemp=articleCreator.creatId(id);
            articleDao.delete(idTemp);
        } catch (CreatorException|DaoException e) {
            throw new ServiceException(e);
        }

    }
}

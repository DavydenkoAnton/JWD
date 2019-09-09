package by.davydenko.petbook.service;

import by.davydenko.petbook.entity.Article;
import by.davydenko.petbook.entity.ArticleType;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface ArticleService extends Service<Article> {


    Optional<List<Article>> getArticles(HttpServletRequest request) throws ServiceException;

    Optional<Article> getArticle(HttpServletRequest request) throws ServiceException;
}

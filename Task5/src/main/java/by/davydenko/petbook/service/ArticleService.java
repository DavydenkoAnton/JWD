package by.davydenko.petbook.service;

import by.davydenko.petbook.entity.Article;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface ArticleService extends Service<Article> {


    Optional<List<Article>> getArticles(String petType) throws ServiceException;

    Optional<Article> getArticle(String articleTitle) throws ServiceException;
}

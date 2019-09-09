package by.davydenko.petbook.dao;

import by.davydenko.petbook.entity.Article;
import by.davydenko.petbook.entity.ArticleType;

import java.util.List;
import java.util.Optional;

public interface ArticleDao extends Dao<Article> {
    Optional<List<Article>> readByType(ArticleType articleType) throws DaoException;

    Optional<Article> readByTitle(String articleTitle) throws DaoException;
}

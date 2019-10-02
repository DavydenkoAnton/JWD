package by.davydenko.petbook.dao;

import by.davydenko.petbook.entity.Article;
import by.davydenko.petbook.entity.PetType;

import java.util.List;
import java.util.Optional;

public interface ArticleDao extends Dao<Article> {
    Optional<List<Article>> readByType(PetType petType) throws DaoException;

    Optional<Article> readByTitle(String articleTitle) throws DaoException;

    Optional<List<Article>> readAll()throws DaoException;

    @Override
    void update(Article article)throws DaoException;
}

package by.davydenko.petbook.dao.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.dao.ArticleDao;
import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.Article;
import by.davydenko.petbook.entity.ArticleType;
import by.davydenko.petbook.entity.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArticleDaoImpl implements ArticleDao {

    private static final String SELECT_ARTICLES_BY_TYPE = "SELECT title,text,type,description,id FROM petbook.articles WHERE type=?";
    private static final String SELECT_ARTICLE_BY_TITLE = "SELECT title,text,type,description,id FROM petbook.articles WHERE title=?";

    @Override
    public void create(Article entity) throws ConnectionPoolException, DaoException {

    }

    @Override
    public Optional<Article> read(int id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<List<Article>> readByType(ArticleType articleType) throws DaoException {
        List<Article> articles = null;
        Connection connection;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
        try {
            preparedStatement = connection.prepareStatement(SELECT_ARTICLES_BY_TYPE);
            preparedStatement.setString(1, articleType.toString());
            resultSet = preparedStatement.executeQuery();
            articles = new ArrayList<>();
            while (resultSet.next()) {
                resultSet.getRow();
                Article article = new Article();
                article.setTitle(resultSet.getString(Attribute.TITLE));
                article.setText(resultSet.getString(Attribute.TEXT));
                article.setDescription(resultSet.getString(Attribute.DESCRIPTION));
                article.setId(resultSet.getInt(Attribute.ID));
                article.setArticleType(ArticleType.valueOf(resultSet.getString(Attribute.TYPE)));
                articles.add(article);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        try {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }

        return Optional.ofNullable(articles);
    }

    @Override
    public Optional<Article> readByTitle(String articleTitle) throws DaoException {
        Article article = null;
        Connection connection;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
        try {
            preparedStatement = connection.prepareStatement(SELECT_ARTICLE_BY_TITLE);
            preparedStatement.setString(1, articleTitle);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultSet.getRow();
                article = new Article();
                article.setTitle(resultSet.getString(Attribute.TITLE));
                article.setText(resultSet.getString(Attribute.TEXT));
                article.setDescription(resultSet.getString(Attribute.DESCRIPTION));
                article.setId(resultSet.getInt(Attribute.ID));
                article.setArticleType(ArticleType.valueOf(resultSet.getString(Attribute.TYPE)));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        try {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }

        return Optional.ofNullable(article);
    }

    @Override
    public void update(Article entity) {

    }

    @Override
    public void delete(int id) {

    }
}

package by.davydenko.petbook.dao.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.dao.ArticleDao;
import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.Article;
import by.davydenko.petbook.entity.PetType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArticleDaoImpl implements ArticleDao {

    private static final String SELECT_ALL_ARTICLES = "SELECT title,text,type,description,id FROM petbook.articles ";
    private static final String SELECT_ARTICLES_BY_TYPE = "SELECT title,text,type,description,id FROM petbook.articles WHERE type=?";
    private static final String SELECT_ARTICLE_BY_ID = "SELECT title,text,type,description,id FROM petbook.articles WHERE id=?";
    private static final String SELECT_ARTICLE_BY_TITLE = "SELECT title,text,type,description,id FROM petbook.articles WHERE title=?";
    private static final String INSERT_ARTICLE = "INSERT INTO petbook.articles (title, description, text,type) VALUES (?,?,?,?)";
    private static final String UPDATE_ARTICLE_BY_ID = "UPDATE petbook.articles SET title=?,description=?,text=?,type=? WHERE id=?";
    private static final String DELETE_ARTICLE_BY_ID = "DELETE FROM petbook.articles WHERE id=?";
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private ConnectionPool connectionPool;

    public ArticleDaoImpl() {
        connectionPool = ConnectionPool.getInstance();
    }

    private void closeConnection(ConnectionPool cp, Connection c, PreparedStatement p) throws DaoException {
        try {
            cp.closeConnection(c, p);
        } catch (ConnectionPoolException e) {
            throw new DaoException("cannot close connection", e);
        }
    }

    private void closeConnection(ConnectionPool cp, Connection c, PreparedStatement p, ResultSet r) throws DaoException {
        try {
            cp.closeConnection(c, p, r);
        } catch (ConnectionPoolException e) {
            throw new DaoException("cannot close connection", e);
        }
    }

    private void takeConnection() throws DaoException {
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            throw new DaoException("cannot close connection", e);
        }
    }

    @Override
    public void create(Article article) throws DaoException {
        try {
            takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT_ARTICLE);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getDescription());
            preparedStatement.setString(3, article.getText());
            preparedStatement.setString(4, article.getPetType().name());
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
            }
            closeConnection(connectionPool, connection, preparedStatement);
        } catch (SQLException e) {
            closeConnection(connectionPool, connection, preparedStatement);
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Article article) throws DaoException {
        try {
            takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_ARTICLE_BY_ID);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getDescription());
            preparedStatement.setString(3, article.getText());
            preparedStatement.setString(4, article.getPetType().name());
            preparedStatement.setInt(5, article.getId());
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
            }
            closeConnection(connectionPool, connection, preparedStatement);
        } catch (SQLException e) {
            closeConnection(connectionPool, connection, preparedStatement);
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Article> read(int id) throws DaoException {
        Article article = null;
        try {
            takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ARTICLE_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultSet.getRow();
                article = new Article();
                article.setTitle(resultSet.getString(Attribute.TITLE));
                article.setText(resultSet.getString(Attribute.TEXT));
                article.setDescription(resultSet.getString(Attribute.DESCRIPTION));
                article.setId(resultSet.getInt(Attribute.ID));
                article.setPetType(PetType.valueOf(resultSet.getString(Attribute.TYPE)));
            }
            closeConnection(connectionPool, connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            closeConnection(connectionPool, connection, preparedStatement, resultSet);
            throw new DaoException(e);
        }
        return Optional.ofNullable(article);
    }

    @Override
    public Optional<List<Article>> readByType(PetType petType) throws DaoException {
        List<Article> articles = new ArrayList<>();
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ARTICLES_BY_TYPE);
            preparedStatement.setString(1, petType.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultSet.getRow();
                Article article = new Article();
                article.setTitle(resultSet.getString(Attribute.TITLE));
                article.setText(resultSet.getString(Attribute.TEXT));
                article.setDescription(resultSet.getString(Attribute.DESCRIPTION));
                article.setId(resultSet.getInt(Attribute.ID));
                article.setPetType(PetType.valueOf(resultSet.getString(Attribute.TYPE)));
                articles.add(article);
            }
            if (articles.size() == 0) {
                articles = null;
            }
            closeConnection(connectionPool, connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            closeConnection(connectionPool, connection, preparedStatement, resultSet);
            throw new DaoException(e);
        }
        return Optional.ofNullable(articles);
    }

    @Override
    public Optional<List<Article>> readAll() throws DaoException {
        List<Article> articles = new ArrayList<>();
        try {
            takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_ARTICLES);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultSet.getRow();
                Article article = new Article();
                article.setTitle(resultSet.getString(Attribute.TITLE));
                article.setText(resultSet.getString(Attribute.TEXT));
                article.setDescription(resultSet.getString(Attribute.DESCRIPTION));
                article.setId(resultSet.getInt(Attribute.ID));
                article.setPetType(PetType.valueOf(resultSet.getString(Attribute.TYPE)));
                articles.add(article);
            }
            if (articles.size() == 0) {
                articles = null;
            }
            closeConnection(connectionPool, connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            closeConnection(connectionPool, connection, preparedStatement, resultSet);
            throw new DaoException(e);
        }
        return Optional.ofNullable(articles);
    }

    @Override
    public Optional<Article> readByTitle(String articleTitle) throws DaoException {
        Article article = null;
        try {
            takeConnection();
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
                article.setPetType(PetType.valueOf(resultSet.getString(Attribute.TYPE)));
            }
            closeConnection(connectionPool, connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            closeConnection(connectionPool, connection, preparedStatement, resultSet);
            throw new DaoException(e);
        }
        return Optional.ofNullable(article);
    }


    @Override
    public void delete(int id) throws DaoException {
        try {
            takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_ARTICLE_BY_ID);
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
            }
            closeConnection(connectionPool, connection, preparedStatement);
        } catch (SQLException e) {
            closeConnection(connectionPool, connection, preparedStatement);
            throw new DaoException(e);
        }
    }
}

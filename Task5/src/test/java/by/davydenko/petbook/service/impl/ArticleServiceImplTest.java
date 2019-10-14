package by.davydenko.petbook.service.impl;

import by.davydenko.petbook.dao.ArticleDao;
import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.DaoFactory;
import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.Article;
import by.davydenko.petbook.entity.PetType;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArticleServiceImplTest {

    private static ArticleDao articleDao;

    @BeforeAll
    static void init() throws ConnectionPoolException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.init();
        DaoFactory daoFactory = DaoFactory.getInstance();
        articleDao = daoFactory.getArticleDao();
    }

    @Test
    @Order(1)
    @DisplayName("add article to db")
    void addArticle() {
        Article article = new Article();
        article.setTitle("testTitle");
        article.setDescription("testDescription");
        article.setText("testText");
        article.setPetType(PetType.OTHER);
        assertDoesNotThrow(() -> articleDao.create(article));
    }

    @Test
    @Order(2)
    @DisplayName("get article by title")
    void getArticleByTitle() throws DaoException {
        Optional<Article> optionalArticle = articleDao.readByTitle("testTitle");
        assertNotEquals(Optional.empty(), optionalArticle);
    }

    @Test
    @Order(3)
    @DisplayName("update article")
    void updateArticle() throws DaoException {
        Optional<Article> optionalArticle = articleDao.readByTitle("testTitle");
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            article.setTitle("titleTest");
            article.setDescription("descriptionTest");
            article.setText("textTest");
            article.setPetType(PetType.DOG);
            assertDoesNotThrow(() -> articleDao.update(article));
        }else{
            fail("[ cannot get article by title ]");
        }
    }

    @Test
    @Order(4)
    @DisplayName("delete article by id")
    void deleteById() throws DaoException {
        Optional<Article> optionalArticle = articleDao.readByTitle("titleTest");
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            assertDoesNotThrow(() -> articleDao.delete(article.getId()));
        }else{
            fail("[ cannot get article by title ]");
        }
    }

}
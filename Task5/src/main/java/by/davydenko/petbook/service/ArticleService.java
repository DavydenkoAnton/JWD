package by.davydenko.petbook.service;

import by.davydenko.petbook.entity.Article;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface ArticleService extends Service<Article> {

    /**
     * Finds and returns articles from the database by given pet type.
     *
     * @param petType pet type of the article to be found
     * @return Optional containing either:
     * <ol>
     * <li>articles encapsulating all the info from the database, if there were found.</li>
     * <li><code>null</code>, if no article was found for given pet type.</li>
     * </ol>
     * @throws ServiceException if any Dao or Creator exception has occurred.
     */
    Optional<List<Article>> getArticles(String petType) throws ServiceException;

    /**
     * Finds and returns article from the database by given article title.
     *
     * @param articleTitle title of the article to be found
     * @return Optional containing either:
     * <ol>
     * <li>article encapsulating all the info from the database, if it was found.</li>
     * <li><code>null</code>, if no article was found for given article title.</li>
     * </ol>
     * @throws ServiceException if any Dao or Creator exception has occurred.
     */
    Optional<Article> getArticleByTitle(String articleTitle) throws ServiceException;

    /**
     * Finds and returns all articles from the database.
     *
     * @return Optional containing either:
     * <ol>
     * <li>articles encapsulating all the info from the database, if there were found.</li>
     * <li><code>null</code>, if no article was found.</li>
     * </ol>
     * @throws ServiceException if any Dao exception has occurred.
     */
    Optional<List<Article>> getAllArticles() throws ServiceException;

    /**
     * Add article to database by given params.
     *
     * @param title       title of the article.
     * @param description description of the article.
     * @param text        text of the article.
     * @param type        pet type of the article.
     * @throws ServiceException if any Dao or Creator exception has occurred.
     */
    void addArticle(String title, String description, String text, String type) throws ServiceException;

    /**
     * Find and return all article from the database by id.
     *
     * @param id id of the article.
     * @return Optional containing either:
     * <ol>
     * <li>article encapsulating all the info from the database, if it was found.</li>
     * <li><code>null</code>, if no article was found for given article id.</li>
     * </ol>
     * @throws ServiceException if any Dao or Creator exception has occurred.
     */
    Optional<Article> getArticleById(String id) throws ServiceException;

    /**
     * Update all article rows in the database by params.
     *
     * @param title       title of the article.
     * @param description description of the article.
     * @param text        text of the article.
     * @param type        pet type of the article.
     * @throws ServiceException if any Dao or Creator exception has occurred.
     */
    void updateArticle(String title, String description, String text, String type, String id) throws ServiceException;

    /**
     * Delete from database by give article id.
     *
     * @param id id of the article.
     * @throws ServiceException if any Dao  exception has occurred.
     */
    void deleteById(String id) throws ServiceException;
}

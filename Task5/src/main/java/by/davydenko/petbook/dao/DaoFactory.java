package by.davydenko.petbook.dao;

import by.davydenko.petbook.dao.impl.ArticleDaoImpl;
import by.davydenko.petbook.dao.impl.MessageDaoImpl;
import by.davydenko.petbook.dao.impl.PetDaoImpl;
import by.davydenko.petbook.dao.impl.UserDaoImpl;


public class DaoFactory {

    private static final DaoFactory instance = new DaoFactory();
    private MessageDao messageDao = new MessageDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private PetDao petDao = new PetDaoImpl();
    private ArticleDao articleDao = new ArticleDaoImpl();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public PetDao getPetDao() {
        return petDao;
    }

    public ArticleDao getArticleDao() {
        return articleDao;
    }
}

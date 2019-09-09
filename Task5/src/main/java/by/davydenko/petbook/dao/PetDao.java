package by.davydenko.petbook.dao;

import by.davydenko.petbook.entity.ArticleType;
import by.davydenko.petbook.entity.Pet;

import java.util.List;
import java.util.Optional;

public interface PetDao extends Dao<Pet> {
    @Override
    void create(Pet pet);

    @Override
    Optional<Pet> read(int id) throws DaoException;

    Optional<List<Pet>> read() throws DaoException;

    Optional<Pet> readByUserId(int userId) throws DaoException;

    @Override
    void update(Pet pet);

    @Override
    void delete(int id);

    void updateName(int id, String name) throws DaoException;

    void updateBreed(int id, String breed) throws DaoException;

    void updateAvatarURL(int userId, String path) throws DaoException;

    void updateAge(int id, int age) throws DaoException;

    Optional<List<Pet>> readCorrespondents(int userId) throws DaoException;

    void createByUserId(int userId) throws DaoException;

    Optional<List<Pet>> readByType(ArticleType type) throws DaoException;
}

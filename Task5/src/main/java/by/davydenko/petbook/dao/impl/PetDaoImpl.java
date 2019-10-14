package by.davydenko.petbook.dao.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.PetDao;
import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.PetType;
import by.davydenko.petbook.entity.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PetDaoImpl implements PetDao {

    private static final String SELECT_PET_BY_USER_ID = "SELECT name,breed,age,avatarUrl,userId,type FROM petbook.pets WHERE userId=?";
    private static final String SELECT_PHOTO_URL_BY_ID = "SELECT url FROM petbook.photo WHERE userId=?";
    private static final String SELECT_PHOTO_URL_BY_ID_FROM_TO = "SELECT url FROM petbook.photo WHERE userId=? AND id>? AND id<?";
    private static final String SELECT_ALL = "SELECT * FROM petbook.pets";
    private static final String SELECT_PETS_FROM_TO = "SELECT name,breed,age,avatarUrl,userId,type FROM petbook.pets WHERE userId>=? AND userId<=?";
    private static final String SELECT_PETS_FROM_TO_BY_NAME = "SELECT name,breed,age,avatarUrl,userId,type FROM petbook.pets WHERE userId>=? AND userId<=? AND name LIKE ? ";
    private static final String SELECT_PETS_BY_TYPE = "SELECT name,breed,age,avatarUrl,userId,type FROM petbook.pets WHERE type=?";
    private static final String SELECT_PETS_BY_TYPE_NO_USER = "SELECT name,breed,age,avatarUrl,userId,type FROM petbook.pets WHERE type=? AND userId!=?";
    private static final String SELECT_PETS_NO_USER = "SELECT name,breed,age,avatarUrl,userId,type FROM petbook.pets WHERE userId!=?";
    private static final String SELECT_PET_BY_SENDER_MESSAGE_ID = "SELECT name,breed,age,avatarUrl,userId,type FROM petbook.pets " +
            "WHERE pets.userId IN (SELECT petbook.messages.userId FROM petbook.messages WHERE messages.senderId=?) " +
            "OR pets.userId IN (SELECT petbook.messages.senderId FROM petbook.messages WHERE messages.userId=?)";
    private final static String UPDATE_PET_NAME = "UPDATE petbook.pets  SET name=? WHERE userId=? ";
    private final static String UPDATE_PET_BREED = "UPDATE petbook.pets  SET breed=? WHERE userId=? ";
    private final static String UPDATE_PET_AVATAR = "UPDATE petbook.pets  SET avatarUrl=? WHERE userId=? ";
    private final static String UPDATE_PET_AGE = "UPDATE petbook.pets  SET age=? WHERE userId=? ";
    private final static String UPDATE_PET_TYPE = "UPDATE petbook.pets  SET type=? WHERE userId=? ";
    private final static String CREATE_BY_USER_ID = "INSERT INTO petbook.pets (userId) VALUES (?)";
    private final static String CREATE_PHOTO_URL_BY_ID = "INSERT INTO petbook.photo (userId,url) VALUES (?,?) ";
    private final static String DELETE_PET_BY_USER_ID = "DELETE FROM petbook.pets WHERE userId=?";
    private final static String DELETE_PHOTO = "DELETE FROM petbook.photo WHERE userId=? AND url=?";
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private ConnectionPool connectionPool;

    public PetDaoImpl() {
        connectionPool = ConnectionPool.getInstance();
    }

    @Override
    public void create(Pet pet) {

    }

    @Override
    public void createByUserId(int userId) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(CREATE_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
            }
            connectionPool.closeConnection(connection, preparedStatement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void createPhotoById(int id, String path) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(CREATE_PHOTO_URL_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, path);
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
            }
            connectionPool.closeConnection(connection, preparedStatement);
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void createAvatarById(int id, String path) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_PET_AVATAR);
            preparedStatement.setString(1, path);
            preparedStatement.setInt(2, id);
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
            }
            connectionPool.closeConnection(connection, preparedStatement);
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Pet> read(int id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<List<Pet>> readFromTo(int from, int to) throws DaoException {
        List<Pet> pets = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_PETS_FROM_TO);
            preparedStatement.setInt(1, from);
            preparedStatement.setInt(2, to);
            resultSet = preparedStatement.executeQuery();
            List<Pet> petsBuf = new ArrayList<>();
            while (resultSet.next()) {
                resultSet.getRow();
                Pet pet = new Pet();
                pet.setName(resultSet.getString(Attribute.NAME));
                pet.setBreed(resultSet.getString(Attribute.BREED));
                pet.setAge(resultSet.getInt(Attribute.AGE));
                pet.setType(PetType.valueOf(resultSet.getString(Attribute.TYPE)));
                pet.setAvatarUrl(resultSet.getString(Attribute.AVATAR_URL));
                pet.setUserId(resultSet.getInt(Attribute.USER_ID));
                petsBuf.add(pet);
            }
            if (petsBuf.size() != 0) {
                pets = petsBuf;
            }
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }

        return Optional.ofNullable(pets);
    }

    @Override
    public Optional<List<Pet>> readFromTo(int from, int to, String searchValue) throws DaoException {
        List<Pet> pets = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_PETS_FROM_TO_BY_NAME);
            preparedStatement.setInt(1, from);
            preparedStatement.setInt(2, to);
            preparedStatement.setString(3, searchValue + "%");
            resultSet = preparedStatement.executeQuery();
            List<Pet> petsBuf = new ArrayList<>();
            while (resultSet.next()) {
                resultSet.getRow();
                Pet pet = new Pet();
                pet.setName(resultSet.getString(Attribute.NAME));
                pet.setBreed(resultSet.getString(Attribute.BREED));
                pet.setAge(resultSet.getInt(Attribute.AGE));
                pet.setType(PetType.valueOf(resultSet.getString(Attribute.TYPE)));
                pet.setAvatarUrl(resultSet.getString(Attribute.AVATAR_URL));
                pet.setUserId(resultSet.getInt(Attribute.USER_ID));
                petsBuf.add(pet);
            }
            if (petsBuf.size() != 0) {
                pets = petsBuf;
            }
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }

        return Optional.ofNullable(pets);
    }

    @Override
    public Optional<List<Pet>> readAllNoUser(int id) throws DaoException {
        List<Pet> pets = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_PETS_NO_USER);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            List<Pet>petsBuf = new ArrayList<>();
            while (resultSet.next()) {
                resultSet.getRow();
                Pet pet = new Pet();
                pet.setName(resultSet.getString(Attribute.NAME));
                pet.setBreed(resultSet.getString(Attribute.BREED));
                pet.setAge(resultSet.getInt(Attribute.AGE));
                pet.setAvatarUrl(resultSet.getString(Attribute.AVATAR_URL));
                pet.setUserId(resultSet.getInt(Attribute.USER_ID));
                petsBuf.add(pet);
            }
            if(petsBuf.size()>0){
                pets=petsBuf;
            }
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(pets);
    }

    @Override
    public Optional<List<Pet>> read() throws DaoException {
        List<Pet> pets = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            List<Pet> petsBuf = new ArrayList<>();
            while (resultSet.next()) {
                resultSet.getRow();
                Pet pet = new Pet();
                pet.setName(resultSet.getString(Attribute.NAME));
                pet.setBreed(resultSet.getString(Attribute.BREED));
                pet.setAge(resultSet.getInt(Attribute.AGE));
                pet.setAvatarUrl(resultSet.getString(Attribute.AVATAR_URL));
                pet.setType(PetType.valueOf(resultSet.getString(Attribute.TYPE)));
                pet.setUserId(resultSet.getInt(Attribute.USER_ID));
                petsBuf.add(pet);
            }
            if(petsBuf.size()>0){
                pets=petsBuf;
            }
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(pets);
    }

    @Override
    public Optional<Pet> readByUserId(int userId) throws DaoException {
        Pet pet = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_PET_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultSet.getRow();
                pet = new Pet();
                pet.setName(resultSet.getString(Attribute.NAME));
                pet.setBreed(resultSet.getString(Attribute.BREED));
                pet.setAge(resultSet.getInt(Attribute.AGE));
                pet.setAvatarUrl(resultSet.getString(Attribute.AVATAR_URL));
                pet.setUserId(resultSet.getInt(Attribute.USER_ID));
                pet.setType(PetType.valueOf(resultSet.getString((Attribute.TYPE))));
            }
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(pet);
    }

    @Override
    public Optional<List<Pet>> readByType(PetType type) throws DaoException {
        List<Pet> pets = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_PETS_BY_TYPE);
            preparedStatement.setString(1, type.toString());
            resultSet = preparedStatement.executeQuery();
            List<Pet> petsBuf = new ArrayList<>();
            while (resultSet.next()) {
                resultSet.getRow();
                Pet pet = new Pet();
                pet.setName(resultSet.getString(Attribute.NAME));
                pet.setBreed(resultSet.getString(Attribute.BREED));
                pet.setAge(resultSet.getInt(Attribute.AGE));
                pet.setAvatarUrl(resultSet.getString(Attribute.AVATAR_URL));
                pet.setUserId(resultSet.getInt(Attribute.USER_ID));
                petsBuf.add(pet);
            }
            if(petsBuf.size()>0){
                pets=petsBuf;
            }
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(pets);
    }

    @Override
    public Optional<List<Pet>> readByTypeNoUser(String type, int id) throws DaoException {
        List<Pet> pets = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_PETS_BY_TYPE_NO_USER);
            preparedStatement.setString(1, type.toString());
            preparedStatement.setInt(2, id);
            resultSet = preparedStatement.executeQuery();
            List<Pet> petsBuf = new ArrayList<>();
            while (resultSet.next()) {
                resultSet.getRow();
                Pet pet = new Pet();
                pet.setName(resultSet.getString(Attribute.NAME));
                pet.setBreed(resultSet.getString(Attribute.BREED));
                pet.setAge(resultSet.getInt(Attribute.AGE));
                pet.setAvatarUrl(resultSet.getString(Attribute.AVATAR_URL));
                pet.setUserId(resultSet.getInt(Attribute.USER_ID));
                petsBuf.add(pet);
            }
            if(petsBuf.size()>0){
                pets=petsBuf;
            }
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(pets);
    }

    @Override
    public List<String> readPhotosUrl(int id) throws DaoException {
        List<String> photosUrl = new ArrayList<>();
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_PHOTO_URL_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultSet.getRow();
                photosUrl.add(resultSet.getString(Attribute.URL));
            }
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return photosUrl;
    }

    @Override
    public List<String> readPhotosUrl(int id, int from, int to) throws DaoException {
        List<String> photosUrl = new ArrayList<>();
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_PHOTO_URL_BY_ID_FROM_TO);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, from);
            preparedStatement.setInt(3, to);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultSet.getRow();
                photosUrl.add(resultSet.getString(Attribute.URL));
            }
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return photosUrl;
    }

    @Override
    public Optional<List<Pet>> readCorrespondents(int userId) throws DaoException {
        List<Pet> pets;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_PET_BY_SENDER_MESSAGE_ID);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);
            resultSet = preparedStatement.executeQuery();
            pets = new ArrayList<>();
            while (resultSet.next()) {
                resultSet.getRow();
                Pet pet = new Pet();
                pet.setName(resultSet.getString(Attribute.NAME));
                pet.setBreed(resultSet.getString(Attribute.BREED));
                pet.setAge(resultSet.getInt(Attribute.AGE));
                pet.setAvatarUrl(resultSet.getString(Attribute.AVATAR_URL));
                pet.setUserId(resultSet.getInt(Attribute.USER_ID));
                pets.add(pet);
            }
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(pets);
    }

    @Override
    public void update(Pet pet) {

    }

    @Override
    public void updateName(int id, String name) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_PET_NAME);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
            }
            connectionPool.closeConnection(connection, preparedStatement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateBreed(int id, String breed) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_PET_BREED);
            preparedStatement.setString(1, breed);
            preparedStatement.setInt(2, id);
            int stateValue = preparedStatement.executeUpdate();
            if (stateValue > 0) {
                connection.commit();
            }
            connectionPool.closeConnection(connection, preparedStatement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Cannot update breed", e);
        }
    }

    @Override
    public void updateAge(int id, int age) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_PET_AGE);
            preparedStatement.setInt(1, age);
            preparedStatement.setInt(2, id);
            int stateValue = preparedStatement.executeUpdate();
            if (stateValue > 0) {
                connection.commit();
            }
            connectionPool.closeConnection(connection, preparedStatement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Cannot update breed", e);
        }
    }

    @Override
    public void updateType(int id, PetType type) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_PET_TYPE);
            preparedStatement.setString(1, type.name());
            preparedStatement.setInt(2, id);
            int stateValue = preparedStatement.executeUpdate();
            if (stateValue > 0) {
                connection.commit();
            }
            connectionPool.closeConnection(connection, preparedStatement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Cannot update pet type", e);
        }
    }

    @Override
    public void updateAvatarURL(int userId, String avatarURL) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_PET_AVATAR);
            preparedStatement.setString(1, avatarURL);
            preparedStatement.setInt(2, userId);
            int stateValue = preparedStatement.executeUpdate();
            if (stateValue > 0) {
                connection.commit();
            }
            connectionPool.closeConnection(connection, preparedStatement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Cannot update pet avatar", e);
        }
    }

    @Override
    public void delete(int id)throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_PET_BY_USER_ID);
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
            }
            connectionPool.closeConnection(connection, preparedStatement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Cannot delete pet by user id", e);
        }
    }

    @Override
    public void deletePhotos(int id, String url) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_PHOTO);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, url);
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
            }
            connectionPool.closeConnection(connection, preparedStatement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Cannot delete pet photos", e);
        }
    }
}

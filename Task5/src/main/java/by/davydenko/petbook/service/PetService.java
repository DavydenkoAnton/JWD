package by.davydenko.petbook.service;

import by.davydenko.petbook.entity.Pet;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Optional;

public interface PetService extends Service<Pet> {

    /**
     * Find and update pet name in the database.
     *
     * @param name   new pet name.
     * @param userId id of the user to which have pet.
     * @throws ServiceException if any Dao, Creator exception has occurred.
     */
    void uploadName(String name, String userId) throws ServiceException;

    /**
     * Find and update pet breed in the database.
     *
     * @param breed  new pet breed.
     * @param userId id of the user to which have pet.
     * @throws ServiceException if any Dao, Creator exception has occurred.
     */
    void uploadBreed(String breed, String userId) throws ServiceException;

    /**
     * Find and update pet avatar in the database,and upload avatar image to server.
     *
     * @param image  new pet image.
     * @param path   path to the avatar folder.
     * @param userId id of the user to which have pet.
     * @throws ServiceException if any Dao, Creator exception has occurred.
     */
    void uploadAvatar(Part image, String path, String userId) throws ServiceException;

    /**
     * Find and update pet age in the database.
     *
     * @param age    new pet age.
     * @param userId id of the user to which have pet.
     * @throws ServiceException if any Dao, Creator exception has occurred.
     */
    void uploadAge(String age, String userId) throws ServiceException;

    /**
     * Find and update pet type in the database.
     *
     * @param petType new pet type.
     * @param userId  id of the user to which have pet.
     * @throws ServiceException if any Dao, Creator exception has occurred.
     */
    void uploadType(String petType, String userId) throws ServiceException;

    /**
     * Find and return pets from database which send any messages to user.
     *
     * @param userId user id who has messages.
     * @return Optional containing either:
     * <ol>
     * <li>Pets contain fields userId, name, breed, age, avatarUrl  not null.</li>
     * <li><code>null</code>, if pets were not found.</li>
     * </ol>
     * @throws ServiceException if any Dao, Creator exception has occurred.
     */
    Optional<List<Pet>> getMessageSenders(String userId) throws ServiceException;

    /**
     * Add pet database.
     *
     * @param userId user id who will have a pet.
     * @throws ServiceException if any Dao, Creator exception has occurred.
     */
    void registerByUserId(int userId) throws ServiceException;

    /**
     * Return percent pet type like dog from all pet types.
     *
     * @return int:
     * @throws ServiceException if any Dao exception has occurred.
     */
    int getDogPercent() throws ServiceException;

    /**
     * Return percent pet type like cat from all pet types.
     *
     * @return int:
     * @throws ServiceException if any Dao exception has occurred.
     */
    int getCatPercent() throws ServiceException;

    /**
     * Return percent pet type like bird from all pet types.
     *
     * @return int:
     * @throws ServiceException if any Dao exception has occurred.
     */
    int getBirdPercent() throws ServiceException;

    /**
     * Return percent pet type like other from all pet types.
     *
     * @return int:
     * @throws ServiceException if any Dao exception has occurred.
     */
    int getOtherPercent() throws ServiceException;

    /**
     * Find and return pet from database.
     *
     * @param userId user id who has pet.
     * @return Optional containing either:
     * <ol>
     * <li>Pet contain fields userId, name, breed, age, avatarUrl,type  not null.</li>
     * <li><code>null</code>, if pets were not found.</li>
     * </ol>
     * @throws ServiceException if any Dao, Creator exception has occurred.
     */
    Optional<Pet> getByUserId(String userId) throws ServiceException;

    /**
     * Find and return pet photo urls from database.
     *
     * @param id user id who has pet, which have photo urls.
     * @return ArrayList of String:
     * <ol>
     * <li>empty ArrayList if were not found urls.</li>
     * </ol>
     * @throws ServiceException if any Dao exception has occurred.
     */
    List<String> getPetPhotosById(int id) throws ServiceException;

    /**
     * Adding photo in the database,and upload image to server.
     *
     * @param part   new pet image.
     * @param path   path to the avatar folder.
     * @param userId id of the user to which have pet.
     * @throws ServiceException if any Dao, Creator exception has occurred.
     */
    void uploadPhoto(Part part, String path, String userId) throws ServiceException;

    /**
     * Find and delete pet photo urls from database and server.
     *
     * @param id   user id who has pet, which have photo urls.
     * @param urls List of urls to be deleted.
     * @throws ServiceException if any Dao exception has occurred.
     */
    void deletePhotos(int id, List<String> urls) throws ServiceException;

    List<String> getPagingPhotoCount(List<String> petPhotoUrl, int from);

    /**
     * Find and return pets from database without authorized user.
     *
     * @param id authorized user.
     * @return Optional containing either:
     * <ol>
     * <li>Pets contain fields userId, name, breed, age, avatarUrl  not null.</li>
     * <li><code>null</code>, if pets were not found.</li>
     * </ol>
     * @throws ServiceException if any Dao exception has occurred.
     */
    Optional<List<Pet>> getAllPetsNoUser(int id) throws ServiceException;

    /**
     * Find and return pets by pet type from database without authorized user.
     *
     * @param id      authorized user.
     * @param petType pet type.
     * @return Optional containing either:
     * <ol>
     * <li>Pets contain fields userId, name, breed, age, avatarUrl  not null.</li>
     * <li><code>null</code>, if pets were not found.</li>
     * </ol>
     * @throws ServiceException if any Dao exception has occurred.
     */
    Optional<List<Pet>> getByTypeNoUser(String petType, int id) throws ServiceException;

    /**
     * Find and return pets by search value from the database.
     *
     * @param from        id of the pet, from which the search will be performed inclusively.
     * @param to          id of the pet to which the search will be performed inclusively.
     * @param searchValue part or all of the pet’s name to be searched in the database.
     * @return Optional containing either:
     * <ol>
     * <li>Pets contain fields userId, name, breed, age, type  not null.</li>
     * <li><code>null</code>, if users were not found.</li>
     * </ol>
     * @throws ServiceException if any Dao, Creator exception has occurred.
     */
    Optional<List<Pet>> getFromTo(int from, int to, String searchValue) throws ServiceException;

}

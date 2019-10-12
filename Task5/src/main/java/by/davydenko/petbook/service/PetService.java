package by.davydenko.petbook.service;

import by.davydenko.petbook.entity.Pet;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Optional;

public interface PetService extends Service<Pet> {

    void uploadName(String name, String userId) throws ServiceException;

    void uploadBreed(String breed, String userId) throws ServiceException;

    void uploadAvatar(Part image, String path, String userId) throws ServiceException;

    void uploadAge(String age, String userId) throws ServiceException;

    Optional<List<Pet>> getMessageSenders(String userId) throws ServiceException;

    Optional<Pet> getSender(String userId) throws ServiceException;

    Optional<Pet> getReceiver(String userId) throws ServiceException;

    void registerByUserId(int userId) throws ServiceException;

    int getDogPercent() throws ServiceException;

    int getCatPercent() throws ServiceException;

    int getBirdPercent() throws ServiceException;

    int getOtherPercent() throws ServiceException;

    Optional<List<Pet>> getByType(String type) throws ServiceException;

    Optional<List<Pet>> getAllPets() throws ServiceException;

    Optional<Pet> getByUserId(String userId) throws ServiceException;

    List<String> getPetPhotosById(int id) throws ServiceException;

    List<String> getPetPhotosById(int id, int from) throws ServiceException;

    void uploadPhoto(Part part, String path, String userId) throws ServiceException;

    void deletePhotos(int id, List<String> urls)throws ServiceException;

    List<String> getPagingPhotoCount(List<String> petPhotoUrl, int from);

    void uploadType(String petType, String userId) throws ServiceException;

    Optional<List<Pet>> getAllPetsNoUser(int id) throws ServiceException;

    Optional<List<Pet>> getByTypeNoUser(String petType, int id) throws ServiceException;

    Optional<List<Pet>> getFromTo(int from, int to) throws ServiceException;

    Optional<List<Pet>> getFromTo(int from, int to,String searchValue) throws ServiceException;


}

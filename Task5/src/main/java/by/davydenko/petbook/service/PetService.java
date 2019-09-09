package by.davydenko.petbook.service;

import by.davydenko.petbook.entity.Pet;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface PetService extends Service<Pet> {

    Optional<Pet> getPetByUserId(HttpServletRequest request) throws ServiceException;

    void uploadName(HttpServletRequest request) throws ServiceException;

    void uploadBreed(HttpServletRequest request) throws ServiceException;

    void uploadAvatar(HttpServletRequest request) throws ServiceException;

    void uploadAge(HttpServletRequest request) throws ServiceException;

    Optional<List<Pet>> getMessageSenders(HttpServletRequest request) throws ServiceException;

    Optional<Pet> getSender(HttpServletRequest request) throws ServiceException;

    Optional<Pet> getReceiver(HttpServletRequest request) throws ServiceException;

    void registerPet(HttpServletRequest request) throws ServiceException;

    int getDogPrefer() throws ServiceException;

    int getCatPrefer() throws ServiceException;

    int getBirdPrefer() throws ServiceException;

    int getOtherPrefer() throws ServiceException;
}

package by.davydenko.petbook.service.util.creator;

import by.davydenko.petbook.entity.Pet;
import by.davydenko.petbook.entity.PetType;

import javax.servlet.http.HttpServletRequest;

public interface PetCreator extends Creator<Pet> {
    @Override
    default Pet create(){return null;}

    String createBreed(HttpServletRequest request) throws CreatorException;

    String createName(HttpServletRequest request) throws CreatorException;

    int createAge(HttpServletRequest request) throws CreatorException;

    int createCorrespondentId(HttpServletRequest request) throws CreatorException;

    int createReceiverId(HttpServletRequest request) throws CreatorException;

    PetType createType(HttpServletRequest request) throws CreatorException;

    int idByUserId(HttpServletRequest request) throws CreatorException;

    int byId(HttpServletRequest request) throws CreatorException;
}

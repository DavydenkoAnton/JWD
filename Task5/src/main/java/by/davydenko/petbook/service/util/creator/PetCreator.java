package by.davydenko.petbook.service.util.creator;

import by.davydenko.petbook.entity.Pet;
import by.davydenko.petbook.entity.PetType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.nio.file.Path;

public interface PetCreator extends Creator<Pet> {
    @Override
    default Pet create(){return null;}

    String createBreed(String breed) throws CreatorException;

    String createName(String name) throws CreatorException;

    int createAge(String age) throws CreatorException;

    int createCorrespondentId(HttpServletRequest request) throws CreatorException;

    int createReceiverId(HttpServletRequest request) throws CreatorException;

    PetType createType(String type) throws CreatorException;

    int idByUserId(String userId) throws CreatorException;

    int userId(String userId) throws CreatorException;

    String imageName(Part image, int id) throws CreatorException;

    Path outputFile(String path, String imageName) throws CreatorException;

    int createFromValue(String from) throws CreatorException;

    int createToValue(int from, String direction);
}

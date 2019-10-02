package by.davydenko.petbook.service.util.creator.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.PetType;
import by.davydenko.petbook.service.util.creator.CreatorException;
import by.davydenko.petbook.service.util.creator.PetCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PetCreatorImpl implements PetCreator {

    private static final int MAX_BREED_LENGTH = 16;
    private static final String JPEG_FORMAT = "image/jpeg";

    @Override
    public String createBreed(String breed) throws CreatorException {
        if (breed == null) {
            throw new CreatorException("breed is null");
        } else if (breed.isEmpty()) {
            throw new CreatorException("breed is empty");
        } else if (breed.length() > MAX_BREED_LENGTH) {
            throw new CreatorException("breed length is more than 16 symbols");
        }
        return breed;
    }

    @Override
    public PetType createType(String type) throws CreatorException {
        PetType petType;
        if (type == null) {
            throw new CreatorException("pet type is null");
        } else if (type.isEmpty()) {
            throw new CreatorException("pet type is empty");
        }
        try {
            petType = PetType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CreatorException("pet type argument is illegal", e);
        }
        return petType;
    }

    @Override
    public String createName(String name) throws CreatorException {
        if (name == null) {
            throw new CreatorException("name is null");
        } else if (name.isEmpty()) {
            throw new CreatorException("name is empty");
        } else if (name.length() > MAX_BREED_LENGTH) {
            throw new CreatorException("name length is more than 16 symbols");
        }
        return name;
    }

    @Override
    public int createAge(String age) throws CreatorException {
        if (age == null) {
            throw new CreatorException("age is null");
        } else if (age.isEmpty()) {
            throw new CreatorException("age is empty");
        }
        int petAge;
        try {
            petAge = Integer.valueOf(age);
        } catch (NumberFormatException e) {
            throw new CreatorException("age argument is illegal", e);
        }
        return petAge;
    }

    @Override
    public int idByUserId(String userId) throws CreatorException {
        int id;
        try {
            id = Integer.valueOf(userId);
        } catch (NumberFormatException e) {
            throw new CreatorException("wrong id format");
        }
        if (id <= 0) {
            throw new CreatorException("pet user id less or equals zero");
        }
        return id;
    }

    @Override
    public int userId(String userId) throws CreatorException {
        int id;
        if (userId != null) {
            try {
                id = Integer.valueOf(userId);
            } catch (NumberFormatException e) {
                throw new CreatorException("wrong user id format");
            }
            if (id <= 0) {
                throw new CreatorException("user id less or equals zero");
            }
        } else {
            id = 0;
        }
        return id;
    }

    @Override
    public String imageName(Part image, int id) throws CreatorException {
        String imageName;
        String contentType = image.getContentType();
        if (contentType.equalsIgnoreCase(JPEG_FORMAT)) {
            imageName = image.getSubmittedFileName().replaceAll(".jpg", "");
            imageName += id + ".jpg";
        } else {
            throw new CreatorException("wrong image format for pet avatar");
        }
        return imageName;
    }

    @Override
    public Path outputFile(String path, String imageName) throws CreatorException {
        Path outputFile;
        try {
            outputFile = Paths.get(path, imageName);
        } catch (InvalidPathException e) {
            throw new CreatorException("Invalid path for pet avatar", e);
        }
        return outputFile;
    }

    @Override
    public int createFromValue(String from) throws CreatorException {
        int fromBuf;
        if (from != null) {
            try {
                fromBuf = Integer.valueOf(from);
            } catch (NumberFormatException e) {
                throw new CreatorException("wrong paging from value");
            }
        } else {
            fromBuf = 0;
        }
        return fromBuf;
    }

    @Override
    public int createToValue(int from, String direction) {
        switch (direction) {
            case "prev":

                break;
            case "next":
                break;
        }

        return 0;
    }

    @Override
    public int createCorrespondentId(HttpServletRequest request) throws CreatorException {
        int senderId;

        try {
            senderId = Integer.valueOf(request.getParameter(Attribute.MESSAGE_SENDER_ID));
        } catch (NumberFormatException e) {
            throw new CreatorException("wrong sender id format", e);
        }
        return senderId;
    }

    @Override
    public int createReceiverId(HttpServletRequest request) throws CreatorException {
        int senderId;

        try {
            senderId = (int) request.getSession().getAttribute(Attribute.ID);
        } catch (ClassCastException e) {
            throw new CreatorException("wrong receiver id format", e);
        }
        return senderId;
    }
}

package by.davydenko.petbook.service.util.creator.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.PetType;
import by.davydenko.petbook.service.util.creator.Creator;
import by.davydenko.petbook.service.util.creator.CreatorException;
import by.davydenko.petbook.service.util.creator.PetCreator;

import javax.servlet.http.HttpServletRequest;

public class PetCreatorImpl implements PetCreator {

    @Override
    public String createBreed(HttpServletRequest request) throws CreatorException {
        String breed = request.getParameter(Attribute.BREED);
        if (breed == null || breed.isEmpty()) {
            throw new CreatorException("breed incorrect");
        }
        return breed;
    }

    @Override
    public PetType createType(HttpServletRequest request) throws CreatorException {
        String type = request.getParameter(Attribute.PET_TYPE);
        PetType petType = null;
        if (type != null) {
            try {
                petType = PetType.valueOf(type.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new CreatorException("pet type incorrect", e);
            }
        }
        return petType;
    }

    @Override
    public String createName(HttpServletRequest request) throws CreatorException {
        String name = request.getParameter(Attribute.NAME);
        if (name == null || name.isEmpty()) {
            throw new CreatorException("breed incorrect");
        }
        return name;
    }

    @Override
    public int createAge(HttpServletRequest request) throws CreatorException {
        int age;
        try {
            age = Integer.valueOf(request.getParameter(Attribute.AGE));
        } catch (NumberFormatException e) {
            throw new CreatorException("wrong id format");
        }
        if (age <= 0) {
            throw new CreatorException("wrong id format");
        }

        return age;

    }

    @Override
    public int idByUserId(HttpServletRequest request) throws CreatorException {
        int id;
        try {
            id = Integer.valueOf(request.getParameter("userId"));
        } catch (NumberFormatException e) {
            throw new CreatorException("wrong id format");
        }
        if (id <= 0) {
            throw new CreatorException("wrong id format");
        }
        return id;
    }

    @Override
    public int byId(HttpServletRequest request) throws CreatorException {
        int id;
        try {
            id = (int)request.getSession().getAttribute(Attribute.ID);
        } catch (ClassCastException e) {
            throw new CreatorException("wrong authorized user id format");
        }
        if (id <= 0) {
            throw new CreatorException("wrong authorized user id format");
        }
        return id;
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

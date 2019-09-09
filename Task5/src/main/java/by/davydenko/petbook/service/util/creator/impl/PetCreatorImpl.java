package by.davydenko.petbook.service.util.creator.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.service.util.creator.CreatorException;
import by.davydenko.petbook.service.util.creator.PetCreator;

import javax.servlet.http.HttpServletRequest;

public class PetCreatorImpl implements PetCreator {

    @Override
    public String createBreed(HttpServletRequest request) throws CreatorException {
        String breed = request.getParameter("breed");
        if (breed == null || breed.isEmpty()) {
            throw new CreatorException("breed incorrect");
        }
        return breed;
    }

    @Override
    public String createName(HttpServletRequest request) throws CreatorException {
        String name = request.getParameter("breed");
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
    public int createCorrespondentId(HttpServletRequest request) throws CreatorException {
        int senderId;

        try {
            senderId = Integer.valueOf(request.getParameter(Attribute.MESSAGE_SENDER_ID));
        } catch (NumberFormatException e) {
            throw new CreatorException("wrong sender id format",e);
        }
        return senderId;
    }

    @Override
    public int createReceiverId(HttpServletRequest request) throws CreatorException {
        int senderId;

        try {
            senderId = (int)request.getSession().getAttribute(Attribute.ID);
        } catch (ClassCastException e) {
            throw new CreatorException("wrong receiver id format",e);
        }
        return senderId;
    }
}

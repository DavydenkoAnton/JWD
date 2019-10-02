package by.davydenko.petbook.service.util.creator.impl;

import by.davydenko.petbook.entity.Article;
import by.davydenko.petbook.entity.PetType;
import by.davydenko.petbook.service.util.creator.ArticleCreator;
import by.davydenko.petbook.service.util.creator.CreatorException;

public class ArticleCreatorImpl implements ArticleCreator {

    private static final int MAX_DESCRIPTION_LENGTH = 320;
    private static final int MAX_TEXT_LENGTH = 6000;

    @Override
    public Article create() {
        return new Article();
    }

    @Override
    public PetType createType(String type) throws CreatorException {
        PetType petType;
        if (type != null) {
            try {
                petType = PetType.valueOf(type.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new CreatorException("wrong pet type");
            }
        } else {
            petType = PetType.OTHER;
        }
        return petType;
    }

    @Override
    public String createTitle(String title) throws CreatorException {
        if (title == null) {
            throw new CreatorException("title is null");
        } else if (title.isEmpty()) {
            throw new CreatorException("title is empty");
        }
        return title;
    }

    @Override
    public String createDescription(String description) throws CreatorException {
        if (description == null) {
            throw new CreatorException("description is null");
        } else if (description.isEmpty()) {
            throw new CreatorException("description is empty");
        } else if (description.length() > MAX_DESCRIPTION_LENGTH) {
            throw new CreatorException("description is more than " + MAX_DESCRIPTION_LENGTH + " symbols ("+description.length()+")");
        }
        return description;
    }

    @Override
    public String createText(String text) throws CreatorException {
        if (text == null) {
            throw new CreatorException("text is null");
        } else if (text.isEmpty()) {
            throw new CreatorException("text is empty");
        } else if (text.length() > MAX_TEXT_LENGTH) {
            throw new CreatorException("text is more than " + MAX_TEXT_LENGTH + " symbols ("+text.length()+")");
        }
        return text;
    }

    @Override
    public int creatId(String id) throws CreatorException {
        int idTemp = 0;
        if (id == null) {
            throw new CreatorException("id is null");
        } else if (id.isEmpty()) {
            throw new CreatorException("id is empty");
        } else {
            try {
                idTemp = Integer.valueOf(id);
            } catch (NumberFormatException e) {
                throw new CreatorException(e);
            }
        }
        return idTemp;
    }
}

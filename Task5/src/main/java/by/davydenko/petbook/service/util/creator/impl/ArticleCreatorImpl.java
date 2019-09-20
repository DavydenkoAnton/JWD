package by.davydenko.petbook.service.util.creator.impl;

import by.davydenko.petbook.entity.Article;
import by.davydenko.petbook.entity.PetType;
import by.davydenko.petbook.service.util.creator.ArticleCreator;
import by.davydenko.petbook.service.util.creator.CreatorException;

public class ArticleCreatorImpl implements ArticleCreator {

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
}

package by.davydenko.petbook.service.util.creator;

import by.davydenko.petbook.entity.Article;
import by.davydenko.petbook.entity.PetType;

import javax.servlet.http.HttpServletRequest;

public interface ArticleCreator extends Creator<Article> {

    PetType createType(String petType) throws CreatorException;

    String createTitle(String title) throws CreatorException;
}

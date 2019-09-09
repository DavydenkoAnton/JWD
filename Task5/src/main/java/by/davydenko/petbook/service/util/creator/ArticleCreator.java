package by.davydenko.petbook.service.util.creator;

import by.davydenko.petbook.entity.Article;
import by.davydenko.petbook.entity.ArticleType;
import by.davydenko.petbook.service.util.creator.Creator;

import javax.servlet.http.HttpServletRequest;

public interface ArticleCreator extends Creator<Article> {

    ArticleType createType(HttpServletRequest request);

    String createTitle(HttpServletRequest request) throws CreatorException;
}

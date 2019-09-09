package by.davydenko.petbook.service.util.creator.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.Article;
import by.davydenko.petbook.entity.ArticleType;
import by.davydenko.petbook.service.util.creator.ArticleCreator;
import by.davydenko.petbook.service.util.creator.CreatorException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ArticleCreatorImpl implements ArticleCreator {

    @Override
    public Article create() {
        return new Article();
    }

    @Override
    public ArticleType createType(HttpServletRequest request) {
        String articleType = request.getParameter(Attribute.ARTICLE_TYPE);
        if (articleType != null) {
            switch (articleType) {
                case Attribute.DOGS:
                    return ArticleType.DOG;
                case Attribute.CATS:
                    return ArticleType.CAT;
                case Attribute.BIRDS:
                    return ArticleType.BIRD;
            }
        }
        return ArticleType.OTHERS;
    }

    @Override
    public String createTitle(HttpServletRequest request) throws CreatorException {
        String articleTitle = request.getParameter(Attribute.ARTICLE_TITLE);
        if(articleTitle==null ||articleTitle.isEmpty()){
            throw new CreatorException("article title is null or empty");
        }
        return articleTitle;
    }
}

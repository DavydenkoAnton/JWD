package by.davydenko.greenhouse.service;

import by.davydenko.greenhouse.entity.Flower;
import by.davydenko.greenhouse.service.parser.ParserFactory;
import by.davydenko.greenhouse.service.parser.FlowerXMLParser;
import by.davydenko.greenhouse.service.parser.FlowerXMLParserDOMException;

import java.util.List;

public final class XMLServiceImpl implements XMLService {

    private ParserFactory parserFactory = ParserFactory.getInstance();
    private FlowerXMLParser flowerXmlParser;
    private List<Flower> flowersList;

    @Override
    public List<Flower> parseFlowers(String pathFile, ParserFactory.XMLParserType XMLParserType) throws FlowerXMLParserDOMException {
        switch (XMLParserType) {
            case DOM:
                flowerXmlParser = parserFactory.getXMLParser(ParserFactory.XMLParserType.DOM);
                flowersList = flowerXmlParser.parse(pathFile);
                break;
            case SAX:
                flowerXmlParser = parserFactory.getXMLParser(ParserFactory.XMLParserType.SAX);
                flowerXmlParser.parse(pathFile);
                break;
            case STAX:
                flowerXmlParser = parserFactory.getXMLParser(ParserFactory.XMLParserType.STAX);
                break;
        }

        //flowersList.forEach(flower -> System.out.println(flower));
        return flowersList;
    }


}
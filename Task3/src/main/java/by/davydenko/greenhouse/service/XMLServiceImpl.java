package by.davydenko.greenhouse.service;

import by.davydenko.greenhouse.entity.Flower;
import by.davydenko.greenhouse.entity.FlowerBuilderException;
import by.davydenko.greenhouse.service.parser.*;

import java.io.FileNotFoundException;
import java.util.List;

public final class XMLServiceImpl implements XMLService {

    private ParserFactory parserFactory = ParserFactory.getInstance();
    private FlowerXMLParser flowerXmlParser;
    private List<Flower> flowersList;

    @Override
    public List<Flower> parseFlowers(String pathFile, ParserFactory.XMLParserType XMLParserType) throws FlowerXMLParserDOMException, FlowerXMLParserSAXException, FileNotFoundException, FlowerXMLParserSTAXException {
        switch (XMLParserType) {
            case DOM:
                flowerXmlParser = parserFactory.getXMLParser(ParserFactory.XMLParserType.DOM);
                flowersList = flowerXmlParser.parse(pathFile);
                break;
            case SAX:
                flowerXmlParser = parserFactory.getXMLParser(ParserFactory.XMLParserType.SAX);
                flowersList = flowerXmlParser.parse(pathFile);
                break;
            case STAX:
                flowerXmlParser = parserFactory.getXMLParser(ParserFactory.XMLParserType.STAX);
                flowersList = flowerXmlParser.parse(pathFile);
                break;
        }

        return flowersList;
    }


}
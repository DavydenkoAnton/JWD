package by.davydenko.greenhouse.service;

import by.davydenko.greenhouse.service.parser.ParserFactory;
import by.davydenko.greenhouse.service.parser.XMLParser;

public class XMLServiceImpl implements XMLService {

    private ParserFactory parserFactory = ParserFactory.getInstance();
    private XMLParser xmlParser;


    @Override
    public void parse(String pathFile, ParserFactory.XMLParserType XMLParserType) {
        switch (XMLParserType) {
            case DOM:
                xmlParser = parserFactory.getXMLParser(ParserFactory.XMLParserType.DOM);
                break;
            case SAX:
                xmlParser = parserFactory.getXMLParser(ParserFactory.XMLParserType.SAX);
                break;
            case STAX:
                xmlParser = parserFactory.getXMLParser(ParserFactory.XMLParserType.STAX);
                break;
        }
        xmlParser.parse(pathFile);
    }

}
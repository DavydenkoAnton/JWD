package by.davydenko.greenhouse.service;

import by.davydenko.greenhouse.service.parser.ParserFactory;
import by.davydenko.greenhouse.service.parser.XMLParser;

public class XMLServiceImpl implements XMLService {

    private ParserFactory parserFactory = ParserFactory.getInstance();
    private XMLParser xmlParser;


    @Override
    public void parse(String pathFile, ParserFactory.ParserType parserType) {
        switch (parserType) {
            case DOM:
                xmlParser = parserFactory.getXMLParser(ParserFactory.ParserType.DOM);
                break;
            case SAX:
                xmlParser = parserFactory.getXMLParser(ParserFactory.ParserType.SAX);
                break;
            case STAX:
                xmlParser = parserFactory.getXMLParser(ParserFactory.ParserType.STAX);
                break;
        }
        xmlParser.parse(pathFile);
    }

}
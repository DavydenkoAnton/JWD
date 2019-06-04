package by.davydenko.greenhouse.service.parser;

public final class ParserFactory {

    private static ParserFactory instance;
    private XMLParser xmlParser;

    public enum ParserType {
        DOM,
        SAX,
        STAX
    }

    private ParserFactory() {
    }

    public static ParserFactory getInstance() {
        if (instance == null) {
            instance = new ParserFactory();
        }
        return instance;
    }

    public XMLParser getXMLParser(ParserType parserType) {
        switch (parserType) {
            case DOM:
                xmlParser = new XMLParserDOMImpl();
                break;
            case SAX:
                xmlParser = new XMLParserSAXImpl();
                break;
            case STAX:
                xmlParser = new XMLParserSTAXImpl();
                break;
        }
        return xmlParser;
    }
}

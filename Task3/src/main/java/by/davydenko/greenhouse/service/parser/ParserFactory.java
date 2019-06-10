package by.davydenko.greenhouse.service.parser;

public final class ParserFactory {

    private static ParserFactory instance;
    private FlowerXMLParser flowerXmlParser;

    public enum XMLParserType {
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

    public FlowerXMLParser getXMLParser(XMLParserType XMLParserType) {
        switch (XMLParserType) {
            case DOM:
                flowerXmlParser = new FlowerXMLParserDOMImpl();
                break;
            case SAX:
                flowerXmlParser = new FlowerXMLParserSAXImpl();
                break;
            case STAX:
                flowerXmlParser = new FlowerXMLParserSTAXImpl();
                break;
        }
        return flowerXmlParser;
    }
}

package by.davydenko.greenhouse.service.parser;

public class FlowerXMLParserSAXException extends Exception {
    public FlowerXMLParserSAXException(){super();}
    public FlowerXMLParserSAXException(String name, Throwable exception){super(name,exception);}
    public FlowerXMLParserSAXException(String name){super(name);}
    public FlowerXMLParserSAXException(Throwable exception){super(exception);}
}

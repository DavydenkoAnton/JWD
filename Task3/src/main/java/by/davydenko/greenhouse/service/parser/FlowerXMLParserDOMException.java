package by.davydenko.greenhouse.service.parser;

public class FlowerXMLParserDOMException extends Exception {
    public FlowerXMLParserDOMException(){super();}
    public FlowerXMLParserDOMException(String name, Throwable exception){super(name,exception);}
    public FlowerXMLParserDOMException(String name){super(name);}
    public FlowerXMLParserDOMException(Throwable exception){super(exception);}
}

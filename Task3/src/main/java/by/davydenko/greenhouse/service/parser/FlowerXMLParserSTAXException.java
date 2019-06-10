package by.davydenko.greenhouse.service.parser;

public class FlowerXMLParserSTAXException extends Exception {
    public FlowerXMLParserSTAXException(){super();}
    public FlowerXMLParserSTAXException(String name, Throwable exception){super(name,exception);}
    public FlowerXMLParserSTAXException(String name){super(name);}
    public FlowerXMLParserSTAXException(Throwable exception){super(exception);}
}

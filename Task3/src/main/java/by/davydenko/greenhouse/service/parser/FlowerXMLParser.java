package by.davydenko.greenhouse.service.parser;

import by.davydenko.greenhouse.entity.Flower;

import java.io.FileNotFoundException;
import java.util.List;

public interface FlowerXMLParser {
    public List<Flower> parse(String pathFile) throws FlowerXMLParserDOMException, FlowerXMLParserSAXException, FileNotFoundException, FlowerXMLParserSTAXException;
}

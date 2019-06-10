package by.davydenko.greenhouse.service;

import by.davydenko.greenhouse.entity.Flower;
import by.davydenko.greenhouse.service.parser.ParserFactory;
import by.davydenko.greenhouse.service.parser.FlowerXMLParserDOMException;

import java.util.List;

public interface XMLService {
    List<Flower> parseFlowers(String path, ParserFactory.XMLParserType XMLParserType) throws FlowerXMLParserDOMException;
}

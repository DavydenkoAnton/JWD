package by.davydenko.greenhouse.service.parser;

import by.davydenko.greenhouse.entity.Flower;

import java.util.List;

public class XMLParserSTAXImpl implements XMLParser {
    private List<Flower> nodes = null;

    @Override
    public List<Flower> parse(String pathFile) {
        return nodes;
    }
}

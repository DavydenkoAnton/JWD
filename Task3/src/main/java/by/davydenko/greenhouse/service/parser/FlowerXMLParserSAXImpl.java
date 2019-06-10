package by.davydenko.greenhouse.service.parser;


import by.davydenko.greenhouse.entity.Flower;

import java.util.ArrayList;
import java.util.List;

public final class FlowerFlowerXMLParserSAXImpl implements FlowerXMLParser {
    private List<Flower> flowers;

    @Override
    public List<Flower> parse(String pathFile) {
        flowers = new ArrayList<>();


        return flowers;
    }
}

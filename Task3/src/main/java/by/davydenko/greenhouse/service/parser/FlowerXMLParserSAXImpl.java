package by.davydenko.greenhouse.service.parser;


import by.davydenko.greenhouse.entity.Flower;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public final class FlowerXMLParserSAXImpl implements FlowerXMLParser {
    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

    @Override
    public List<Flower> parse(String pathFile) throws FlowerXMLParserSAXException {
        List<Flower> flowerList = null;
        SAXParser saxParser = null;
        FlowerSAXHandler handler = new FlowerSAXHandler();
        try {
            saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(new File(pathFile), handler);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new FlowerXMLParserSAXException(e);
        }

        flowerList = handler.getFlowerList();

        return flowerList;
    }
}

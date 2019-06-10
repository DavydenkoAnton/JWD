package by.davydenko.greenhouse.service.parser;


import by.davydenko.greenhouse.entity.Flower;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class FlowerXMLParserSAXImpl implements FlowerXMLParser {
    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

    @Override
    public List<Flower> parse(String pathFile) throws FlowerXMLParserDOMException {
        List<Flower> flowerList = null;
        SAXParser saxParser = null;
        FlowerSAXHandler handler = new FlowerSAXHandler();
        try {
            saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(new File(pathFile), handler);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        flowerList = handler.getFlowerList();

        return null;
    }
}

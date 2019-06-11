package by.davydenko.greenhouse.service.parser;

import by.davydenko.greenhouse.entity.Flower;
import by.davydenko.greenhouse.entity.FlowerBuilder;
import by.davydenko.greenhouse.entity.FlowerBuilderImpl;
import org.apache.log4j.Logger;
import org.junit.*;

import java.io.FileNotFoundException;
import java.util.List;

public class FlowerXMLParserDOMImplTest {

    private final static Logger LOGGER = Logger.getLogger(FlowerXMLParserDOMImplTest.class);
    private Flower flower;
    List<Flower> flowerList;
    private FlowerBuilder flowerBuilder;
    private ParserFactory parserFactory;
    private FlowerXMLParser flowerXMLParser;
    private String path;


    @Before
    public void init() {
        path = "src/test/java/resourcestest/greenhouse_test.xml";
        parserFactory = ParserFactory.getInstance();
        flowerXMLParser = parserFactory.getXMLParser(ParserFactory.XMLParserType.DOM);
        flowerBuilder = new FlowerBuilderImpl();
        flowerBuilder.setID("50");
        flowerBuilder.setName("test");
        flowerBuilder.setSoil("send");
        flowerBuilder.setOriginCountry("by");
        flowerBuilder.setLeafColor("red");
        flowerBuilder.setStemColor("red");
        flowerBuilder.setHeight("1");
        flowerBuilder.setWeight("50");
        flowerBuilder.setTemperature("50");
        flowerBuilder.setPhotophilous("true");
        flowerBuilder.setWatering("50");
        flowerBuilder.setMultiplying("bySeed");
        flower=flowerBuilder.getFlower();
    }


    @Test
    public void parse() {
        try {
            flowerList = flowerXMLParser.parse(path);
        } catch (FlowerXMLParserDOMException | FlowerXMLParserSAXException | FileNotFoundException | FlowerXMLParserSTAXException e) {
            LOGGER.error(e);
        }
        Assert.assertEquals(flower.toString(), flowerList.get(0).toString());
    }

    @After
    public void finalize() {
        flower = null;
    }
}

package by.davydenko.greenhouse.presentation;

import by.davydenko.greenhouse.entity.Flower;
import by.davydenko.greenhouse.service.ServiceFactory;
import by.davydenko.greenhouse.service.XMLService;
import by.davydenko.greenhouse.service.parser.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import java.io.FileNotFoundException;
import java.util.List;


class Runner {
    public static void main(String[] args) {

        String flowersXMLPath = "src/main/resources/greenhouse.xml";
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        XMLService xmlService = serviceFactory.getService(ServiceFactory.ServiceType.XML);
        List<Flower> flowerList;
/*
        try {
            flowerList = xmlService.parseFlowers(flowersXMLPath, ParserFactory.XMLParserType.DOM);
            System.out.println("DOM parser:\n---------------------------------");
            flowerList.forEach(System.out::println);
        } catch (FlowerXMLParserDOMException | FlowerXMLParserSAXException e) {
            e.printStackTrace();
        }

        try {
            flowerList = xmlService.parseFlowers(flowersXMLPath, ParserFactory.XMLParserType.SAX);
            System.out.println("SAX parser:\n---------------------------------");
            flowerList.forEach(System.out::println);
        } catch (FlowerXMLParserSAXException | FlowerXMLParserDOMException e) {
            e.printStackTrace();
        }
*/
        try {
            flowerList = xmlService.parseFlowers(flowersXMLPath, ParserFactory.XMLParserType.STAX);
            System.out.println("STAX parser:\n---------------------------------");
            flowerList.forEach(System.out::println);
        } catch (FlowerXMLParserDOMException | FlowerXMLParserSAXException | FileNotFoundException | FlowerXMLParserSTAXException e) {
            e.printStackTrace();
        }

    }
}

package by.davydenko.greenhouse.presentation;

import by.davydenko.greenhouse.entity.Flower;
import by.davydenko.greenhouse.service.ServiceFactory;
import by.davydenko.greenhouse.service.XMLService;
import by.davydenko.greenhouse.service.parser.FlowerSAXHandler;
import by.davydenko.greenhouse.service.parser.ParserFactory;
import by.davydenko.greenhouse.service.parser.FlowerXMLParserDOMException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import java.util.List;


class Runner {
    public static void main(String[] args) {

        String flowersXMLPath = "src/main/resources/greenhouse.xml";
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        XMLService xmlService = serviceFactory.getService(ServiceFactory.ServiceType.XML);
        try {
            List<Flower> flowerList = xmlService.parseFlowers(flowersXMLPath, ParserFactory.XMLParserType.DOM);
        } catch (FlowerXMLParserDOMException e) {
            e.printStackTrace();
        }
        try {
            xmlService.parseFlowers(flowersXMLPath, ParserFactory.XMLParserType.SAX);
        } catch (FlowerXMLParserDOMException e) {
            e.printStackTrace();
        }

    }
}

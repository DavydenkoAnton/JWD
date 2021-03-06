package by.davydenko.greenhouse.controller.command.Impl;


import by.davydenko.greenhouse.controller.command.Command;
import by.davydenko.greenhouse.entity.Flower;
import by.davydenko.greenhouse.service.ServiceFactory;
import by.davydenko.greenhouse.service.XMLService;
import by.davydenko.greenhouse.service.parser.FlowerXMLParserDOMException;
import by.davydenko.greenhouse.service.parser.FlowerXMLParserSAXException;
import by.davydenko.greenhouse.service.parser.FlowerXMLParserSTAXException;
import by.davydenko.greenhouse.service.parser.ParserFactory;

import java.io.FileNotFoundException;
import java.util.List;

public class FlowerParseDOM implements Command {

    public String execute(String request) throws FileNotFoundException, FlowerXMLParserDOMException, FlowerXMLParserSAXException, FlowerXMLParserSTAXException {

        String response = "DOM PARSER:\n----------------------\n";
        String flowersXMLPath = "src/main/resources/greenhouse.xml";
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        XMLService xmlService = serviceFactory.getService(ServiceFactory.ServiceType.XML);
        List<Flower> flowerList;

        flowerList = xmlService.parseFlowers(flowersXMLPath, ParserFactory.XMLParserType.DOM);
        for (Flower f : flowerList) {
            response += f.toString();
        }

        return response;
    }

}

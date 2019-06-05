package by.davydenko.greenhouse.presentation;

import by.davydenko.greenhouse.service.ServiceFactory;
import by.davydenko.greenhouse.service.XMLService;
import by.davydenko.greenhouse.service.parser.ParserFactory;


class Runner {
    public static void main(String[] args) {

        String flowersXMLPath = "src/main/resources/greenhouse.xml";
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        XMLService xmlService = serviceFactory.getService(ServiceFactory.ServiceType.XML);
        xmlService.parse(flowersXMLPath, ParserFactory.XMLParserType.DOM);


    }
}

package by.davydenko.greenhouse.presentation;

import by.davydenko.greenhouse.service.ServiceFactory;
import by.davydenko.greenhouse.service.XMLService;
import by.davydenko.greenhouse.service.parser.ParserFactory;



 class Task3 {
    public static void main(String[] args) {


        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        XMLService xmlService = serviceFactory.getService(ServiceFactory.SERVICE_TYPE.XML);
        xmlService.parse("src/main/resources/greenhouse.xml", ParserFactory.ParserType.DOM);


    }
}

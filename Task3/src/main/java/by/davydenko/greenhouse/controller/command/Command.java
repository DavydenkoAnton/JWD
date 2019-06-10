package by.davydenko.greenhouse.controller.command;

import by.davydenko.greenhouse.entity.FlowerBuilderException;
import by.davydenko.greenhouse.service.parser.FlowerXMLParserDOMException;
import by.davydenko.greenhouse.service.parser.FlowerXMLParserSAXException;
import by.davydenko.greenhouse.service.parser.FlowerXMLParserSTAXException;

import java.io.FileNotFoundException;

public interface Command {
    String execute(String request) throws FileNotFoundException, FlowerXMLParserDOMException, FlowerXMLParserSAXException, FlowerXMLParserSTAXException;
}


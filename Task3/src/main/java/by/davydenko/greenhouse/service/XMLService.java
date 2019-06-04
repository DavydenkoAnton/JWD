package by.davydenko.greenhouse.service;

import by.davydenko.greenhouse.service.parser.ParserFactory;

public interface XMLService {
    void parse(String path, ParserFactory.ParserType parserType);
}

package by.javatr.service.parser;

public interface ParserChain<T> {
    T parseLine(String line);
    ParserChain<T> linkWith(ParserChain<T> next);
}

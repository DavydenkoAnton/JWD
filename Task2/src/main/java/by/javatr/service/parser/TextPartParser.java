package by.javatr.service.parser;

import by.javatr.entity.composite.SmartText;

public abstract class TextPartParser implements ParserChain<SmartText> {

    private TextPartParser next;

    @Override
    public ParserChain<SmartText> linkWith(ParserChain<SmartText> next) {
        ((TextPartParser) next).next = this;
        return next;
    }

    protected SmartText nextParse(String line) {

        if (next != null) {
            return next.parseLine(line);
        } else {
            return null;
        }
    }
}

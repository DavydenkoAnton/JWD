package by.javatr.service.parser;

import by.javatr.entity.composite.SmartText;
import by.javatr.entity.Word;

import static by.javatr.entity.composite.SmartText.TextTypeFunction.WORD;

public class WordParser extends TextPartParser {
    @Override
    public SmartText parseLine(String line) {
        Word word = new Word(WORD);
        word.setWord(line);
        return word;
    }
}

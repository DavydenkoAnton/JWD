package by.javatr.service.parser;

import by.javatr.entity.composite.SmartText;
import by.javatr.entity.Paragraphe;

import static by.javatr.entity.composite.SmartText.TextTypeFunction.PARAGRAPHE;

public class ParagrapheParser extends TextPartParser {
    @Override
    public SmartText parseLine(String line) {
        Paragraphe paragraphe = new Paragraphe(PARAGRAPHE);
        line = line.replaceAll("\t", "");
        for (String sentence : line.split("\n")) {
            if (!sentence.isEmpty()) {
                paragraphe.add(nextParse(sentence));
            }
        }
        return paragraphe;
    }
}

package by.javatr.service.parser;

import by.javatr.entity.composite.SmartText;
import by.javatr.entity.Sentence;

import static by.javatr.entity.composite.SmartText.TextTypeFunction.SENTENCE;

public class SentenceParser extends TextPartParser {
    @Override
    public SmartText parseLine(String line) {
        Sentence sentence = new Sentence(SENTENCE);
        String[] words = line.split(" ");
        for (String word : words) {
            if (!word.isEmpty()) {
                sentence.add(nextParse(word));
            }
        }
        return sentence;
    }
}

package by.javatr.service.parser;

import by.javatr.entity.composite.SmartText;
import by.javatr.entity.Text;

import static by.javatr.entity.composite.SmartText.TextTypeFunction.TEXT;

public class TextParser extends TextPartParser {
    @Override
    public SmartText parseLine(String line) {
        Text text = new Text(TEXT);
        String tab = "\\s{4}";
        for (String paragraph : line.split(tab)) {
            if (!paragraph.isEmpty()) {
                text.add(nextParse(paragraph));
            }
        }
        return text;
    }
}

package by.javatr.service;

import by.javatr.entity.Paragraphe;
import by.javatr.entity.Text;
import by.javatr.entity.composite.LeafText;
import by.javatr.entity.composite.SmartText;
import by.javatr.service.parser.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static by.javatr.entity.composite.SmartText.TextTypeFunction.PARAGRAPHE;
import static by.javatr.entity.composite.SmartText.TextTypeFunction.TEXT;

public class TextService {
    private static final Logger log = LogManager.getLogger(TextService.class);

    private SmartText smartText = null;

    public TextService() {
    }

    public String readFromFile(String path) throws TextServiceException {
        String text;
        Path resourcePath;

        try {
            resourcePath = Paths.get(TextService.class.getResource("/" + path).toURI());
        } catch (URISyntaxException e) {
            log.error(e.getMessage());
            throw new TextServiceException(e);
        }

        ParserChain<SmartText> chainParser = new WordParser().
                linkWith(new SentenceParser()).
                linkWith(new ParagrapheParser()).
                linkWith(new TextParser()).
                linkWith(new InvalidLinePartParser());

        try {
            smartText = new TextFileDataLoader(resourcePath).loadText(chainParser);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new TextServiceException(e);
        }

        text = smartText.getLeaf();

        return text;
    }

    public String sort() {
        smartText.sortBy();
        String text = "";
        /*
        List<Paragraphe> paragraph = smartText.getLeaf();
        paragraph = bubbleSort(paragraph);

        for (LeafText leafText : paragraph) {
            text += leafText.getLeaf();
        }
*/
        return text;
    }

    private Paragraphe[] bubbleSort(Paragraphe[] p) {
        Paragraphe[] buffer = p;
        int length = p.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (buffer[j].getSize() > buffer[j + 1].getSize()) {
                    swap(buffer, j, j + 1);
                }
            }
        }
        return buffer;
    }

    private void swap(Paragraphe[] p, int j, int i) {
        Paragraphe[] buffer = p;
        buffer[j] = p[i];
        buffer[i] = p[j];
        p = buffer;
    }

}

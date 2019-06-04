package by.javatr.service;

import by.javatr.entity.composite.SmartText;
import by.javatr.service.parser.ParserChain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TextFileDataLoader {
    private final Path filePath;

    public TextFileDataLoader(Path filePath) {
        this.filePath = filePath;
    }

    public SmartText loadText(ParserChain<SmartText> parserChain) throws IOException {

        List<String> allLines = Files.readAllLines(filePath);
        String line = "";

        for (String allLine : allLines) {
            line += allLine + "\n";
        }

        SmartText root = parserChain.parseLine(line);

        return root != null ? root.findRoot() : null;
    }
}

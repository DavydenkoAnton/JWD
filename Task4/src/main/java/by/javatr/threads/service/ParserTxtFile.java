package by.javatr.threads.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ParserTxtFile {

    private static final Logger LOGGER = LogManager.getLogger(ParserTxtFile.class.getName());

    public List<String> parse(String fileName) throws ParserTxtFileException {
        List<String> matrixLines = new ArrayList<>();
        Path filePath = Paths.get("./src/main/resources/" + fileName);
        Charset charset = Charset.forName("UTF-8");
        try {
            List<String> lines = Files.readAllLines(filePath, charset);
            matrixLines.addAll(lines);
        } catch (IOException e) {
            LOGGER.error(e);
            throw new ParserTxtFileException(e);
        }
        return matrixLines;
    }
}

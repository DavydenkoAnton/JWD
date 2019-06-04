package by.javatr.service.parser;

import by.javatr.entity.composite.SmartText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InvalidLinePartParser extends TextPartParser {
    private final static Logger log = LogManager.getLogger(InvalidLinePartParser.class);
    @Override
    public SmartText parseLine(String line)  {
        log.error("invalid line: " +line);
        return nextParse(line);
    }
}

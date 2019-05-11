package by.javatr.transport.parser.impl;

import by.javatr.transport.exception.ParseException;
import by.javatr.transport.logger.LoggerClass;
import org.junit.Assert;
import org.junit.Test;
import sun.security.pkcs.ParsingException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TrainPassengerParserTxtImplTest {

    @Test
    public void parse() {
        List<String> lines = new ArrayList<>();
        TrainPassengerParserTxtImpl trainPassengerParserTxt = new TrainPassengerParserTxtImpl();
        int expectedSize = 3;
        try {
            Assert.assertEquals(expectedSize, trainPassengerParserTxt.parse().size());
        } catch (ParseException e) {
            LoggerClass.logger.error("Cannot read file" + e);
        }
    }
}

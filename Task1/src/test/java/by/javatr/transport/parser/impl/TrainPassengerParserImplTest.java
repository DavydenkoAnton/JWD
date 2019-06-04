package by.javatr.transport.parser.impl;

import by.javatr.transport.entity.TrainPassenger;
import by.javatr.transport.exception.DaoException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrainPassengerParserImplTest {

    TrainPassenger trainPassenger;
    TrainPassengerParserImpl trainPassengerParser;
    String request;

    @Before
    public void intialise() {
        trainPassenger = new TrainPassenger();
        trainPassengerParser = new TrainPassengerParserImpl();
        request = "d802c9cf-eeab-4d0f-a393-fdb88eedd6b6 dima 16 vova 12 ";
    }

    @Test
    public void parse()  {
        String expected = "dima";
        List<String> result = trainPassengerParser.parse("d802c9cf-eeab-4d0f-a393-fdb88eedd6b6 dima 16 vova 12 ");
        String name = result.get(1);
        Assert.assertEquals(expected, name);
    }
}

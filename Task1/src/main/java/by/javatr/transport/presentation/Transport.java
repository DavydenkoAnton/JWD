package by.javatr.transport.presentation;

import by.javatr.transport.controller.Controller;
import by.javatr.transport.exception.TrainPassengerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Transport {
    public static final Logger log = LogManager.getLogger();
    public static void main(String[] args) {

        Controller controller = new Controller();
String t=null;
        try {
            controller.executeTask(t);
        } catch (TrainPassengerException e) {
            e.printStackTrace();
        }

    }
}

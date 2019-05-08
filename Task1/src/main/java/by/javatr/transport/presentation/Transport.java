package by.javatr.transport.presentation;

import by.javatr.transport.controller.Controller;
import by.javatr.transport.exception.DaoException;
import by.javatr.transport.exception.TrainPassengerException;

import java.io.IOException;

public class Transport {
    public static void main(String[] args) {
int x=0;
        Controller controller=new Controller();

        try {
            controller.executeTask("add_train_passenger 1234566");
        } catch ( IOException | TrainPassengerException e) {
            e.printStackTrace();
        }
        System.out.println("maven is started");

    }
}

package by.javatr.transport.presentation;

import by.javatr.transport.controller.Controller;

public class Transport {
    public static void main(String[] args) {

        Controller controller=new Controller();
        controller.executeTask("add_train_passenger 1");


    }
}

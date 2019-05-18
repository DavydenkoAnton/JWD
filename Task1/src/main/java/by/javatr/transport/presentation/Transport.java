package by.javatr.transport.presentation;

import by.javatr.transport.controller.Controller;
import by.javatr.transport.exception.TrainPassengerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Transport {
    public static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {

        Controller controller = new Controller();
        String response = "null";
        try {
            response = controller.executeTask("get_all_trains_passenger ");
        } catch (TrainPassengerException e) {
            log.error(e.getMessage());
        }
        System.out.println(response);

        try {
            response=controller.executeTask("add_train_passenger dima 16 vova 12");
        } catch (TrainPassengerException e) {
            log.error(e.getMessage());
        }

        try {
            response = controller.executeTask("get_all_trains_passenger ");
        } catch (TrainPassengerException e) {
            log.error(e.getMessage());
        }
        System.out.println(response);

        try {
            response=controller.executeTask("SORT_TRAINPASSENGER_ID ");
        } catch (TrainPassengerException e) {
            log.error(e.getMessage());
        }
        System.out.println(response);
    }
}

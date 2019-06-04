package by.javatr;

import by.javatr.service.TextService;
import by.javatr.service.TextServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Controller {

    private static final Logger log = LogManager.getLogger(Controller.class);

    TextService textService = new TextService();

    public Controller() {
    }

    public String getTextFromFile(String path){

        String text="";
        try {
            text=textService.readFromFile(path);
        } catch (TextServiceException e) {
            log.error(e);
        }
        return text;
    }

    public String sort(){
        textService.sort();
        return "";
    }
}

package by.davydenko.greenhouse.presentation;

import by.davydenko.greenhouse.controller.Controller;

class Runner {
    public static void main(String[] args) {

        Controller controller = new Controller();
        String response;

        response = controller.executeTask("flower_parse_dom ");
        System.out.println(response);

        response = controller.executeTask("flower_parse_sax ");
        System.out.println(response);

        response = controller.executeTask("flower_parse_stax ");
        System.out.println(response);

        response = controller.executeTask("wrong request");
        System.out.println(response);
    }
}

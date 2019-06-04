package by.javatr.presentation;

import by.javatr.Controller;

public class Runner {
    public static void main(String[] args) {
        Controller textController = new Controller();
        System.out.println(textController.getTextFromFile("text.txt"));
        textController.sort();
    }
}

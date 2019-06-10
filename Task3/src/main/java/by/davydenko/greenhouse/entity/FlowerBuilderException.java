package by.davydenko.greenhouse.entity;

public class FlowerBuilderException extends Exception {

    public FlowerBuilderException() {
        super();
    }

    public FlowerBuilderException(String name, Throwable exception) {
        super(name, exception);
    }

    public FlowerBuilderException(String name) {
        super(name);
    }

    public FlowerBuilderException(Throwable exception) {
        super(exception);
    }
}

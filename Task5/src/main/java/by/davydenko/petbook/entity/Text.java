package by.davydenko.petbook.entity;

abstract public class Text extends Entity {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

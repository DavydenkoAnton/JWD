package by.davydenko.petbook.entity;

public enum ArticleType {
    DOG("dog"),
    CAT("cat"),
    BIRD("bird"),
    OTHERS("others");

    private final String name;

     ArticleType(String name) {
        this.name = name;
    }

    public static ArticleType getType(int id) {
        return ArticleType.values()[id];
    }

    public int getId() {
        return ordinal();
    }

    @Override
    public String toString() {
        return  name;
    }
}

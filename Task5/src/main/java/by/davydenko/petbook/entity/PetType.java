package by.davydenko.petbook.entity;

public enum PetType {
    DOG("dog"),
    CAT("cat"),
    BIRD("bird"),
    OTHERS("others");

    private final String name;

     PetType(String name) {
        this.name = name;
    }

    public static PetType getType(int id) {
        return PetType.values()[id];
    }

    public int getId() {
        return ordinal();
    }

    @Override
    public String toString() {
        return  name;
    }
}

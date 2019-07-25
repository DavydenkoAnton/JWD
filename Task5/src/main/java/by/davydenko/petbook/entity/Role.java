package by.davydenko.petbook.entity;

public enum Role {
    ADMIN("admin"),
    USER("user"),
    LIBRARIAN("библиотекарь");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getIdentity() {
        return ordinal();
    }

    public static Role getByIdentity(Integer identity) {
        return Role.values()[identity];
    }
}

package by.davydenko.petbook.entity;

public enum Role {
    GUEST("guest"),
    USER("user"),
    ADMIN("admin");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public static Role getRole(int id) {
        return Role.values()[id];
    }

    public int getId() {
        return ordinal();
    }

    @Override
    public String toString() {
        return name;
    }
}

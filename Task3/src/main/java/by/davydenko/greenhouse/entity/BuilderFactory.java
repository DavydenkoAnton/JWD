package by.davydenko.greenhouse.entity;

public final class BuilderFactory {
    private static BuilderFactory instance;
    private FlowerBuilder flowerBuilder;

    private BuilderFactory() {
    }

    public static BuilderFactory getInstance() {
        if (instance == null) {
            instance = new BuilderFactory();
        }
        return instance;
    }

    public FlowerBuilder getFlowerBuilder() {
        return flowerBuilder = new FlowerFlowerBuilderImpl();
    }
}

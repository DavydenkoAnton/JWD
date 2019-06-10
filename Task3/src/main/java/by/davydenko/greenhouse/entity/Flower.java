package by.davydenko.greenhouse.entity;

public class Flower {
    private String ID;
    private String name;
    private Soil soil;
    private Country originCountry;
    private Color leafColor;
    private Color stemColor;
    private int height;
    private int weight;
    private int temperature;
    private boolean photophilous;
    private int watering;
    private PlantMultiplaying multiplying;

    public Flower() {
        this.name = "Chamomile";
        this.soil = Soil.GROUND;
        this.originCountry = Country.BY;
        this.leafColor = Color.WHITE;
        this.stemColor = Color.RED;
        this.height = 1;
        this.weight = 1;
        this.temperature = 0;
        this.photophilous = true;
        this.watering = 100;
        this.multiplying = PlantMultiplaying.BY_SEED;
    }

    public String getID() {
        return ID;
    }

    public enum Country {
        BY, RU, PL, DE
    }

    public enum Color {
        GREEN, RED, BLUE, WHITE
    }

    public enum Soil {
        GROUND, SEND, WATER
    }

    public enum PlantMultiplaying {
        BY_LEAF, BY_STEM, BY_SEED
    }

    /*
     * Getters
     * */

    public String getSoil() {
        return soil.name();
    }

    public String getName() {
        return name;
    }

    public String getOriginCountry() {
        return originCountry.name();
    }

    public String getLeafColor() {
        return leafColor.name();
    }

    public String getStemColor() {
        return stemColor.name();
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getTemperature() {
        return temperature;
    }

    public boolean isPhotophilous() {
        return photophilous;
    }

    public int getWatering() {
        return watering;
    }

    public String getMultiplying() {
        return multiplying.name();
    }

    /*
     * Setters
     * */

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSoil(Soil soil) {
        this.soil = soil;
    }

    public void setOriginCountry(Country originCountry) {
        this.originCountry = originCountry;
    }

    public void setLeafColor(Color leafColor) {
        this.leafColor = leafColor;
    }

    public void setStemColor(Color stemColor) {
        this.stemColor = stemColor;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setPhotophilous(boolean photophilous) {
        this.photophilous = photophilous;
    }

    public void setWatering(int watering) {
        this.watering = watering;
    }

    public void setMultiplying(PlantMultiplaying multiplying) {
        this.multiplying = multiplying;
    }


    @Override
    public String toString() {
        String flower = "[Flower]:\n" +
                "id:              " + this.ID +
                "\nname:            " + this.name +
                "\nsoil:            " + this.soil +
                "\norigin country:  " + this.originCountry +
                "\nleafColor:       " + this.leafColor.name() +
                "\nstemColor:       " + this.stemColor.name() +
                "\ntemperature:     " + this.temperature +
                "\nphotophilous:    " + this.photophilous +
                "\nwatering:        " + this.watering +
                "\nmultiplying:     " + this.multiplying +
                "\n";

        return flower;
    }
}

package by.davydenko.greenhouse.entity;

import java.util.Optional;
import java.util.stream.Stream;

public class Flower {

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

    private Flower() {
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

    public String getSoil() {
        return soil.name();
    }

    public String getName() {
        return name;
    }

    public Country getOriginCountry() {
        return originCountry;
    }

    public Color getLeafColor() {
        return leafColor;
    }

    public Color getStemColor() {
        return stemColor;
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

    public PlantMultiplaying getMultiplying() {
        return multiplying;
    }

    /*
     * class Builder with setters for class Flower
     *
     * */

    public static class Builder {
        private Flower flower;

        public Builder() {
            flower = new Flower();
            flower.name="defalt";
            flower.soil=Soil.GROUND;
            flower.originCountry=Country.BY;
            flower.leafColor=Color.WHITE;
            flower.stemColor=Color.RED;
            flower.height=10;
            flower.weight=10;
            flower.temperature=0;
            flower.photophilous=true;
            flower.watering=100;
            flower.multiplying=PlantMultiplaying.BY_SEED;
        }

        public Builder setName(String name) {
            flower.name = name;
            return this;
        }

        public Builder setSoil(Soil soil) {
            flower.soil = soil;
            return this;
        }


        public Builder setOriginCountry(Country country) {
            flower.originCountry = country;
            return this;
        }

        public Builder setLeafColor(Color color) {
            flower.leafColor = color;
            return this;
        }

        public Builder setStemColor(Color color) {
            flower.stemColor = color;
            return this;
        }

        public Builder setHeight(int height) {
            flower.height = height;
            return this;
        }

        public Builder setWeight(int weight) {
            flower.weight = weight;
            return this;
        }

        public Builder setTemperature(int temperature) {
            flower.temperature = temperature;
            return this;
        }

        public Builder setPhotophilous(boolean photophilous) {
            flower.photophilous = photophilous;
            return this;
        }

        public Builder setWatering(int watering) {
            flower.watering = watering;
            return this;
        }

        public Builder setMultiplying(PlantMultiplaying type) {
            flower.multiplying = type;
            return this;
        }

        public Flower build() {
            return flower;
        }
    }
}

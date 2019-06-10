package by.davydenko.greenhouse.entity;

public class FlowerBuilderImpl implements FlowerBuilder {

    private Flower flower;

    public FlowerBuilderImpl() {
        flower = new Flower();
    }

    public void setID(String ID) {
        flower.setID(ID);
    }

    public void setName(String name) {
        flower.setName(name);
    }

    public void setSoil(String soil) {
        switch (soil.toLowerCase()) {
            case "ground":
                flower.setSoil(Flower.Soil.GROUND);
                break;
            case "water":
                flower.setSoil(Flower.Soil.WATER);
                break;
            case "send":
                flower.setSoil(Flower.Soil.SEND);
                break;
        }
    }

    public void setOriginCountry(String originCountry) {
        switch (originCountry.toLowerCase()) {
            case "by":
                flower.setOriginCountry(Flower.Country.BY);
                break;
            case "ru":
                flower.setOriginCountry(Flower.Country.RU);
                break;
            case "pl":
                flower.setOriginCountry(Flower.Country.PL);
                break;
            case "de":
                flower.setOriginCountry(Flower.Country.DE);
                break;
        }
    }

    public void setLeafColor(String leafColor) {
        switch (leafColor.toLowerCase()) {
            case "white":
                flower.setLeafColor(Flower.Color.WHITE);
                break;
            case "red":
                flower.setLeafColor(Flower.Color.RED);
                break;
            case "green":
                flower.setLeafColor(Flower.Color.GREEN);
                break;
            case "blue":
                flower.setLeafColor(Flower.Color.BLUE);
                break;
        }
    }

    public void setStemColor(String stemColor) {
        switch (stemColor.toLowerCase()) {
            case "red":
                flower.setStemColor(Flower.Color.RED);
                break;
            case "white":
                flower.setStemColor(Flower.Color.WHITE);
                break;
            case "green":
                flower.setStemColor(Flower.Color.GREEN);
                break;
            case "blue":
                flower.setStemColor(Flower.Color.BLUE);
                break;
        }
    }

    public void setHeight(String height) {
        flower.setHeight(Integer.valueOf(height));
    }

    public void setWeight(String weight) {
        flower.setWeight(Integer.valueOf(weight));
    }

    public void setTemperature(String temperature) {
        flower.setTemperature(Integer.valueOf(temperature));
    }

    public void setPhotophilous(String photophilous) {
        if (photophilous.equalsIgnoreCase("true")) {
            flower.setPhotophilous(true);
        } else {
            flower.setPhotophilous(false);
        }
    }

    public void setWatering(String watering) {
        flower.setWatering(Integer.valueOf(watering));
    }

    public void setMultiplying(String multiplying) {
        switch (multiplying.toLowerCase()) {
            case "byseed":
                flower.setMultiplying(Flower.PlantMultiplaying.BY_SEED);
                break;
            case "byleaf":
                flower.setMultiplying(Flower.PlantMultiplaying.BY_LEAF);
                break;
            case "bystem":
                flower.setMultiplying(Flower.PlantMultiplaying.BY_STEM);
                break;
        }

    }

    public Flower getFlower() {
        return flower;
    }
}

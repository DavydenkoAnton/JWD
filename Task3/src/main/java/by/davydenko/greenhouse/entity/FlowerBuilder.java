package by.davydenko.greenhouse.entity;

public interface FlowerBuilder {

    public void setID(String ID) ;

    public void setName(String name);

    public void setSoil(String soil);

    public void setOriginCountry(String originCountry);

    public void setLeafColor(String leafColor);

    public void setStemColor(String stemColor);

    public void setHeight(String height);

    public void setWeight(String weight);

    public void setTemperature(String temperature);

    public void setPhotophilous(String photophilous);

    public void setWatering(String watering);

    public void setMultiplying(String multiplying);

    public Flower getFlower();
}

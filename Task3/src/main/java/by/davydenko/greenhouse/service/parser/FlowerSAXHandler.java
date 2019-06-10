package by.davydenko.greenhouse.service.parser;

import by.davydenko.greenhouse.entity.BuilderFactory;
import by.davydenko.greenhouse.entity.Flower;
import by.davydenko.greenhouse.entity.FlowerBuilder;
import by.davydenko.greenhouse.entity.FlowerBuilderException;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class FlowerSAXHandler extends DefaultHandler {

    private List<Flower> flowers;
    private BuilderFactory builderFactory;
    private FlowerBuilder flowerBuilder;
    private StringBuilder data;

    private boolean foundID = false;
    private boolean foundName = false;
    private boolean foundSoil = false;
    private boolean foundOriginCountry = false;
    private boolean foundLeafColor = false;
    private boolean foundStemColor = false;
    private boolean foundHeight = false;
    private boolean foundWeight = false;
    private boolean foundTemperature = false;
    private boolean foundPhotophilous = false;
    private boolean foundWatering = false;
    private boolean foundMultiplying = false;

    public FlowerSAXHandler() {
        flowers = new ArrayList<>();
        builderFactory = BuilderFactory.getInstance();
        data = null;
    }

    public List<Flower> getFlowerList(){
        return flowers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("Flower")) {
            flowerBuilder = builderFactory.getFlowerBuilder();
        }

        if (qName.equalsIgnoreCase("ID")) {
            foundID = true;
        }

        if (qName.equalsIgnoreCase("Name")) {
            foundName = true;
        }

        if (qName.equalsIgnoreCase("Soil")) {
            foundSoil = true;
        }

        if (qName.equalsIgnoreCase("Origin")) {
            foundOriginCountry = true;
        }

        if (qName.equalsIgnoreCase("LeafColor")) {
            foundLeafColor = true;
        }

        if (qName.equalsIgnoreCase("StemColor")) {
            foundStemColor = true;
        }

        if (qName.equalsIgnoreCase("Height")) {
            foundHeight = true;
        }

        if (qName.equalsIgnoreCase("Weight")) {
            foundWeight = true;
        }

        if (qName.equalsIgnoreCase("Temperature")) {
            foundTemperature = true;
        }

        if (qName.equalsIgnoreCase("Photophilous")) {
            foundPhotophilous = true;
        }

        if (qName.equalsIgnoreCase("Watering")) {
            foundWatering = true;
        }

        if (qName.equalsIgnoreCase("Multiplying")) {
            foundMultiplying = true;
        }

        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (foundID) {
            flowerBuilder.setID(data.toString());
            foundID = false;
        }

        if (foundName) {
            flowerBuilder.setName(data.toString());
            foundName = false;
        }

        if (foundSoil) {
            flowerBuilder.setSoil(data.toString());
            foundSoil = false;
        }

        if (foundOriginCountry) {
            flowerBuilder.setOriginCountry(data.toString());
            foundOriginCountry = false;
        }

        if (foundLeafColor) {
            flowerBuilder.setLeafColor(data.toString());
            foundLeafColor = false;
        }

        if (foundStemColor) {
            flowerBuilder.setStemColor(data.toString());
            foundStemColor = false;
        }

        if (foundHeight) {
            flowerBuilder.setHeight(data.toString());
            foundHeight = false;
        }

        if (foundWeight) {
            flowerBuilder.setWeight(data.toString());
            foundWeight = false;
        }

        if (foundTemperature) {
            flowerBuilder.setTemperature(data.toString());
            foundTemperature = false;
        }

        if (foundPhotophilous) {
            flowerBuilder.setPhotophilous(data.toString());
            foundPhotophilous = false;
        }

        if (foundWatering) {
            flowerBuilder.setWatering(data.toString());
            foundWatering = false;
        }

        if (foundMultiplying) {
            flowerBuilder.setMultiplying(data.toString());
            foundMultiplying = false;
        }

        if (qName.equalsIgnoreCase("Flower")) {
            flowers.add(flowerBuilder.getFlower());
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) {
        data.append(new String(ch, start, length));
    }
}

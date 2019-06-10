package by.davydenko.greenhouse.service.parser;

import by.davydenko.greenhouse.entity.BuilderFactory;
import by.davydenko.greenhouse.entity.Flower;
import by.davydenko.greenhouse.entity.FlowerBuilder;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public final class FlowerXMLParserSTAXImpl implements FlowerXMLParser {
    private List<Flower> flowers;
    private BuilderFactory builderFactory;
    private FlowerBuilder flowerBuilder;

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

    @Override
    public List<Flower> parse(String pathFile) throws FlowerXMLParserSTAXException {

        builderFactory = BuilderFactory.getInstance();
        flowers = new ArrayList<>();
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(pathFile));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {

                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();

                        if (qName.equalsIgnoreCase("Flower")) {
                            flowerBuilder = builderFactory.getFlowerBuilder();
                        } else if (qName.equalsIgnoreCase("ID")) {
                            foundID = true;
                        } else if (qName.equalsIgnoreCase("Name")) {
                            foundName = true;
                        } else if (qName.equalsIgnoreCase("Soil")) {
                            foundSoil = true;
                        } else if (qName.equalsIgnoreCase("Origin")) {
                            foundOriginCountry = true;
                        } else if (qName.equalsIgnoreCase("LeafColor")) {
                            foundLeafColor = true;
                        } else if (qName.equalsIgnoreCase("StemColor")) {
                            foundStemColor = true;
                        } else if (qName.equalsIgnoreCase("Height")) {
                            foundHeight = true;
                        } else if (qName.equalsIgnoreCase("Weight")) {
                            foundWeight = true;
                        } else if (qName.equalsIgnoreCase("Temperature")) {
                            foundTemperature = true;
                        } else if (qName.equalsIgnoreCase("Photophilous")) {
                            foundPhotophilous = true;
                        } else if (qName.equalsIgnoreCase("Watering")) {
                            foundWatering = true;
                        } else if (qName.equalsIgnoreCase("Multiplaying")) {
                            foundMultiplying = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if (foundID) {
                            flowerBuilder.setID(characters.getData());
                            foundID = false;
                        } else if (foundName) {
                            flowerBuilder.setName(characters.getData());
                            foundName = false;
                        } else if (foundSoil) {
                            flowerBuilder.setSoil(characters.getData());
                            foundSoil = false;
                        } else if (foundOriginCountry) {
                            flowerBuilder.setOriginCountry(characters.getData());
                            foundOriginCountry = false;
                        } else if (foundLeafColor) {
                            flowerBuilder.setLeafColor(characters.getData());
                            foundLeafColor = false;
                        } else if (foundStemColor) {
                            flowerBuilder.setStemColor(characters.getData());
                            foundStemColor = false;
                        } else if (foundHeight) {
                            flowerBuilder.setHeight(characters.getData());
                            foundHeight = false;
                        } else if (foundWeight) {
                            flowerBuilder.setWeight(characters.getData());
                            foundWeight = false;
                        } else if (foundTemperature) {
                            flowerBuilder.setTemperature(characters.getData());
                            foundTemperature = false;
                        } else if (foundPhotophilous) {
                            flowerBuilder.setPhotophilous(characters.getData());
                            foundPhotophilous = false;
                        } else if (foundWatering) {
                            flowerBuilder.setWatering(characters.getData());
                            foundWatering = false;
                        } else if (foundMultiplying) {
                            flowerBuilder.setMultiplying(characters.getData());
                            foundMultiplying = false;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();

                        if (endElement.getName().getLocalPart().equalsIgnoreCase("Flower")) {
                            flowers.add(flowerBuilder.getFlower());
                        }
                        break;
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new FlowerXMLParserSTAXException(e);
        }

        return flowers;
    }
}

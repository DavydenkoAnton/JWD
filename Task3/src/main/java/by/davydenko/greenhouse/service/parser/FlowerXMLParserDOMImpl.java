package by.davydenko.greenhouse.service.parser;

import by.davydenko.greenhouse.entity.BuilderFactory;
import by.davydenko.greenhouse.entity.Flower;
import by.davydenko.greenhouse.entity.FlowerBuilder;
import by.davydenko.greenhouse.entity.FlowerBuilderException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public final class FlowerXMLParserDOMImpl implements FlowerXMLParser {

    private final static Logger logger = Logger.getLogger(FlowerXMLParserDOMImpl.class);

    @Override
    public List<Flower> parse(String path) throws FlowerXMLParserDOMException {
        List<Flower> flowers = new ArrayList<>();
        BuilderFactory builderFactory = BuilderFactory.getInstance();
        FlowerBuilder flowerBuilder = builderFactory.getFlowerBuilder();

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(path);

            NodeList flowersNodeList = doc.getElementsByTagName("Flower");

            for (int i = 0; i < flowersNodeList.getLength(); i++) {
                if (flowersNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element flowerElement = (Element) flowersNodeList.item(i);
                    //here we can take attributes
                    // some code for get attributes

                    NodeList childNodeList = flowerElement.getChildNodes();
                    for (int j = 0; j < childNodeList.getLength(); j++) {
                        if (childNodeList.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            Element childElement = (Element) childNodeList.item(j);

                            switch (childElement.getNodeName()) {
                                case "ID":
                                    flowerBuilder.setID(childElement.getTextContent());
                                    break;
                                case "Name":
                                    flowerBuilder.setName(childElement.getTextContent());
                                    break;
                                case "Soil":
                                    flowerBuilder.setSoil(childElement.getTextContent());
                                    break;
                                case "Origin":
                                    flowerBuilder.setOriginCountry(childElement.getTextContent());
                                    break;
                                case "Parametres":
                                    NodeList childParams = childElement.getChildNodes();
                                    for (int k = 0; k < childParams.getLength(); k++) {
                                        if (childParams.item(k).getNodeType() == Node.ELEMENT_NODE) {
                                            switch (childParams.item(k).getNodeName()) {
                                                case "VisualParametres":
                                                    NodeList childVisualParams = childParams.item(k).getChildNodes();
                                                    for (int l = 0; l < childVisualParams.getLength(); l++) {
                                                        if (childVisualParams.item(l).getNodeType() == Node.ELEMENT_NODE) {
                                                            Element childVisualParamsElement = (Element) childVisualParams.item(l);
                                                            flowerBuilder.setLeafColor(childVisualParamsElement.getTextContent());
                                                        }
                                                    }
                                                    break;
                                                case "InsideParametres":
                                                    NodeList childInsideParams = childParams.item(k).getChildNodes();
                                                    for (int l = 0; l < childInsideParams.getLength(); l++) {
                                                        if (childInsideParams.item(l).getNodeType() == Node.ELEMENT_NODE) {
                                                            Element childInsideParamsElement = (Element) childInsideParams.item(l);
                                                            flowerBuilder.setWeight(childInsideParamsElement.getTextContent());
                                                        }
                                                    }
                                                    break;
                                            }
                                        }
                                    }
                                    break;
                                case "Temperature":
                                    flowerBuilder.setTemperature(childElement.getTextContent());
                                    break;
                                case "Photophilous":
                                    flowerBuilder.setPhotophilous(childElement.getTextContent());
                                    break;
                                case "Watering":
                                    flowerBuilder.setWatering(childElement.getTextContent());
                                    break;
                                case "Multiplying":
                                    flowerBuilder.setMultiplying(childElement.getTextContent());
                                    break;
                            }
                        }
                    }
                }
                flowers.add(flowerBuilder.getFlower());
                flowerBuilder = builderFactory.getFlowerBuilder();
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            logger.error(ex);
            throw new FlowerXMLParserDOMException(ex.getMessage());
        }
        return flowers;
    }


}

package by.davydenko.greenhouse.service.parser;

import by.davydenko.greenhouse.entity.Flower;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class XMLParserDOMImpl implements XMLParser {

    @Override
    public List<Flower> parse(String path) {

        List<Flower> flowers = new ArrayList<>();
        Flower flower;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(path);

            Element flowersElement = (Element) doc.getElementsByTagName("Flowers").item(0);

            NodeList flowersNodeList = doc.getElementsByTagName("Flower");

            for (int i = 0; i < flowersNodeList.getLength(); i++) {
                if (flowersNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element flowerElement = (Element) flowersNodeList.item(i);
                    //here we can take attributes
                    // TODO for get attributes

                    NodeList childNodeList = flowerElement.getChildNodes();
                    for (int j = 0; j < childNodeList.getLength(); j++) {
                        if (childNodeList.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            Element childElement = (Element) childNodeList.item(j);

                            switch (childElement.getNodeName()) {
                                case "ID":
                                    System.out.println(childElement.getTextContent());
                                    break;
                                case "Name":
                                    System.out.println(childElement.getTextContent());
                                    break;
                                case "Soil":
                                    System.out.println(childElement.getTextContent());
                                    break;
                                case "Origin":
                                    System.out.println(childElement.getTextContent());
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
                                                            switch (childVisualParamsElement.getNodeName()) {
                                                                case "LeafColor":
                                                                    System.out.println(childVisualParamsElement.getTextContent());
                                                                    break;
                                                                case "Stem":
                                                                    System.out.println(childVisualParamsElement.getTextContent());
                                                                    break;
                                                                case "Height":
                                                                    System.out.println(childVisualParamsElement.getTextContent());
                                                                    break;
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case "InsideParametres":
                                                    NodeList childInsideParams = childParams.item(k).getChildNodes();
                                                    for (int l = 0; l < childInsideParams.getLength(); l++) {
                                                        if (childInsideParams.item(l).getNodeType() == Node.ELEMENT_NODE) {
                                                            Element childInsideParamsElement = (Element) childInsideParams.item(l);
                                                            switch (childInsideParamsElement.getNodeName()) {
                                                                case "Weight":
                                                                    System.out.println(childInsideParamsElement.getTextContent());
                                                                    break;
                                                            }
                                                        }
                                                    }
                                                    break;
                                            }
                                        }
                                    }
                                    break;
                                case "Temperature":
                                    System.out.println(childElement.getTextContent());
                                    break;
                                case "Photophilous":
                                    System.out.println(childElement.getTextContent());
                                    break;
                                case "Watering":
                                    System.out.println(childElement.getTextContent());
                                    break;
                                case "Multiplying":
                                    System.out.println(childElement.getTextContent());
                                    break;
                            }
                        }
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex.getMessage());
        }
        return flowers;
    }


    private static void printNodes(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                // Пeчaтaem иmя нoды и знaчeниe          
                System.out.println();
                System.out.println("Иmя нoды: " + node.getNodeName());
                System.out.println("Знaчeниe нoды: " + node.getTextContent());
                if (node.hasAttributes()) {
                    // Ecть aтpибyты: пeчaтaem и их                    

                    NamedNodeMap attributes = node.getAttributes();
                    for (int j = 0; j < attributes.getLength(); j++) {
                        Node attribute = attributes.item(j);
                        System.out.println("Иmя aтpибyтa: " + attribute.getNodeName());
                        System.out.println("Знaчeниe aтpибyтa: " + attribute.getNodeValue());
                    }
                }
                if (node.hasChildNodes()) {
                    // Ecть дoчepниe нoды: пeчaтaem их
                    printNodes(node.getChildNodes());
                }
            }
        }
    }


}

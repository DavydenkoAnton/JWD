package by.davydenko.greenhouse.service.parser;

import by.davydenko.greenhouse.entity.Flower;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

public final class XMLParserDOMImpl implements XMLParser {


    @Override
    public List<Flower> parse(String path) {

        List<String> nodes = new ArrayList<>();
        List<Flower> flowerList = new ArrayList<>();

        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(path);
            document.getDocumentElement().normalize();

            System.out.println("Root: " + document.getDocumentElement().getNodeName());
            NodeList nodeList = document.getElementsByTagName(document.getDocumentElement().getChildNodes().item(1).getNodeName());
            System.out.println("--------------------");
            for (int tmp = 0; tmp < nodeList.getLength(); tmp++) {
                Node node = nodeList.item(tmp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    nodes.add(element.getElementsByTagName("Name").item(0).getChildNodes().item(0).getNodeValue());
                    nodes.add(element.getElementsByTagName("Soil").item(0).getChildNodes().item(0).getNodeValue());
                
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return flowerList;
    }
}
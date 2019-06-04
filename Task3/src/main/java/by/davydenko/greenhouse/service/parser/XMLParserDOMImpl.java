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

        List<String> content = new ArrayList<>();
        List<Flower> flowerList = new ArrayList<>();

        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(path);
            document.getDocumentElement().normalize();

            NodeList flowerNodeList = document.getElementsByTagName(document.getDocumentElement().getChildNodes().item(1).getNodeName());

            for (int iter = 0; iter < flowerNodeList.getLength(); iter++) {
                Node flowerNode = flowerNodeList.item(iter);
                if (flowerNode.getNodeType() == Node.ELEMENT_NODE) {
                    String buff = flowerNode.getTextContent();
                    if (buff.length() != 0) {
                        content.add(buff);
                    }
                }
            }
            Flower flower = new Flower.Builder().setName(content.get(0)).build();
            flowerList.add(flower);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return flowerList;
    }
}
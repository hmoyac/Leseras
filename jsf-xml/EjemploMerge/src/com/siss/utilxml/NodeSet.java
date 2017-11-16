package com.siss.utilxml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author econtreras
 */
public class NodeSet {

    public static org.w3c.dom.NodeList getNS1() throws ParserConfigurationException, SAXException, IOException, TransformerException {
        org.w3c.dom.NodeList nodeList;
        File file = new File("file1.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();
        nodeList = doc.getChildNodes();
        return nodeList;
    }

    public static org.w3c.dom.NodeList getNS2() throws ParserConfigurationException, SAXException, IOException, TransformerException {
        org.w3c.dom.NodeList nodeList;
        File file = new File("file2.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();
        nodeList = doc.getChildNodes();
        return nodeList;
    }

}

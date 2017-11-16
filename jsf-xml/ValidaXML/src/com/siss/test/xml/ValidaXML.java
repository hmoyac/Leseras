package com.siss.test.xml;

import com.siss.utilxml.ValidateXml;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author econtreras
 */
public class ValidaXML {

    static String xml = "Archivos" + File.separator + "cr_69070900_201003.xml";
    static String xsd = "Archivos" + File.separator + "ClientesRegulados-v1-0.xsd";
    static String schematron = "Archivos" + File.separator + "ClientesRegulados-v1-0.sch";

    public static void main(String[] args) {
/*
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(xml);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValidaXML.class.getName()).log(Level.SEVERE, null, ex);
        }

        NamespaceContext nsContext = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader evtReader = null;
        try {
            evtReader = factory.createXMLEventReader(fis);
        } catch (XMLStreamException ex) {
            Logger.getLogger(ValidaXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (evtReader.hasNext()) {
            XMLEvent event = null;
            try {
                event = evtReader.nextEvent();
            } catch (XMLStreamException ex) {
                Logger.getLogger(ValidaXML.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (event.isStartElement()) {
                nsContext = ((StartElement) event).getNamespaceContext();
                if (nsContext.getNamespaceURI("")!=null) {
                    System.out.println("Debe remover espacio de nombre xmlns=\"" +
                                        nsContext.getNamespaceURI("")+"\" en elemento " + ((StartElement) event).getName().getLocalPart());
                    return;
                }
            }
            if (event.isEndElement()) {
                break;
            }
        }
        
        ValidateXml validateXml = new ValidateXml(xml);

        validateXml.validacionXsd(xsd, "http://extranet.siss.cl/sinar/sifac/");
        System.out.println(validateXml.getResultXsd());

        //validateXml.validacionSch(schematron, "http://desaex.siss.cl/sinar/scripts/schematron-diagnose.xsl");
        //System.out.println(validateXml.getResultSch());
 */

         System.out.println(padRight("Howto", 20) + "*");

    }
    
    public static String padRight(String s, int n) {
         return String.format("%1$-" + n + "s", s);
    }

    public static String padLeft(String s, int n) {
        return String.format("%1$#" + n + "s", s);
    }

}

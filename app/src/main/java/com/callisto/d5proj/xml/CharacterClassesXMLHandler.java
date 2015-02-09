package com.callisto.d5proj.xml;

import com.callisto.d5proj.xml.pojos.CharacterClass;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by Crux on 08/02/2015.
 */
public class CharacterClassesXMLHandler extends DefaultHandler {
    boolean currentElement = false;
    String currentValue = "";
    private ArrayList<CharacterClass> pcClasses;
    private CharacterClass pcClass;

    public ArrayList<CharacterClass> getPcClasses() { return pcClasses; }

    public void startElement(String uri, String localName, String qName, Attributes attributes)
        throws SAXException {

        currentElement = true;

        if (qName.equals("CharacterClasses")) {
            pcClasses = new ArrayList<>();
        } else if (qName.equals("CharacterClass")) {
            pcClass = new CharacterClass();
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        currentElement = false;

        // TODO Complete XML PARSER!
//        if (qName.equalsIgnoreCase("Name")) {
//            pcClass.setName(currentValue.trim());
//        } else if (qName.equalsIgnoreCase("DieSize")) {
//            pcClass.setDieSize(Integer.parseInt(currentValue.trim()));
//        } else if (qName.equalsIgnoreCase("KeyStat")) {
//            pcClass.getKeyStats().add();
//        } else if (qName.equalsIgnoreCase("Row")) {
//            levels.add(level);
//        }

        currentValue = "";
    }

    public void characters(char[] ch, int start, int length)
        throws SAXException {

        if (currentElement) {
            currentValue = currentValue + new String(ch, start, length);
        }
    }
}

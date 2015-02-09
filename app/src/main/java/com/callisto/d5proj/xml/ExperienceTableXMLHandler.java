package com.callisto.d5proj.xml;

import com.callisto.d5proj.pojos.Level;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by Crux on 07/02/2015.
 * Source: http://www.mysamplecode.com/2012/10/android-parse-xml-file-from-assets-or.html
 */
public class ExperienceTableXMLHandler extends DefaultHandler {
    boolean currentElement = false;
    String currentValue = "";
    private ArrayList<Level> levels;
    private Level level;

    public ArrayList<Level> getLevels() {
        return levels;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes)
        throws SAXException {

        currentElement = true;

        if (qName.equals("ExperienceTable")) {
            levels = new ArrayList<>();
        } else if (qName.equals("Row")) {
            level = new Level();
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        currentElement = false;

        int trimmedValue = 0;

        if (currentValue != "") {
            trimmedValue = Integer.parseInt(currentValue.trim());
        }
        if (qName.equalsIgnoreCase("Level")) {
            level.setNumber(trimmedValue);
        } else if (qName.equalsIgnoreCase("Experience")) {
            level.setExperience(trimmedValue);
        } else if (qName.equalsIgnoreCase("ProficiencyBonus")) {
            level.setProficiencyBonus(trimmedValue);
        } else if (qName.equalsIgnoreCase("Row")) {
            levels.add(level);
        }

        currentValue = "";
    }

    public void characters(char[] ch, int start, int length)
        throws SAXException {

        if (currentElement) {
            currentValue = currentValue + new String(ch, start, length);
        }
    }
}

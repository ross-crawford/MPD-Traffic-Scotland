package com.rosscrawford.mpdtrafficscotland;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * @author : Ross Crawford
 * @matriculation no. : S1821950
 * @university : Glasgow Caledonian University
 * @module : Mobile Platform Development
 * @created : 21/03/2020
 **/
public class XmlParser {

    private ArrayList<Item> items;
    private Item item;
    private String text;

    public XmlParser()
    {
        items = new ArrayList<>();
    }

    public void parseData(String data) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(data));
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                String tag = parser.getName();

            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}

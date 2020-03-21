package com.rosscrawford.mpdtrafficscotland;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * @author : Ross Crawford
 * @matriculation no. : S1821950
 * @university : Glasgow Caledonian University
 * @module : Mobile Platform Development
 * @created : 21/03/2020
 **/
public class XmlParser
{

    private ArrayList<Item> items;
    private Item item;
    private String text;

    public XmlParser()
    {
        items = new ArrayList<>();
    }


    public void parseData(String data)
    {
        try
        {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(data));
            int eventType = parser.getEventType();
            item = new Item();

            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                String tag = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tag.equalsIgnoreCase("item"))
                        {
                            item = new Item();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        //Log.d("Test", text);
                        break;

                    case XmlPullParser.END_TAG:
                        if (tag.equalsIgnoreCase("item"))
                        {
                            items.add(item);
                        }
                        else if (tag.equalsIgnoreCase("title"))
                        {
                            item.setTitle(text);
                        }
                        else if (tag.equalsIgnoreCase("description"))
                        {
                            item.setDescription(text);
                        }
                        else if (tag.equalsIgnoreCase("pubDate"))
                        {
                            item.setPublished(text);
                        }
                        else if (tag.equalsIgnoreCase("point"))
                        {
                            item.setGeo(text);
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }
            TrafficApplication.items = items;
        }
        catch (XmlPullParserException | IOException e)
        {
            e.printStackTrace();
        }
    }
}

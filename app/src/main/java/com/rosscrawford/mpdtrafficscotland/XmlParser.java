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
    private Formatter formatter;

    public XmlParser()
    {
        items = new ArrayList<>();
        formatter = new Formatter();
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
                        break;

                    case XmlPullParser.END_TAG:
                        if (tag.equalsIgnoreCase("item"))
                        {
                            // negating items which don't have a title in the RSS feed - breaking the filter
                            if (!(item.getTitle() == null))
                            {
                                items.add(item);
                            }
                        }

                        else if (tag.equalsIgnoreCase("title"))
                        {
                            item.setTitle(text);

                        }
                        else if (tag.equalsIgnoreCase("description"))
                        {
                            String formatted = formatter.convertLineBreaks(text);
                            int startIndexFirst = formatted.indexOf("Start Date: ");
                            int startIndexLast = formatted.indexOf(" - 00:00");
                            //String startDate = formatted.substring(startIndexFirst, startIndexLast);
                            //String startDateIndex = formatted.substring(formatted.indexOf("Start Date: "), formatted.indexOf(':'));
                            //Log.d("Test", startIndexFirst + " " + startIndexLast);
                            item.setDescription(formatted);
                        }
                        else if (tag.equalsIgnoreCase("pubDate"))
                        {
                            item.setPublished(text);
                        }
                        else if (tag.equalsIgnoreCase("point"))
                        {
                            double[] latLng = formatter.getLatLng(text);
                            //Log.d("Test", "Latitude: " + latLng[0] + " Longitude: " + latLng[1]);
                            item.setGeo(text);
                            item.setLatLng(latLng);
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

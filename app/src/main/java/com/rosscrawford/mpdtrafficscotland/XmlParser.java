package com.rosscrawford.mpdtrafficscotland;

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
                switch (eventType)
                {
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
                            String[] dateStrings = formatter.getDateStrings(text);
                            String formatted = formatter.convertLineBreaks(text);
                            item.setDescription(formatted);
                            if (dateStrings != null)
                            {
                                item.setStart(formatter.getCalendarFromString(dateStrings[0]));
                                item.setEnd(formatter.getCalendarFromString(dateStrings[1]));
                                item.setRating(formatter.getDurationRating(item.getStart(), item.getEnd()));
                            }
                        }
                        else if (tag.equalsIgnoreCase("pubDate"))
                        {
                            item.setPublished(text);
                        }
                        else if (tag.equalsIgnoreCase("point"))
                        {
                            double[] latLng = formatter.getLatLng(text);
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

/*
    FORMATTING REQUIRED

    - Check if there are start/end dates (should only be on roadworks)
        - if dates exist, extract them in string format
        - pass the date strings to formatter to convert to calendar
        - perform rating check to determine low/med/high based on duration of works

    Current Incidents Format
    <item>
        <title>A87 Skye Bridge - High winds</title>
        <description>The A87 Skye Bridge is closed to High Sided Vehicles due to high winds affecting driving conditions.</description>
        <link>http://tscot.org/01c272751</link>
        <georss:point>57.2772769825118 -5.741514002315</georss:point>
        <author />
        <comments />
        <pubDate>Mon, 23 Mar 2020 02:22:50 GMT</pubDate>
    </item>

    Current Roadworks Format
    <item>
        <title>A90 NB Tipperty to Ellon Roundabout</title>
        <description>
        Start Date: Monday, 23 March 2020 - 00:00<br />End Date: Friday, 27 March 2020 - 00:00<br />Delay Information: No reported delay.
        </description>
        <link>http://tscot.org/03cAWP2020908</link>
        <georss:point>57.3267322745009 -2.04885786818408</georss:point>
        <author/>
        <comments/>
        <pubDate>Mon, 23 Mar 2020 00:00:00 GMT</pubDate>
    </item>

    Planned Roadworks Format
    <item>
        <title>M6</title>
        <description>
        Start Date: Tuesday, 24 March 2020 - 00:00<br />End Date: Wednesday, 01 April 2020 - 00:00<br />TYPE : AWP Location : The M6 northbound between junctions J41 and J42 Lane Closures : Lanes 1, 2 a
        </description>
        <link>http://tscot.org/04h1231200078</link>
        <georss:point>54.7047810186083 -2.79225736783276</georss:point>
        <author/>
        <comments/>
        <pubDate>Tue, 24 Mar 2020 00:00:00 GMT</pubDate>
    </item>
*/

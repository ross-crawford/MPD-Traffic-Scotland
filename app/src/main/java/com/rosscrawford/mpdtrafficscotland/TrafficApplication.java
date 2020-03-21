package com.rosscrawford.mpdtrafficscotland;

import android.app.Application;

import java.util.ArrayList;

/**
 * @author : Ross Crawford
 * @matriculation no. : S1821950
 * @university : Glasgow Caledonian University
 * @module : Mobile Platform Development
 * @created : 20/03/2020
 **/
public class TrafficApplication extends Application {

    public static final String currentIncidentsUrl = "https://trafficscotland.org/rss/feeds/currentincidents.aspx";
    public static final String currentRoadworksUrl = "https://trafficscotland.org/rss/feeds/roadworks.aspx";
    public static final String plannedRoadworksUrl = "https://trafficscotland.org/rss/feeds/plannedroadworks.aspx";

    public static ArrayList<Item> items;
    public static String itemsString;

}

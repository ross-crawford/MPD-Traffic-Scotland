package com.rosscrawford.mpdtrafficscotland;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static java.lang.Double.valueOf;

/**
 * @author : Ross Crawford
 * @matriculation no. : S1821950
 * @university : Glasgow Caledonian University
 * @module : Mobile Platform Development
 * @created : 20/03/2020
 **/

class Formatter
{

    // convert geo values for map
    double[] getLatLng(String geo)
    {
        String[] latLngString = geo.split(" ");
        return new double[]{valueOf(latLngString[0]), valueOf(latLngString[1])};
    }

    public String[] getDateStrings(String text)
    {
        if (!text.contains("Start Date: ") || !text.contains("End Date: "))
        {
            return null;
        }
        else
        {
            String startDateIndex = text.substring(text.indexOf("Start Date: "), text.indexOf(':'));
            String startDateString = text.substring(startDateIndex.length() + 2, text.indexOf(" - 00:00"));

            // remove start date to parse end date
            text = text.substring(text.indexOf('>') + 1);

            String endDateIndex = text.substring(text.indexOf("End Date: "), text.indexOf(':'));
            String endDateString = text.substring(endDateIndex.length() + 2, text.indexOf(" - 00:00"));

            String[] datesArray = new String[2];
            datesArray[0] = startDateString;
            datesArray[1] = endDateString;
            return datesArray;
        }
    }

    // extract Calendar object from date string in description
    public Calendar getCalendarFromString(String date)
    {
        Calendar calendar = Calendar.getInstance();
        TimeZone tz = TimeZone.getTimeZone("GMT");
        String pattern = "EEEE, dd MMMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
        try {
            calendar.setTime(simpleDateFormat.parse(date));
            calendar.setTimeZone(tz);
        } catch (ParseException e) {
            Log.d("Test", "Error converting date String to date Calendar");
        }
        return calendar;
    }

    // duration ratings: 1 - low, 2 - med, 3 - high
    public int getDurationRating(Calendar start, Calendar end)
    {
        long s = start.getTimeInMillis();
        long e = end.getTimeInMillis();
        long daysBetween = TimeUnit.MILLISECONDS.toDays(e - s);

        if (daysBetween <= 1)
        {
            return 1;
        }
        else if (daysBetween > 1 && daysBetween <= 7)
        {
            return 2;
        }
        else {
            return 3;
        }
    }

    // convert xml line breaks to \n and line break for roadworks information
    String convertLineBreaks(String text)
    {
        text = text.replaceAll("<br />", "\n");
        text = text.replace("Location : ", "\nLocation: ");
        text = text.replace("Traffic Management:", "\nTraffic Management: ");
        text = text.replace("Diversion Information:", "\nDiversion Information: ");
        text = text.replace("Lane Closures :", "\nLane Closures: ");
        text = text.replace("Reason :", "\nReason: ");
        return text;
    }

}

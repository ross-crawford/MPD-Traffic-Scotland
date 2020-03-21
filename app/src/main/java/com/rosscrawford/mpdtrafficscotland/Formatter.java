package com.rosscrawford.mpdtrafficscotland;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * @author : Ross Crawford
 * @matriculation no. : S1821950
 * @university : Glasgow Caledonian University
 * @module : Mobile Platform Development
 * @created : 20/03/2020
 **/
public class Formatter {

    // convert geo values for map
    public double[] getLatLng(String geo)
    {
        String[] latLngString = geo.split(" ");
        double[] latLng = {Double.valueOf(latLngString[0]), Double.valueOf(latLngString[1])};
        return latLng;
    }

    // extract Calendar object from date string in description
    public Calendar getCalendarFromString(String date)
    {
        Calendar calendar = Calendar.getInstance();
        String pattern = "EEEE, dd MMMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
        try {
            calendar.setTime(simpleDateFormat.parse(date));
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

    // convert xml line breaks to \n
    public String convertLineBreaks(String text)
    {
        String converted = text.replaceAll("<br />", "\n");
        return converted;
    }

}

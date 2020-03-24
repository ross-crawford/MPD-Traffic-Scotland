package com.rosscrawford.mpdtrafficscotland;

import org.junit.Test;

import java.util.Calendar;
import java.util.TimeZone;

import static org.junit.Assert.*;

/**
 * @author : Ross Crawford
 * @matriculation no. : S1821950
 * @university : Glasgow Caledonian University
 * @module : Mobile Platform Development
 * @created : 24/03/2020
 **/
public class FormatterTest
{
    Formatter formatter = new Formatter();

    // working
    @Test
    public void getLatLng()
    {
        String input = "57.3267322745009 -2.04885786818408";
        double[] expected = {57.3267322745009, -2.04885786818408};
        double[] output = formatter.getLatLng(input);
        assertEquals(expected[0], output[0], 0);
        assertEquals(expected[1], output[1], 0);
    }

    // working
    @Test
    public void getDateStrings()
    {
        String input = "Start Date: Monday, 23 March 2020 - 00:00<br />End Date: Friday, 27 March 2020 - 00:00<br />Delay Information: No reported delay.";
        String[] output = formatter.getDateStrings(input);
        String[] expected = {"Monday, 23 March 2020", "Friday, 27 March 2020"};
        assertEquals(expected[0], output[0]);
        assertEquals(expected[1], output[1]);
    }

    // not working
    @Test
    public void getCalendarFromString()
    {
        String input = "Monday, 23 March 2020";
        Calendar expected = Calendar.getInstance();
        expected.setTimeZone(TimeZone.getTimeZone("GMT"));
        expected.set(2020, 3, 23, 0, 0, 0);
        assertEquals(expected.getTime().toString(), formatter.getCalendarFromString(input).getTime().toString());
    }

    // working
    @Test
    public void getDurationRating()
    {
        int low = 1;
        int med = 2;
        int high = 3;

        Calendar inputStart1 = Calendar.getInstance();
        inputStart1.set(2020, 3, 1);
        Calendar inputEnd1 = Calendar.getInstance();
        inputEnd1.set(2020, 3, 2);

        Calendar inputStart2 = Calendar.getInstance();
        inputStart2.set(2020, 3, 1);
        Calendar inputEnd2 = Calendar.getInstance();
        inputEnd2.set(2020, 3, 4);

        Calendar inputStart3 = Calendar.getInstance();
        inputStart3.set(2020, 3, 1);
        Calendar inputEnd3 = Calendar.getInstance();
        inputEnd3.set(2020, 3, 10);

        int expectedLow = formatter.getDurationRating(inputStart1, inputEnd1);
        int expectedMed = formatter.getDurationRating(inputStart2, inputEnd2);
        int expectedHigh = formatter.getDurationRating(inputStart3, inputEnd3);

        assertEquals(low, expectedLow);
        assertEquals(med, expectedMed);
        assertEquals(high, expectedHigh);
    }

    // working
    @Test
    public void convertLineBreaks()
    {
        String input = "Start Date: Thursday, 26 March 2020 - 00:00<br />End Date: Friday, 27 March 2020 - 00:00<br />Works: Gantry Works Traffic Management: Road Closure. Diversion Information: Diversion will be Jct 2 SB On slip to Jct 1c Admiralty and return.";
        String output = formatter.convertLineBreaks(input);
        String expected = "Start Date: Thursday, 26 March 2020 - 00:00\nEnd Date: Friday, 27 March 2020 - 00:00\nWorks: Gantry Works \nTraffic Management: Road Closure. \nDiversion Information: Diversion will be Jct 2 SB On slip to Jct 1c Admiralty and return.";

        assertEquals(expected, output);
    }
}
package com.rosscrawford.mpdtrafficscotland;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author : Ross Crawford
 * @matriculation no. : S1821950
 * @university : Glasgow Caledonian University
 * @module : Mobile Platform Development
 * @created : 21/03/2020
 **/

public class RssFeed extends AsyncTask<String, Integer, Void>
{
    @SuppressLint("StaticFieldLeak")
    private MainActivity activity;

    public RssFeed()
    {
        // empty constructor
    }

    RssFeed(MainActivity activity)
    {
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(String... strings)
    {
        StringBuilder result = new StringBuilder();
        try
        {
            URL url = new URL(strings[0]);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String input;
            while ((input = reader.readLine()) != null)
            {
               result.append(input);
            }
            reader.close();
            TrafficApplication.itemsString = result.toString();
            XmlParser xmlParser = new XmlParser();
            xmlParser.parseData(TrafficApplication.itemsString);
            Log.d("Test", TrafficApplication.items.size() + "");
            if (!TrafficApplication.items.isEmpty())
            {
                Log.d("Test", TrafficApplication.items.get(0).getPublished());
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        activity.loading(true);
        Log.d("Test", "Execution started");
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
        Log.d("Test", "Execution complete " + TrafficApplication.itemsString);
        Intent intent = new Intent();
        intent.setClass(activity.getApplicationContext(), ItemList.class);
        activity.startActivity(intent);
        activity.loading(false);
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        super.onProgressUpdate(values);
    }
}

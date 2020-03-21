package com.rosscrawford.mpdtrafficscotland;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

/**
 * @author : Ross Crawford
 * @matriculation no. : S1821950
 * @university : Glasgow Caledonian University
 * @module : Mobile Platform Development
 * @created : 21/03/2020
 **/
public class RssFeed extends AsyncTask<String, Integer, Void>
{
    String input;
    String result;
    URL url;
    URLConnection connection;
    BufferedReader reader;

    public RssFeed()
    {
        // empty constructor
    }

    @Override
    protected Void doInBackground(String... strings)
    {
        Log.d("Test", "Executing... " + strings[0]);
        result = "";
        try
        {
            url = new URL(strings[0]);
            Log.d("Test", "URL : " + url);
            connection = url.openConnection();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((input = reader.readLine()) != null)
            {
               result = result + input;
            }
            reader.close();
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
        Log.d("Test", "Execution started");
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
        Log.d("Test", "Execution complete " + result);
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        super.onProgressUpdate(values);
    }
}

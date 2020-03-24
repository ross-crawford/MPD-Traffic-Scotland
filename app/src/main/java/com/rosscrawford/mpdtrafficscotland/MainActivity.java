package com.rosscrawford.mpdtrafficscotland;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;

/**
 * @author : Ross Crawford
 * @matriculation no. : S1821950
 * @university : Glasgow Caledonian University
 * @module : Mobile Platform Development
 * @created : 21/03/2020
 **/

public class MainActivity extends AppCompatActivity
{

    Formatter formatter;
    ConstraintLayout content, loader;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        formatter = new Formatter();
        content = findViewById(R.id.content);
        loader = findViewById(R.id.loader);
    }

    public void openCurrentIncidents(View view)
    {
        RssFeed rss = new RssFeed(this);
        rss.execute(TrafficApplication.currentIncidentsUrl);
        TrafficApplication.feedName = "Current Incidents";
    }

    public void openCurrentRoadworks(View view)
    {
        RssFeed rss = new RssFeed(this);
        rss.execute(TrafficApplication.currentRoadworksUrl);
        TrafficApplication.feedName = "Current Roadworks";
    }

    public void openPlannedRoadworks(View view)
    {
        RssFeed rss = new RssFeed(this);
        rss.execute(TrafficApplication.plannedRoadworksUrl);
        TrafficApplication.feedName = "Planned Roadworks";
    }

    public void loading(final boolean show)
    {
        loader.setVisibility(show ? View.VISIBLE : View.GONE);
        content.setVisibility(show ? View.GONE : View.VISIBLE);
    }
}

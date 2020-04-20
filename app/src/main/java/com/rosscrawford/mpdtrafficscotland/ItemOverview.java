package com.rosscrawford.mpdtrafficscotland;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * @author : Ross Crawford
 * @matriculation no. : S1821950
 * @university : Glasgow Caledonian University
 * @module : Mobile Platform Development
 * @created : 21/03/2020
 **/

public class ItemOverview extends AppCompatActivity implements OnMapReadyCallback
{

    private ActionBar actionBar;
    private TextView tvTitle, tvDescription, tvPublished;
    private Intent intent;
    private Item item;

    private GoogleMap map;
    private UiSettings mapSettings;
    private double[] latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_overview);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setSubtitle("Details");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        tvTitle = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);
        tvPublished = findViewById(R.id.tvPublished);

        intent = getIntent();

        if (intent.getExtras() != null)
        {
            item = (Item) intent.getSerializableExtra("data");

            tvTitle.setText(item.getTitle());
            tvDescription.setText(item.getDescription());
            tvPublished.setText(String.format("%s%s", getString(R.string.published_label), item.getPublished().substring(0, item.getPublished().length() - 13)));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        map = googleMap;
        latLng = item.getLatLng();
        mapSettings = map.getUiSettings();
        mapSettings.setZoomControlsEnabled(true);
        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json));
        LatLng position = new LatLng(latLng[0], latLng[1]);
        map.addMarker(new MarkerOptions().position(position).title(item.getTitle()).visible(true));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 15));
    }
}

package com.rosscrawford.mpdtrafficscotland;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {

    private GoogleMap map;
    private MapView mapView;
    private UiSettings mapSettings;
    private ItemOverview overview;
    private double[] latLng;
    private Item item;

    public MapFragment() {
        // Required empty public constructor
        //overview = (ItemOverview) getActivity();
        //assert overview != null;
        //item = overview.getItem();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    //@Override
    //public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    //{
    //    super.onViewCreated(view, savedInstanceState);
    //    mapView = view.findViewById(R.id.mapView);
    //    if (mapView != null) {
    //        mapView.onCreate(null);
    //        mapView.onResume();
    //        mapView.getMapAsync(this);
    //    }
    //}
    //
    //@Override
    //public void onMapReady(GoogleMap googleMap) {
    //    map = googleMap;
    //    latLng = item.getLatLng();
    //    mapSettings = map.getUiSettings();
    //    mapSettings.setZoomControlsEnabled(true);
    //    map.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.style_json));
    //    LatLng position = new LatLng(latLng[0], latLng[1]);
    //    map.addMarker(new MarkerOptions().position(position).title(item.getTitle()).visible(true));
    //    map.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 14));
    //}
}

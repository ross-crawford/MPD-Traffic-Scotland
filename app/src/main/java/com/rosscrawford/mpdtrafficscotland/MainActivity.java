package com.rosscrawford.mpdtrafficscotland;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Formatter formatter;
    ConstraintLayout content, loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        formatter = new Formatter();
        content = findViewById(R.id.content);
        loader = findViewById(R.id.loader);
    }

    public void openCurrentIncidents(View view) {
        Toast.makeText(this, "Current Incidents Clicked", Toast.LENGTH_SHORT).show();
        //formatter.getLatLng("55.8208549651854 -4.33758965118342");
        //formatter.getCalendarFromString("Friday, 20 March 2020");
        //formatter.getDurationRating(formatter.getCalendarFromString("Friday, 20 March 2020"), formatter.getCalendarFromString("Friday, 27 March 2020"));
        //formatter.convertLineBreaks("Start Date: Friday, 20 March 2020 - 00:00<br />End Date: Saturday, 21 March 2020 - 00:00<br />Delay Information: No reported delay.");
        //loading(true);
        //final Timer timer = new Timer();
        //timer.schedule(new TimerTask() {
        //    @Override
        //    public void run() {
        //        runOnUiThread(new Runnable() {
        //            @Override
        //            public void run() {
        //                loading(false);
        //            }
        //        });
        //        timer.cancel();
        //    }
        //}, 5000);


    }

    public void openCurrentRoadworks(View view) {
        Toast.makeText(this, "Current Roadworks Clicked", Toast.LENGTH_SHORT).show();
    }

    public void openPlannedRoadworks(View view) {
        Toast.makeText(this, "Planned Roadworks Clicked", Toast.LENGTH_SHORT).show();
    }

    public void loading(final boolean show)
    {
        loader.setVisibility(show ? View.VISIBLE : View.GONE);
        content.setVisibility(show ? View.GONE : View.VISIBLE);
    }
}

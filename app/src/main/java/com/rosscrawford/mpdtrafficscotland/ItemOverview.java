package com.rosscrawford.mpdtrafficscotland;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ItemOverview extends AppCompatActivity {

    ActionBar actionBar;
    TextView tvTitle, tvDescription, tvPublished;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_overview);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle("Details");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        tvTitle = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);
        tvPublished = findViewById(R.id.tvPublished);

        intent = getIntent();

        if (intent.getExtras() != null)
        {
            Item item = (Item) intent.getSerializableExtra("data");

            tvTitle.setText(item.getTitle());
            tvDescription.setText(item.getDescription());
            tvPublished.setText(item.getPublished());
        }
    }
}

package com.rosscrawford.mpdtrafficscotland;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

public class ItemList extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText etFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setSubtitle(TrafficApplication.feedName);
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.rvList);
        etFilter = findViewById(R.id.etFilter);

        ItemAdapter adapter = new ItemAdapter(this,TrafficApplication.items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


}

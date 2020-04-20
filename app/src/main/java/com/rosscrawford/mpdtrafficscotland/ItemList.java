package com.rosscrawford.mpdtrafficscotland;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author : Ross Crawford
 * @matriculation no. : S1821950
 * @university : Glasgow Caledonian University
 * @module : Mobile Platform Development
 * @created : 21/03/2020
 **/

public class ItemList extends AppCompatActivity implements ItemAdapter.ItemSelected
{

    ActionBar actionBar;
    RecyclerView recyclerView;
    LinearLayout empty;
    ItemAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(TrafficApplication.feedName);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (findViewById(R.id.landscapeList) != null)
        {
            layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        }
        else
        {
            layoutManager = new LinearLayoutManager(this);
        }

        recyclerView = findViewById(R.id.rvList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        items = new ArrayList<>(TrafficApplication.items);
        empty = findViewById(R.id.noResults);

        // message display for no results - needs amending to work in the filter too
        if (items.isEmpty())
        {
            recyclerView.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
        }

        adapter = new ItemAdapter(items, this);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void itemSelected(Item item)
    {
        startActivity(new Intent(ItemList.this, ItemOverview.class).putExtra("data", item));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem menuItemSearch = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItemSearch.getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();

        if (id == R.id.action_search)
        {
            return true;
        }
        if (id == R.id.action_date)
        {
            // Get Current Date
            int year, month, day;
            final Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    // this is where we would filter the recyclerview list to display a filtered result set based on the date input matching the Calendar dates
                    // need to first store the date selected
                    // then query against the list being shown
                    // would need to determine if the selected date is greater than start date or less than end date
                    // would need to disable this feature in the current incidents as no dates shown on there
                    Toast.makeText(ItemList.this, dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_SHORT).show();
                }
            }, year, month, day);
            datePickerDialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

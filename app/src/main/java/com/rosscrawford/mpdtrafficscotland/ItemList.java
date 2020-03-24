package com.rosscrawford.mpdtrafficscotland;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class ItemList extends AppCompatActivity implements ItemAdapter.ItemSelected
{

    ActionBar actionBar;
    RecyclerView recyclerView;
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

        recyclerView = findViewById(R.id.rvList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        items = new ArrayList<>(TrafficApplication.items);

        adapter = new ItemAdapter(this, items, this);

        recyclerView.setAdapter(adapter);
    }

    // not working
    //@Override
    //public void onItemSelected(int index)
    //{
    //    startActivity(new Intent(ItemList.this, ItemOverview.class).putExtra("data", items.get(index)));
    //}
    @Override
    public void itemSelected(Item item) {
        startActivity(new Intent(ItemList.this, ItemOverview.class).putExtra("data", item));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
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
        return super.onOptionsItemSelected(item);
    }
}

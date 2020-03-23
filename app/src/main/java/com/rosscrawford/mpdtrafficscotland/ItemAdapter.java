package com.rosscrawford.mpdtrafficscotland;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author : Ross Crawford
 * @matriculation no. : S1821950
 * @university : Glasgow Caledonian University
 * @module : Mobile Platform Development
 * @created : 21/03/2020
 **/

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>
{
    private ArrayList<Item> items;
    private Context context;

    ItemAdapter(Context context, ArrayList<Item> items)
    {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.traffic_item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Item current = items.get(position);
        holder.title.setText(current.getTitle());
        holder.description.setText(current.getDescription());
        holder.published.setText(current.getPublished());
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView title, description, published;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            description = itemView.findViewById(R.id.tvDescription);
            published = itemView.findViewById(R.id.tvPublished);
        }
    }
}

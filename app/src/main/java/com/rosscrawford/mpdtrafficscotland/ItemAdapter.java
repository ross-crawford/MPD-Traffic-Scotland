package com.rosscrawford.mpdtrafficscotland;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collection;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author : Ross Crawford
 * @matriculation no. : S1821950
 * @university : Glasgow Caledonian University
 * @module : Mobile Platform Development
 * @created : 21/03/2020
 **/

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> implements Filterable
{
    private ArrayList<Item> items;
    private ArrayList<Item> itemsFiltered;
    private ItemSelected activity;

    public interface ItemSelected
    {
        void itemSelected(Item item);
    }

    public ItemAdapter(Context context, ArrayList<Item> items, ItemSelected activity)
    {
        this.items = items;
        this.itemsFiltered = items;
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvTitle;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    activity.itemSelected(items.get(getAdapterPosition()));
                }
            });
        }
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.traffic_item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position)
    {
        holder.itemView.setTag(items.get(position));
        holder.tvTitle.setText(items.get(position).getTitle());
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    @Override
    public Filter getFilter()
    {
        Filter filter = new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence constraint)
            {
                FilterResults filterResults = new FilterResults();
                if (constraint == null | constraint.length() == 0)
                {
                    filterResults.count = itemsFiltered.size();
                    filterResults.values = itemsFiltered;
                }
                else
                {
                    String query = constraint.toString().toLowerCase();
                    ArrayList<Item> resultsData = new ArrayList<>();

                    for (Item item : itemsFiltered)
                    {
                        if (item.getTitle().toLowerCase().contains(query))
                        {
                            resultsData.add(item);
                        }
                    }
                    filterResults.count = resultsData.size();
                    filterResults.values = resultsData;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results)
            {
                items = (ArrayList<Item>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}

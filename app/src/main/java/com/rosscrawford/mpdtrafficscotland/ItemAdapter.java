package com.rosscrawford.mpdtrafficscotland;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Calendar;

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

    public ItemAdapter(ArrayList<Item> items, ItemSelected activity)
    {
        this.items = items;
        this.itemsFiltered = items;
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvTitle;
        ImageView ivRating;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivRating = itemView.findViewById(R.id.ivRating);

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
        switch (items.get(position).getRating())
        {
            case 1:
                holder.ivRating.setImageResource(R.drawable.rating_low);
                break;
            case 2:
                holder.ivRating.setImageResource(R.drawable.rating_med);
                break;
            case 3:
                holder.ivRating.setImageResource(R.drawable.rating_high);
                break;
            default:
                holder.ivRating.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    @Override
    public Filter getFilter()
    {
        return new Filter()
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
    }

    public void filterByCalendar(Calendar query){
        items = itemsFiltered;
        ArrayList<Item> filteredItems = new ArrayList<>();
        for (Item item : items) {
            if (item.getStart().getTimeInMillis() < query.getTimeInMillis() && item.getEnd().getTimeInMillis() > query.getTimeInMillis())
            {
                filteredItems.add(item);
            }
        }
        items = filteredItems;
        notifyDataSetChanged();
    }
}

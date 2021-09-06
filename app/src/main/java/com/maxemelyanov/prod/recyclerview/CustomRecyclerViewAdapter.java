package com.maxemelyanov.prod.recyclerview;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.maxemelyanov.prod.R;
import com.maxemelyanov.prod.model.Data;

import java.util.List;

public class CustomRecyclerViewAdapter<T extends Data> extends RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<T> items;

    public CustomRecyclerViewAdapter(Context context, List<T> items){
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CustomRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerViewAdapter.ViewHolder holder, int position) {
        Pair<String, String> pair = items.get(position).getData();
        holder.headerView.setText(pair.first);
        holder.balanceView.setText(pair.second);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView headerView, balanceView;
        ViewHolder(View view){
            super(view);
            headerView = (TextView) view.findViewById(R.id.first_element);
            balanceView = (TextView) view.findViewById(R.id.second_element);
        }
    }

}

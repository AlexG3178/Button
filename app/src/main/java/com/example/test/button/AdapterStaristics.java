package com.example.test.button;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AdapterStaristics extends RecyclerView.Adapter<AdapterStaristics.ViewHolder>{
    private String[] data;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ViewHolder(TextView tv) {
            super(tv);
            textView = tv;
        }
    }

    public AdapterStaristics(String[] data1) {
        data = data1;
    }

    @Override
    public AdapterStaristics.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.statistic_item, parent, false);
        return new ViewHolder((TextView)(v));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.textView.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}


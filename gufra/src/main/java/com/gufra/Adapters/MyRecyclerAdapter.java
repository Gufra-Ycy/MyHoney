package com.gufra.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class MyRecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter,RecyclerAdapter.RecyclerHolder> {

    @Override
    public RecyclerAdapter onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter recyclerAdapter, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {

    public RecyclerHolder(View itemView) {
        super(itemView);
    }
}
}

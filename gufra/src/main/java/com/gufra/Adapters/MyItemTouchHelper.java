package com.gufra.Adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class MyItemTouchHelper extends ItemTouchHelper.Callback{
    private ItemTouchAdapter adapter ;

    public MyItemTouchHelper (ItemTouchAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

        return makeMovementFlags(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        Log.d("SimpleItemTouchHelper", "onSwiped, onMove, source=" + viewHolder.getAdapterPosition() + ",target=" + viewHolder1.getAdapterPosition());
        adapter.onItemMove(viewHolder.getAdapterPosition(),viewHolder1.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        adapter.onItemDissmiss(viewHolder.getAdapterPosition());
    }
}

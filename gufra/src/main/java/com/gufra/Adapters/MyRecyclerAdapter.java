package com.gufra.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gufra.gufra.R;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyRecyclerHolder>  implements ItemTouchAdapter {

    private List<String> mList = new ArrayList<String>();
    public MyRecyclerAdapter(List<String> list) {
        mList = list;
    }

    @Override
    public MyRecyclerHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view,parent,false);
        return new MyRecyclerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerHolder myRecyclerHolder, int i) {
        myRecyclerHolder.setmText(mList.get(i));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //itemTouchAdapter的移动
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        notifyItemMoved(fromPosition,toPosition);
    }
    //itemTouchAdapter的删除
    @Override
    public void onItemDissmiss(int position) {
        notifyItemRemoved(position);
    }

    class MyRecyclerHolder extends RecyclerView.ViewHolder{

        TextView mText;
        public MyRecyclerHolder(@NonNull View itemView) {
            super(itemView);
            mText = (TextView)itemView.findViewById(R.id.item_text);
        }
        public void setmText(String txt){
            mText.setText(txt);
        }
    }
}


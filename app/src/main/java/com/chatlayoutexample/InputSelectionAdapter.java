package com.chatlayoutexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by v-ruchd on 3/24/2017.
 */

public class InputSelectionAdapter extends RecyclerView.Adapter<InputSelectionAdapter.ViewHolder> {


    private final LayoutInflater mLayoutInflater;

    public InputSelectionAdapter(Context context){
        mLayoutInflater= LayoutInflater.from(context);
    }

    @Override
    public InputSelectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem=mLayoutInflater.inflate(R.layout.chat_input_selection_item,null);
        ViewHolder viewHolder=new ViewHolder(viewItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(InputSelectionAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}

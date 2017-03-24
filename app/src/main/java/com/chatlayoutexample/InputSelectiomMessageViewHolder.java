package com.chatlayoutexample;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by v-ruchd on 3/24/2017.
 */
public class InputSelectiomMessageViewHolder extends RecyclerView.ViewHolder {
public RecyclerView mRecyclerView;
    public InputSelectiomMessageViewHolder(View itemView) {
        super(itemView);
        mRecyclerView=(RecyclerView)itemView.findViewById(R.id.horizontal_list);
    }
}

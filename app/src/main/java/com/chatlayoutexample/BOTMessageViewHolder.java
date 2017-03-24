package com.chatlayoutexample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by v-ruchd on 3/24/2017.
 */
public class BOTMessageViewHolder extends RecyclerView.ViewHolder{
    public TextView mTextMessageTextView;
    public BOTMessageViewHolder(View itemView) {
        super(itemView);
        mTextMessageTextView=(TextView) itemView.findViewById(R.id.txt_msg);
    }
}

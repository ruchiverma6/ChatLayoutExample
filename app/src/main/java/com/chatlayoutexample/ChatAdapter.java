package com.chatlayoutexample;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Technovibe on 17-04-2015.
 */
public class ChatAdapter extends RecyclerView.Adapter {
    private static final int TYPE_BOT = 0;
    private static final int TYPE_USER = 1;
    private static final int TYPE_INPUT_SELETION_RESULT = 2;
    private static final int TYPE_NEWS_CHANNELS_RESULT = 3;
    private LayoutInflater inflater;
    private RecyclerView.ViewHolder viewHolder;




    @Override
    public int getItemViewType(int position) {
        if (chatMessages.get(position).getType()==TYPE_USER) {
            return TYPE_USER;
        } else if (chatMessages.get(position).getType()==TYPE_BOT) {
            return TYPE_BOT;
        }
        else if (chatMessages.get(position).getType()==TYPE_INPUT_SELETION_RESULT) {
            return TYPE_INPUT_SELETION_RESULT;
        }
        return -1;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_USER:
                View v1 = inflater.inflate(R.layout.item_chat_left, parent, false);
                viewHolder = new UserMessageViewHolder(v1);
                break;

            case TYPE_BOT:
                View userMessageViewHolder = inflater.inflate(R.layout.item_chat_right, parent, false);
                viewHolder = new BOTMessageViewHolder(userMessageViewHolder);
                break;
            case TYPE_INPUT_SELETION_RESULT:
                View inputSelectionViewHolder = inflater.inflate(R.layout.chat_inputselection_layout, parent, false);
                viewHolder = new InputSelectiomMessageViewHolder(inputSelectionViewHolder);
                break;

            case TYPE_NEWS_CHANNELS_RESULT:
                View newChannelResultViewHolder = inflater.inflate(R.layout.item_chat_right, parent, false);
                viewHolder = new NewsChannelResultViewHolder(newChannelResultViewHolder);
                break;

            default:
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        configureViewHolder1(viewHolder.getItemViewType(), holder, position);

    }

    private void configureViewHolder1(int type, RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()) {
            case TYPE_USER:

                UserMessageViewHolder userMessageViewHolder = (UserMessageViewHolder) viewHolder;
                userMessageViewHolder.mTextMessageTextView.setText(chatMessages.get(position).getMessage());
                break;
            case TYPE_BOT:

                BOTMessageViewHolder holder = (BOTMessageViewHolder) viewHolder;
                holder.mTextMessageTextView.setText(chatMessages.get(position).getMessage());
                break;

            case TYPE_INPUT_SELETION_RESULT:
                InputSelectiomMessageViewHolder inputSelectiomMessageViewHolder = (InputSelectiomMessageViewHolder) viewHolder;
                inputSelectiomMessageViewHolder.mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                inputSelectiomMessageViewHolder.mRecyclerView.setAdapter(new InputSelectionAdapter(context));
                break;

            case TYPE_NEWS_CHANNELS_RESULT:
                NewsChannelResultViewHolder channelResultViewHolder = (NewsChannelResultViewHolder) viewHolder;

                break;
            default:

                break;
        }

    }

    @Override
    public int getItemCount() {
        if (chatMessages != null) {
            return chatMessages.size();
        } else {
            return 0;
        }
    }

    private final List<ChatMessage> chatMessages;
    private Activity context;

    public ChatAdapter(Activity context, List<ChatMessage> chatMessages) {
        this.context = context;
        this.chatMessages = chatMessages;
        inflater=LayoutInflater.from(context);
    }



 /*   @Override
    public ChatMessage getItem(int position) {
        if (chatMessages != null) {
            return chatMessages.get(position);
        } else {
            return null;
        }
    }*/


    @Override
    public long getItemId(int position) {
        return position;
    }


    /* @Override
     public View getView(final int position, View convertView, ViewGroup parent) {
         ViewHolder holder;
         ChatMessage chatMessage = getItem(position);
         LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

         if (convertView == null) {
             convertView = vi.inflate(R.layout.list_item_chat_message, null);
             holder = createViewHolder(convertView);
             convertView.setTag(holder);
         } else {
             holder = (ViewHolder) convertView.getTag();
         }

         boolean myMsg = chatMessage.getType() ;//Just a dummy check to simulate whether it me or other sender
         setAlignment(holder, myMsg);
         holder.txtMessage.setText(chatMessage.getMessage());
         holder.txtInfo.setText(chatMessage.getDate());


         return convertView;
     }
 */
    public void add(ChatMessage message) {
        chatMessages.add(message);
    }

    public void add(List<ChatMessage> messages) {
        chatMessages.addAll(messages);
    }

    private void setAlignment(ViewHolder holder, boolean isMe) {
        if (!isMe) {
            holder.contentWithBG.setBackgroundResource(R.drawable.in_message_bg);

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.contentWithBG.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            holder.contentWithBG.setLayoutParams(layoutParams);

            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) holder.content.getLayoutParams();
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.content.setLayoutParams(lp);
            layoutParams = (LinearLayout.LayoutParams) holder.txtMessage.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            holder.txtMessage.setLayoutParams(layoutParams);

            layoutParams = (LinearLayout.LayoutParams) holder.txtInfo.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            holder.txtInfo.setLayoutParams(layoutParams);
        } else {
            holder.contentWithBG.setBackgroundResource(R.drawable.out_message_bg);

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.contentWithBG.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            holder.contentWithBG.setLayoutParams(layoutParams);

            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) holder.content.getLayoutParams();
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            holder.content.setLayoutParams(lp);
            layoutParams = (LinearLayout.LayoutParams) holder.txtMessage.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            holder.txtMessage.setLayoutParams(layoutParams);

            layoutParams = (LinearLayout.LayoutParams) holder.txtInfo.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            holder.txtInfo.setLayoutParams(layoutParams);
        }
    }
/*
    private ViewHolder createViewHolder(View v) {
        ViewHolder holder = new ViewHolder();
        holder.txtMessage = (TextView) v.findViewById(R.id.txtMessage);
        holder.content = (LinearLayout) v.findViewById(R.id.content);
        holder.contentWithBG = (LinearLayout) v.findViewById(R.id.contentWithBackground);
        holder.txtInfo = (TextView) v.findViewById(R.id.txtInfo);
        return holder;
    }*/


    private static class ViewHolder {
        public TextView txtMessage;
        public TextView txtInfo;
        public LinearLayout content;
        public LinearLayout contentWithBG;
    }
}

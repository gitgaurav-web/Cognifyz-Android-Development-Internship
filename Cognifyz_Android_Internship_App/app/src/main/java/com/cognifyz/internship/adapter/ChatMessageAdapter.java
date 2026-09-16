package com.cognifyz.internship.adapter;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cognifyz.internship.R;
import com.cognifyz.internship.model.ChatMessage;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.MessageViewHolder> {

    private List<ChatMessage> messageList;

    public ChatMessageAdapter(List<ChatMessage> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        ChatMessage msg = messageList.get(position);
        holder.tvMessageBody.setText(msg.getText());
        holder.tvMessageTime.setText(msg.getTimestamp());

        if (msg.isUser()) {
            holder.layoutRoot.setGravity(Gravity.END);
            holder.cardBubble.setCardBackgroundColor(Color.parseColor("#E8EAF6"));
            holder.tvSenderName.setText("You (Gaurav)");
            holder.tvSenderName.setTextColor(Color.parseColor("#1A237E"));
        } else {
            holder.layoutRoot.setGravity(Gravity.START);
            holder.cardBubble.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.tvSenderName.setText("Cognifyz AI ");
            holder.tvSenderName.setTextColor(Color.parseColor("#00897B"));
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutRoot;
        MaterialCardView cardBubble;
        TextView tvSenderName, tvMessageBody, tvMessageTime;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutRoot = itemView.findViewById(R.id.layout_message_root);
            cardBubble = itemView.findViewById(R.id.card_message_bubble);
            tvSenderName = itemView.findViewById(R.id.tv_sender_name);
            tvMessageBody = itemView.findViewById(R.id.tv_message_body);
            tvMessageTime = itemView.findViewById(R.id.tv_message_time);
        }
    }
}


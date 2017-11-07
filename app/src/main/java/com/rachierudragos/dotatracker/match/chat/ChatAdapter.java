package com.rachierudragos.dotatracker.match.chat;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Wrapper.match.Chat;

import java.util.List;

/**
 * Created by Dragos on 11.01.2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ItemHolder> {

    Context context;
    List<Chat> chats;

    public ChatAdapter(Context context, List<Chat> chats) {
        this.context = context;
        this.chats = chats;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Chat chat = chats.get(position);
        holder.textPerson.setText(chat.getName());
        if (chat.slot < 5)
            holder.textPerson.setTextColor(ContextCompat.getColor(context,R.color.green));
        else
            holder.textPerson.setTextColor(ContextCompat.getColor(context,R.color.red));
        holder.textMessage.setText(chat.getMessage());
        String text = "";
        int time = chat.getTime();
        if (time < 0) {
            text += "-";
            time = -time;
        }
        int seconds = time % 60;
        text += time / 60 + ":";
        text += (seconds < 10 ? "0" : "") + seconds;
        holder.textWhen.setText(text);
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    protected class ItemHolder extends RecyclerView.ViewHolder {
        TextView textPerson;
        TextView textMessage;
        TextView textWhen;

        public ItemHolder(View view) {
            super(view);
            textMessage = itemView.findViewById(R.id.text_chat_message);
            textPerson = itemView.findViewById(R.id.text_chat_person);
            textWhen = itemView.findViewById(R.id.text_chat_when);
        }
    }
}

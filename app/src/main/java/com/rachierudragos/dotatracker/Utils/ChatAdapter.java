package com.rachierudragos.dotatracker.Utils;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Wrapper.match.MatchDetail;

import java.util.List;

/**
 * Created by Dragos on 11.01.2017.
 */

public class ChatAdapter extends ArrayAdapter<MatchDetail.Chat> {
    public ChatAdapter(Context context, List<MatchDetail.Chat> chats) {
        super(context, 0, chats);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MatchDetail.Chat chat = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_chat, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.chat_person)).setText(chat.getName());
        if (chat.slot < 5)
            ((TextView) convertView.findViewById(R.id.chat_person)).setTextColor(Color.GREEN);
        else
            ((TextView) convertView.findViewById(R.id.chat_person)).setTextColor(Color.RED);
        ((TextView) convertView.findViewById(R.id.chat_message)).setText(chat.getMessage());
        String text = "";
        int time = chat.getTime();
        if (time < 0) {
            text += "-";
            time = -time;
        }
        int seconds = time % 60;
        text += time / 60 + ":";
        text += (seconds < 10 ? "0" : "") + seconds;
        ((TextView) convertView.findViewById(R.id.chat_minute)).setText(text);
        return convertView;
    }
}

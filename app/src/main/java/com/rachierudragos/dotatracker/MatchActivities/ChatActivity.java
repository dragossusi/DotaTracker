package com.rachierudragos.dotatracker.MatchActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Utils.ChatAdapter;
import com.rachierudragos.dotatracker.Wrapper.match.MatchDetail;

import java.util.List;

public class ChatActivity extends AppCompatActivity {
    List<MatchDetail.Chat> chats;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chats = (List<MatchDetail.Chat>) getIntent().getSerializableExtra("chat");
        listView = (ListView)findViewById(R.id.list_chat);
        listView.setAdapter(new ChatAdapter(this,chats));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

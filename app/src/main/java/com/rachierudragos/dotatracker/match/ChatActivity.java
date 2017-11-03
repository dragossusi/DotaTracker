package com.rachierudragos.dotatracker.match;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ListView;

import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Wrapper.match.Chat;
import com.rachierudragos.dotatracker.adapter.ChatAdapter;
import com.rachierudragos.dotatracker.Wrapper.match.MatchDetail;

import java.util.List;

public class ChatActivity extends AppCompatActivity {
    List<Chat> chats;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chats = getIntent().getParcelableExtra("chat");
        recyclerView =  findViewById(R.id.recycler_chat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new ChatAdapter(this, chats));
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

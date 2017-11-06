package com.rachierudragos.dotatracker.match;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.rachierudragos.dotatracker.vars.App;
import com.rachierudragos.dotatracker.Wrapper.ODotaAPI2;
import com.rachierudragos.dotatracker.adapter.MatchHeroAdapter;
import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Wrapper.match.MatchDetail;
import com.rachierudragos.dotatracker.match.detail.MatchPlayerDetailActivity;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An activity representing a list of Matches. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MatchPlayerDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MatchDetailActivity extends AppCompatActivity {
    private MatchDetail matchDetail;
    private Context context;
    private long match_id;

    RecyclerView recyclerView;
    ODotaAPI2 api;
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);

        recyclerView = findViewById(R.id.match_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle(getTitle());
        context = this;
        Intent intentAnt = getIntent();
        match_id = intentAnt.getLongExtra("match_id", 0);
        if (matchDetail == null) {
            api = ((App) getApplication()).getApi();
            Call<MatchDetail> call = api.getMatch(match_id);
            call.enqueue(new Callback<MatchDetail>() {
                @Override
                public void onResponse(Call<MatchDetail> call, Response<MatchDetail> response) {
                    matchDetail = response.body();
                    recyclerView.setNestedScrollingEnabled(false);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MatchDetailActivity.this, LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(new MatchHeroAdapter(MatchDetailActivity.this, matchDetail.players, findViewById(R.id.match_detail_container) != null));
                }

                @Override
                public void onFailure(Call<MatchDetail> call, Throwable t) {
                    Toast.makeText(MatchDetailActivity.this, "Match not working or no network connection", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public MatchDetail getMatchDetail() {
        return matchDetail;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("match", matchDetail);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        matchDetail = savedInstanceState.getParcelable("match");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_match, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_graphs) {
            Intent intent = new Intent(this, GraphActivity.class);
            intent.putExtra("gpm", (Serializable) matchDetail.radiant_gold_adv);
            intent.putExtra("xpm", (Serializable) matchDetail.radiant_xp_adv);
            context.startActivity(intent);
            return true;
        }
        if (id == R.id.action_chat) {
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("chat", (Serializable) matchDetail.chat);
            context.startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

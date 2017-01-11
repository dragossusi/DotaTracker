package com.rachierudragos.dotatracker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rachierudragos.dotatracker.Wrapper.hero.HeroDatabase;
import com.rachierudragos.dotatracker.Wrapper.match.MatchDetail;

import java.io.Serializable;
import java.util.List;

/**
 * An activity representing a list of Matches. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MatchDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MatchListActivity extends AppCompatActivity {
    private MatchDetail matchDetail;
    private Context context;
    private long match_id;
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle(getTitle());
        context = this;
        Intent intentAnt = getIntent();
        match_id = intentAnt.getLongExtra("match_id",0);
        new GetMatch().execute(null,null,null);
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
            Intent intent = new Intent(this,GraphActivity.class);
            intent.putExtra("gpm",(Serializable)matchDetail.radiant_gold_adv);
            intent.putExtra("xpm",(Serializable)matchDetail.radiant_xp_adv);
            context.startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(matchDetail.players));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<MatchDetail.Player> mValues;

        public SimpleItemRecyclerViewAdapter(List<MatchDetail.Player> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.match_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            if(holder.mItem.personaname!=null)
                holder.mIdView.setText(holder.mItem.personaname);
            else
                holder.mIdView.setText("Unknown");
            if (position < 5)
                holder.mView.setBackgroundColor(Color.GREEN);
            else
                holder.mView.setBackgroundColor(Color.RED);
            holder.mContentView.setImageResource(getResources().getIdentifier(HeroDatabase.getHeroIdName(holder.mItem.hero_id), "drawable", getPackageName()));

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putSerializable("player", holder.mItem);
                        MatchDetailFragment fragment = new MatchDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.match_detail_container, fragment)
                                .commit();
                    } else {
                        Intent intent = new Intent(context, MatchDetailActivity.class);
                        intent.putExtra("player", holder.mItem);
                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final ImageView mContentView;
            public MatchDetail.Player mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id_player_detail);
                mContentView = (ImageView) view.findViewById(R.id.playerhero);
            }
        }
    }

    private class GetMatch extends AsyncTask<Void, Void, Boolean> {
        protected Boolean doInBackground(Void... urls) {
            try {
                matchDetail = MainActivity.api.getMatch(match_id);
                return true;
            } catch (Exception e) {
                // Do something if an error occurs
                e.printStackTrace();
                return false;
            }
        }

        protected void onProgressUpdate(Void... progress) {

        }

        protected void onPostExecute(Boolean result) {
            if(result) {
                View recyclerView = findViewById(R.id.match_list);
                assert recyclerView != null;
                setupRecyclerView((RecyclerView) recyclerView);
                if (findViewById(R.id.match_detail_container) != null) {
                    // The detail container view will be present only in the
                    // large-screen layouts (res/values-w900dp).
                    // If this view is present, then the
                    // activity should be in two-pane mode.
                    mTwoPane = true;
                }
            } else
                Toast.makeText(MatchListActivity.this,"Match not working or no network connection",Toast.LENGTH_LONG).show();
        }
    }
}

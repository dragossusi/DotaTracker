package com.rachierudragos.dotatracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rachierudragos.dotatracker.Wrapper.HeroDetail;
import com.rachierudragos.dotatracker.Wrapper.domain.matchdetail.MatchDetail;
import com.rachierudragos.dotatracker.Wrapper.domain.matchdetail.MatchDetailPlayer;

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
        context=this;
        Intent intentAnt = getIntent();
        matchDetail = (MatchDetail) intentAnt.getSerializableExtra("match");
        System.out.println("level erou"+matchDetail.getPlayers().get(0).getHeroLevel());
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
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(matchDetail.getPlayers()));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<MatchDetailPlayer> mValues;

        public SimpleItemRecyclerViewAdapter(List<MatchDetailPlayer> items) {
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
            holder.mIdView.setText(String.valueOf(mValues.get(position).getAccountId()));
            holder.mContentView.setImageResource(getResources().getIdentifier(HeroDetail.getHeroName(mValues.get(position).getHeroId()), "drawable", getPackageName()));//mValues.get(position));

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
                        System.out.println("level erou"+holder.mItem.getHeroLevel());
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
            public MatchDetailPlayer mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id_player_detail);
                mContentView = (ImageView) view.findViewById(R.id.playerhero);
            }
            /*
            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
            */
        }
    }
}

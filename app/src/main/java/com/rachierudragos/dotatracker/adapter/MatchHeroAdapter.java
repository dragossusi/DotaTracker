package com.rachierudragos.dotatracker.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Wrapper.hero.HeroDatabase;
import com.rachierudragos.dotatracker.Wrapper.match.MatchPlayer;
import com.rachierudragos.dotatracker.match.detail.MatchDetailFragment;
import com.rachierudragos.dotatracker.match.detail.MatchPlayerDetailActivity;

import java.util.List;

/**
 * Created by Dragos on 02-Nov-17.
 */

public class MatchHeroAdapter extends RecyclerView.Adapter<MatchHeroAdapter.ItemHolder> {

    Context context;
    boolean mTwoPane;
    private List<MatchPlayer> players;

    public MatchHeroAdapter(Context context, List<MatchPlayer> players, boolean mTwoPane) {
        this.players = players;
        this.context = context;
        this.mTwoPane = mTwoPane;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_match_hero, parent, false));
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, final int position) {
        final MatchPlayer player = players.get(position);
        if (player.personaname != null)
            holder.mIdView.setText(player.personaname);
        else
            holder.mIdView.setText("Unknown");
        if (position < 5) {
            holder.itemView.setBackgroundColor(Color.GREEN);
        } else {
            holder.itemView.setBackgroundColor(Color.RED);
        }
        holder.mContentView.setImageResource(context.getResources().getIdentifier(HeroDatabase.getHeroIdName(player.hero_id), "drawable", context.getPackageName()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putInt("player", position);
                    MatchDetailFragment fragment = new MatchDetailFragment();
                    fragment.setArguments(arguments);
                    ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.match_detail_container, fragment)
                            .commit();
                } else {
                    Intent intent = new Intent(context, MatchPlayerDetailActivity.class);
                    intent.putExtra("player", player);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        public final TextView mIdView;
        public final ImageView mContentView;

        public ItemHolder(View view) {
            super(view);
            mIdView = view.findViewById(R.id.id_player_detail);
            mContentView = view.findViewById(R.id.playerhero);
        }
    }
}
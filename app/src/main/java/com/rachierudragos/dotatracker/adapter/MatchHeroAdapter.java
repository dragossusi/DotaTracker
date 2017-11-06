package com.rachierudragos.dotatracker.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Wrapper.database.HeroDatabase;
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
    private int selectPosition = -1;

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
        holder.itemView.setSelected(false);
        if (player.personaname != null)
            holder.textName.setText(player.personaname);
        else
            holder.textName.setText("Unknown");
        if (position < 5) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.green));
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
        }
        holder.imageHero.setImageResource(context.getResources().getIdentifier(HeroDatabase.getHeroIdName(player.hero_id), "drawable", context.getPackageName()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putInt("player", position);
                    MatchDetailFragment fragment = new MatchDetailFragment();
                    fragment.setArguments(arguments);
                    fragment.setEnterTransition(new Slide(Gravity.BOTTOM));
                    fragment.setExitTransition(new Slide(Gravity.RIGHT));
                    ((AppCompatActivity) context).getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.match_detail_container, fragment,"heroDetail")
                            .commit();
                    notifyItemChanged(selectPosition);
                    holder.itemView.setSelected(true);
                    selectPosition=position;
                } else {
                    Intent intent = new Intent(context, MatchPlayerDetailActivity.class);
                    intent.putExtra("player", player);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,holder.imageHero,context.getString(R.string.hero_image_transition));
                    context.startActivity(intent,options.toBundle());
                }
            }
        });
    }

    public void deselect() {
        notifyItemChanged(selectPosition);
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        public final TextView textName;
        public final ImageView imageHero;

        public ItemHolder(View view) {
            super(view);
            textName = view.findViewById(R.id.id_player_detail);
            imageHero = view.findViewById(R.id.playerhero);
        }
    }
}
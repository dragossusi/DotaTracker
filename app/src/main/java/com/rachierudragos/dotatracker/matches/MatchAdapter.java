package com.rachierudragos.dotatracker.matches;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Wrapper.hero.HeroDatabase;
import com.rachierudragos.dotatracker.Wrapper.match.MatchPreview;
import com.rachierudragos.dotatracker.match.MatchDetailActivity;

import java.util.List;

/**
 * Created by Dragos on 10.11.2016.
 */

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ItemHolder> {

    Context context;
    List<MatchPreview> matches;

    public MatchAdapter(Context context, List<MatchPreview> matches) {
        this.context = context;
        this.matches = matches;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemHolder result = new ItemHolder(LayoutInflater.from(context).inflate(R.layout.item_match_preview, parent, false));
        return result;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, final int position) {
        MatchPreview match = matches.get(position);
        holder.imgHero.setImageResource(context.getResources().getIdentifier(HeroDatabase.getHeroIdName(match.hero_id), "drawable", context.getPackageName()));
        holder.textHeroName.setText(HeroDatabase.getHeroName(match.hero_id));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("a dat click pe item");
                Intent intent = new Intent(context, MatchDetailActivity.class);
                intent.putExtra("match_id", matches.get(position).match_id);
                context.startActivity(intent);
            }
        });
        if (match.hasWon()) {
            holder.textWin.setText("Win");
            holder.textWin.setTextColor(Color.GREEN);
        } else {
            holder.textWin.setText("Lost");
            holder.textWin.setTextColor(Color.RED);
        }
        holder.textDuration.setText(match.getDurationText());
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        TextView textDuration;
        TextView textWin;
        TextView textHeroName;
        ImageView imgHero;

        public ItemHolder(View itemView) {
            super(itemView);
            textDuration = (TextView) itemView.findViewById(R.id.text_duration);
            textWin = (TextView) itemView.findViewById(R.id.text_win);
            textHeroName = (TextView) itemView.findViewById(R.id.text_hero_name);
            imgHero = (ImageView) itemView.findViewById(R.id.image_hero);
        }
    }
}

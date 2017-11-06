package com.rachierudragos.dotatracker.heroes;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.vars.Utils;
import com.rachierudragos.dotatracker.Wrapper.database.HeroDatabase;
import com.rachierudragos.dotatracker.Wrapper.hero.HeroDetail;

import java.util.List;

/**
 * Created by Dragos on 13.01.2017.
 */

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ItemHolder> {
    Context context;
    List<HeroDetail> heroDetails;

    public HeroAdapter(Context context, List<HeroDetail> heroDetails) {
        this.context = context;
        this.heroDetails = heroDetails;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemHolder result = new ItemHolder(LayoutInflater.from(context).inflate(R.layout.item_hero, parent, false));
        return result;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, final int position) {
        HeroDetail heroDetail = heroDetails.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("a dat click pe erou");
                HeroDetail heroDetail= heroDetails.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final View dialog = LayoutInflater.from(context).inflate(R.layout.dialog_hero, null);
                float winrate = (heroDetail.win * 100.f) / heroDetail.games;
                TextView wrate = (TextView) dialog.findViewById(R.id.winrate);
                wrate.setText(Utils.DECIMAL_FORMAT.format(winrate)+"%");
                if(winrate>=50.0f)
                    wrate.setTextColor(ContextCompat.getColor(context,android.R.color.holo_green_dark));
                else
                    wrate.setTextColor(ContextCompat.getColor(context,android.R.color.holo_red_dark));
                ((TextView)dialog.findViewById(R.id.wins)).setText(String.valueOf(heroDetail.win));
                ((TextView)dialog.findViewById(R.id.loses)).setText(String.valueOf(heroDetail.games-heroDetail.win));
                ((TextView)dialog.findViewById(R.id.with_games)).setText(String.valueOf(heroDetail.with_games));
                ((TextView)dialog.findViewById(R.id.against_games)).setText(String.valueOf(heroDetail.against_games));
                ((TextView)dialog.findViewById(R.id.with_wins)).setText(String.valueOf(heroDetail.with_win));
                ((TextView)dialog.findViewById(R.id.against_wins)).setText(String.valueOf(heroDetail.against_win));
                //LinearLayout
                builder.setView(dialog)
                        .setTitle(HeroDatabase.getHeroName(heroDetail.hero_id));
                builder.show();
            }
        });
        holder.imgHero.setImageResource(Utils.getPhotoResId(context,heroDetail.hero_id));
        holder.textHeroName.setText(HeroDatabase.getHeroName(heroDetail.hero_id));
        holder.textGames.setText(String.valueOf(heroDetail.games));
    }

    @Override
    public int getItemCount() {
        return heroDetails.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        ImageView imgHero;
        TextView textGames;
        TextView textHeroName;
        public ItemHolder(View itemView) {
            super(itemView);
            imgHero = itemView.findViewById(R.id.image_hero);
            textGames = itemView.findViewById(R.id.text_hero_matches_number);
            textHeroName = itemView.findViewById(R.id.text_hero_name);
        }
    }
}

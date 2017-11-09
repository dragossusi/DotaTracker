package com.rachierudragos.dotatracker.match.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.database.Ability;
import com.rachierudragos.dotatracker.vars.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dragos on 08-Nov-17.
 */

public class HeroAbilitiesAdapter extends RecyclerView.Adapter<HeroAbilitiesAdapter.ItemHolder> {

    Context context;
    List<Ability> items;

    public HeroAbilitiesAdapter(Context context, List<Ability> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(context).inflate(R.layout.item_hero_ability, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Ability ability = items.get(position);
        holder.textLevel.setText(context.getString(R.string.ability_level_up, position + 1));
        Log.d("site",Utils.IMG_ABILITY_HOME + ability.getName() + "_md.png");
        Picasso.with(context)
                .load(Utils.IMG_ABILITY_HOME + ability.getName() + "_md.png")
                .fit()
                .error(R.drawable.ic_talent_tree)
                .into(holder.imageAbility);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    protected class ItemHolder extends RecyclerView.ViewHolder {
        TextView textLevel;
        ImageView imageAbility;

        public ItemHolder(View itemView) {
            super(itemView);
            textLevel = itemView.findViewById(R.id.text_level);
            imageAbility = itemView.findViewById(R.id.image_ability);
        }
    }
}

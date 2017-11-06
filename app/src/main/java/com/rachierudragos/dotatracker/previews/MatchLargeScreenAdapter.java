package com.rachierudragos.dotatracker.previews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Wrapper.database.Lobbies;
import com.rachierudragos.dotatracker.Wrapper.match.MatchPreview;
import com.rachierudragos.dotatracker.vars.Utils;

import java.util.List;

/**
 * Created by Dragos on 03-Nov-17.
 */

public class MatchLargeScreenAdapter extends MatchAdapter {
    public MatchLargeScreenAdapter(Context context, List<MatchPreview> matches) {
        super(context, matches);
        Log.d("asd","large");
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolderLarge(LayoutInflater.from(context).inflate(R.layout.item_match_preview,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ItemHolderLarge holderLarge = (ItemHolderLarge) holder;
        holderLarge.textSkill.setText(Lobbies.getLobby(matches.get(position).lobby_type));
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    class ItemHolderLarge extends MatchAdapter.ItemHolder {
        TextView textLane;
        TextView textSkill;
        TextView textWhen;
        public ItemHolderLarge(View itemView) {
            super(itemView);
            textLane = itemView.findViewById(R.id.text_lane);
            textSkill = itemView.findViewById(R.id.text_skill);
            textWhen = itemView.findViewById(R.id.text_when);
        }
    }
}
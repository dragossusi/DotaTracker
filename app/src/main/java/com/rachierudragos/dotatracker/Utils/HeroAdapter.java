package com.rachierudragos.dotatracker.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Wrapper.hero.HeroDatabase;
import com.rachierudragos.dotatracker.Wrapper.hero.HeroDetail;

import java.util.List;

/**
 * Created by Dragos on 13.01.2017.
 */

public class HeroAdapter extends ArrayAdapter<HeroDetail> {

    public HeroAdapter(Context context, List<HeroDetail> heroDetails) {
        super(context,0,heroDetails);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HeroDetail heroDetail = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_hero, parent, false);
        }

        ((ImageView)convertView.findViewById(R.id.image_hero)).setImageResource(getContext().getResources().getIdentifier(HeroDatabase.getHeroIdName(heroDetail.hero_id), "drawable", getContext().getPackageName()));
        ((TextView)convertView.findViewById(R.id.hero_name)).setText(HeroDatabase.getHeroName(heroDetail.hero_id));
        ((TextView)convertView.findViewById(R.id.hero_matches_number)).setText(String.valueOf(heroDetail.games));
        return convertView;
    }
}

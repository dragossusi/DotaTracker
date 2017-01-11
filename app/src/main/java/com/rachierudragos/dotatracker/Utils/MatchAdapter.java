package com.rachierudragos.dotatracker.Utils;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Wrapper.hero.HeroDatabase;
import com.rachierudragos.dotatracker.Wrapper.match.MatchPreview;

import java.util.List;

/**
 * Created by Dragos on 10.11.2016.
 */

public class MatchAdapter extends ArrayAdapter<MatchPreview> {

    public MatchAdapter(Context context, List<MatchPreview> matches) {
        super(context, 0, matches);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MatchPreview matchDetail = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }
        // Lookup view for data population
        TextView tv1 = (TextView) convertView.findViewById(R.id.txt_item1);
        TextView tv2 = (TextView) convertView.findViewById(R.id.txt_item2);
        TextView tv3 = (TextView) convertView.findViewById(R.id.txt_duration);
        ImageView iv = (ImageView) convertView.findViewById(R.id.img_hero);
        // Populate the data into the template view using the data object
        tv1.setText(HeroDatabase.getHeroName(matchDetail.hero_id));
        int mins = matchDetail.duration/60;
        int secs = matchDetail.duration%60;
        int id=-1;
        boolean team=true;
        iv.setImageResource(getContext().getResources().getIdentifier(HeroDatabase.getHeroIdName(matchDetail.hero_id), "drawable", getContext().getPackageName()));
        String text = (secs < 10 ? "0" : "") + secs;
        tv3.setText(mins+":"+text);
        ///^^^^^^^^^^^^^^^^^^ schimbat neaparat ^^^^^^^^^^^^^^^^^
        if(matchDetail.radiant_win==(matchDetail.player_slot<128)) {
            tv2.setText("Win");
            tv2.setTextColor(Color.GREEN);
        }
        else {
            tv2.setText("Lost");
            tv2.setTextColor(Color.RED);
        }
        // Return the completed view to render on screen
        return convertView;
    }
}

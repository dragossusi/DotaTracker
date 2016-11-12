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

import com.rachierudragos.dotatracker.MainActivity;
import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Wrapper.HeroDetail;
import com.rachierudragos.dotatracker.Wrapper.domain.matchdetail.MatchDetail;
import com.rachierudragos.dotatracker.Wrapper.domain.matchdetail.MatchDetailPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos on 10.11.2016.
 */

public class MatchAdapter extends ArrayAdapter<MatchDetail> {

    public static final boolean RADIANT=true,DIRE=false;

    public MatchAdapter(Context context, ArrayList<MatchDetail> matches) {
        super(context, 0, matches);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MatchDetail matchDetail = getItem(position);
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
        tv1.setText(String.valueOf(matchDetail.getMatchOverview().getMatchId()));
        int mins = matchDetail.getDurationOfMatch()/60;
        int secs = matchDetail.getDurationOfMatch()%60;
        int id=-1;
        boolean team=true;
        List<MatchDetailPlayer> players = matchDetail.getPlayers();
        //for(MatchDetailPlayer i : players )
        for(int i=0;i<players.size();++i)
            if (players.get(i).getAccountId() == MainActivity.ID) {
                id = players.get(i).getHeroId();
                if(i<5)team=RADIANT;
                else team=DIRE;
            }
        iv.setImageResource(getContext().getResources().getIdentifier(HeroDetail.getHeroName(id), "drawable", getContext().getPackageName()));
        String text = (secs < 10 ? "0" : "") + secs;
        tv3.setText(mins+":"+text);
        ///^^^^^^^^^^^^^^^^^^ schimbat neaparat ^^^^^^^^^^^^^^^^^
        if(matchDetail.didRadianWin()==team) {
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

package com.rachierudragos.dotatracker.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Wrapper.domain.matchdetail.MatchDetail;

import java.util.ArrayList;

/**
 * Created by Dragos on 10.11.2016.
 */

public class MatchAdapter extends ArrayAdapter<MatchDetail> {

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
        // Populate the data into the template view using the data object
        tv1.setText(String.valueOf(matchDetail.getMatchOverview().getMatchId()));
        int mins = matchDetail.getDurationOfMatch()/100;
        int secs = matchDetail.getDurationOfMatch()%100;
        String text = (secs < 10 ? "0" : "") + secs;
        tv3.setText(mins+":"+text);
        ///^^^^^^^^^^^^^^^^^^ schimbat neaparat ^^^^^^^^^^^^^^^^^
        if(matchDetail.didRadianWin())
            tv2.setText("Radiant Victory");
        else
            tv2.setText("Dire Victory");
        // Return the completed view to render on screen
        return convertView;
    }
}

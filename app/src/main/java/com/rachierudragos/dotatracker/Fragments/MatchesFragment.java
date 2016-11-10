package com.rachierudragos.dotatracker.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Wrapper.Dota2Stats;
import com.rachierudragos.dotatracker.Wrapper.domain.GameMode;
import com.rachierudragos.dotatracker.Wrapper.domain.MatchOverview;
import com.rachierudragos.dotatracker.Wrapper.domain.filter.MatchHistoryFilter;
import com.rachierudragos.dotatracker.Wrapper.domain.matchhistory.MatchHistory;
import com.rachierudragos.dotatracker.Wrapper.exceptions.Dota2StatsAccessException;
import com.rachierudragos.dotatracker.Wrapper.impl.Dota2StatsImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos on 09.11.2016.
 */

public class MatchesFragment extends Fragment {

    Dota2Stats stats;
    ListView listView;
    ArrayList<MatchOverview> meciuri;
    Thread t;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_matches, container, false);
        listView = (ListView)rootView.findViewById(R.id.list_lastmatches);
        stats = new Dota2StatsImpl("E1CF20517738CE047F04CC4823904781");
        meciuri = new ArrayList<>();
        t = new Thread(new Runnable() {
            public void run() {
                try {
                    MatchHistory history = stats.getMatchHistory(new MatchHistoryFilter().forAccountId(110276393).forGameMode(GameMode.All_Pick));
                    List<MatchOverview> overviews = history.getMatchOverviews();

                    for (MatchOverview match : overviews) {
                        System.out.println(match.getMatchId());
                        meciuri.add(match);
                    }
                } catch (Dota2StatsAccessException e1) {
                    // Do something if an error occurs
                }
            }
        });
        t.start();
        return rootView;
    }

    @Override
    public void onResume() {
        try {
            t.join();

            //listView.setAdapter(adapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onResume();
    }
}

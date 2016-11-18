package com.rachierudragos.dotatracker.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rachierudragos.dotatracker.MainActivity;
import com.rachierudragos.dotatracker.MatchListActivity;
import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Utils.MatchAdapter;
import com.rachierudragos.dotatracker.Wrapper.Dota2Stats;
import com.rachierudragos.dotatracker.Wrapper.domain.MatchOverview;
import com.rachierudragos.dotatracker.Wrapper.domain.filter.MatchHistoryFilter;
import com.rachierudragos.dotatracker.Wrapper.domain.matchdetail.MatchDetail;
import com.rachierudragos.dotatracker.Wrapper.domain.matchhistory.MatchHistory;
import com.rachierudragos.dotatracker.Wrapper.exceptions.Dota2StatsAccessException;
import com.rachierudragos.dotatracker.Wrapper.impl.Dota2StatsImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos on 09.11.2016.
 */

public class MatchesFragment extends Fragment {

    private Dota2Stats stats;
    private ListView listView;
    private Thread t;
    private ArrayList<MatchDetail> meciuri;
    private MatchAdapter adapter;

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
                    MatchHistory history = stats.getMatchHistory(new MatchHistoryFilter().forAccountId(MainActivity.ID).forMaximumNumberOfResults(10));
                    List<MatchOverview> overviews = history.getMatchOverviews();
                    for (MatchOverview match : overviews) {
                        meciuri.add(stats.getMatchDetails(match.getMatchId()));
                    }
                    adapter = new MatchAdapter(getActivity(),meciuri);
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
            listView.setAdapter(adapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
                System.out.println("a dat click pe item");
                Intent intent = new Intent(getActivity(), MatchListActivity.class);
                intent.putExtra("id", meciuri.get(position).getMatchOverview().getMatchId());
                intent.putExtra("match",meciuri.get(position));
                try {
                    getActivity().startActivity(intent);
                }
                catch (Exception e){
                    System.out.println("eroaree\n"+e);
                }

            }
        });
        super.onResume();
    }
}

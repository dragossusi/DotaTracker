package com.rachierudragos.dotatracker.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

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
    private ArrayList<MatchDetail> meciuri;
    private MatchAdapter adapter;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_matches, container, false);
        listView = (ListView) rootView.findViewById(R.id.list_lastmatches);
        stats = new Dota2StatsImpl("E1CF20517738CE047F04CC4823904781");
        meciuri = new ArrayList<>();
        if(MainActivity.getID()!=-1)
            new GetMatches().execute(null,null,null);
        else
            Toast.makeText(getActivity(),"id incorect",Toast.LENGTH_LONG);
        return rootView;
    }
    private class GetMatches extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... urls) {
            try {
                MatchHistory history = stats.getMatchHistory(new MatchHistoryFilter().forAccountId(MainActivity.getID()).forMaximumNumberOfResults(10));
                List<MatchOverview> overviews = history.getMatchOverviews();
                for (MatchOverview match : overviews) {
                    meciuri.add(stats.getMatchDetails(match.getMatchId()));
                }
                adapter = new MatchAdapter(getActivity(), meciuri);
            } catch (Dota2StatsAccessException e1) {
                // Do something if an error occurs
            }
            return null;
        }

        protected void onProgressUpdate(Void... progress) {

        }

        protected void onPostExecute(Void result) {
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
                    System.out.println("a dat click pe item");
                    Intent intent = new Intent(getActivity(), MatchListActivity.class);
                    intent.putExtra("match", meciuri.get(position));
                    getActivity().startActivity(intent);
                }
            });
            listView.setVisibility(View.VISIBLE);
            ProgressBar pb = (ProgressBar) rootView.findViewById(R.id.progressBar);
            pb.setVisibility(View.GONE);
        }
    }

}

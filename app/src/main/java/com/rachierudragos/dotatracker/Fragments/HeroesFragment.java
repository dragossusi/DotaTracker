package com.rachierudragos.dotatracker.Fragments;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rachierudragos.dotatracker.MainActivity;
import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Utils.HeroAdapter;
import com.rachierudragos.dotatracker.Wrapper.filter.MatchFilter;
import com.rachierudragos.dotatracker.Wrapper.hero.HeroDatabase;
import com.rachierudragos.dotatracker.Wrapper.hero.HeroDetail;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Dragos on 13.01.2017.
 */

public class HeroesFragment extends Fragment {
    private ListView listView;
    private List<HeroDetail> heroDetails;
    private HeroAdapter adapter;
    private View rootView;
    private long ID;
    private DecimalFormat df;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        df = new DecimalFormat("0.00");
        rootView = inflater.inflate(R.layout.fragment_matches, container, false);
        listView = (ListView) rootView.findViewById(R.id.list_lastmatches);
        ID = MainActivity.getID();
        if(ID!=-1)
            new GetHeroes().execute(null,null,null);
        else
            Toast.makeText(getActivity(),"id incorect",Toast.LENGTH_LONG);
        return rootView;
    }

    private class GetHeroes extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... urls) {
            try {
                MatchFilter filter = new MatchFilter();
                filter.setLimitTo(MainActivity.getMatches_number());
                heroDetails = MainActivity.api.getHeroes(ID);
                adapter = new HeroAdapter(getActivity(), heroDetails);
            } catch (Exception e) {
                // Do something if an error occurs
                e.printStackTrace();
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
                    System.out.println("a dat click pe erou");
                    HeroDetail heroDetail= heroDetails.get(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    final View dialog = getActivity().getLayoutInflater().inflate(R.layout.dialog_hero, null);
                    float winrate = (heroDetail.win * 100.f) / heroDetail.games;
                    TextView wrate = (TextView) dialog.findViewById(R.id.winrate);
                    wrate.setText(df.format(winrate)+"%");
                    if(winrate>=50.0f)
                        wrate.setTextColor(ContextCompat.getColor(getActivity(),android.R.color.holo_green_dark));
                    else
                        wrate.setTextColor(ContextCompat.getColor(getActivity(),android.R.color.holo_red_dark));
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
            listView.setVisibility(View.VISIBLE);
            ProgressBar pb = (ProgressBar) rootView.findViewById(R.id.progressBar);
            pb.setVisibility(View.GONE);
        }
    }
}

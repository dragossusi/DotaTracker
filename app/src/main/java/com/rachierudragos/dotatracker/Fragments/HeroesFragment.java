package com.rachierudragos.dotatracker.Fragments;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rachierudragos.dotatracker.MainActivity;
import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Utils.HeroAdapter;
import com.rachierudragos.dotatracker.Wrapper.filter.MatchFilter;
import com.rachierudragos.dotatracker.Wrapper.hero.HeroDetail;

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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
            /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
                    System.out.println("a dat click pe item");
                    Intent intent = new Intent(getActivity(), MatchListActivity.class);
                    intent.putExtra("match_id", heroDetails.get(position).hero_id);
                    getActivity().startActivity(intent);
                }
            });*/
            listView.setVisibility(View.VISIBLE);
            ProgressBar pb = (ProgressBar) rootView.findViewById(R.id.progressBar);
            pb.setVisibility(View.GONE);
        }
    }
}
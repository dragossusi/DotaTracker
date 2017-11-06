package com.rachierudragos.dotatracker.heroes;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.rachierudragos.dotatracker.vars.App;
import com.rachierudragos.dotatracker.MainActivity;
import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Wrapper.ODotaAPI2;
import com.rachierudragos.dotatracker.Wrapper.filter.MatchesFilter;
import com.rachierudragos.dotatracker.Wrapper.hero.HeroDetail;
import com.rachierudragos.dotatracker.vars.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dragos on 13.01.2017.
 */

public class HeroesFragment extends Fragment {
    private RecyclerView recyclerView;
    private View rootView;
    private int id;
    ODotaAPI2 api;
    ProgressBar pb;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_heroes, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_heroes);
        pb = rootView.findViewById(R.id.progressBar);
        id = Utils.getId();
        api = ((App)getActivity().getApplication()).getApi();
        Call<List<HeroDetail>> heroDetails = api.getHeroDetails(id,new MatchesFilter().getFilter());
        heroDetails.enqueue(new Callback<List<HeroDetail>>() {
            @Override
            public void onResponse(Call<List<HeroDetail>> call, Response<List<HeroDetail>> response) {
                recyclerView.setAdapter(new HeroAdapter(getActivity(),response.body()));//TODO
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                pb.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<HeroDetail>> call, Throwable t) {

            }
        });
        return rootView;
    }
}

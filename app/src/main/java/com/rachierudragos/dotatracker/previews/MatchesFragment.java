package com.rachierudragos.dotatracker.previews;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rachierudragos.dotatracker.vars.App;
import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Wrapper.ODotaAPI2;
import com.rachierudragos.dotatracker.Wrapper.filter.MatchesFilter;
import com.rachierudragos.dotatracker.Wrapper.match.MatchPreview;
import com.rachierudragos.dotatracker.vars.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dragos on 09.11.2016.
 */

public class MatchesFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<MatchPreview> meciuri;
    private View rootView;
    private int id;
    ODotaAPI2 api;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_matches, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_items);
        return rootView;
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        id = Utils.getId();
        api = ((App) context.getApplicationContext()).getApi();
        Call<List<MatchPreview>> matchesPreviews = api.getMatchesPreviews(id, new MatchesFilter().setLimit(Utils.getMatchesNumber()).getFilter());
        matchesPreviews.enqueue(new Callback<List<MatchPreview>>() {
            @Override
            public void onResponse(Call<List<MatchPreview>> call, Response<List<MatchPreview>> response) {
                Log.d("url", call.request().url().toString());
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
                if(dpWidth<900)
                    recyclerView.setAdapter(new MatchAdapter(context, response.body()));
                else
                    recyclerView.setAdapter(new MatchLargeScreenAdapter(context,response.body()));
                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                ProgressBar pb = rootView.findViewById(R.id.progressBar);
                pb.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<MatchPreview>> call, Throwable t) {
                Toast.makeText(context, R.string.error_id, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

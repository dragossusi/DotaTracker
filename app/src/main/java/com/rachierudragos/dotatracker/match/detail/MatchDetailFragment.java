package com.rachierudragos.dotatracker.match.detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.Gson;
import com.rachierudragos.dotatracker.Wrapper.abilities.AbilityDetail;
import com.rachierudragos.dotatracker.Wrapper.match.MatchPlayer;
import com.rachierudragos.dotatracker.database.Ability;
import com.rachierudragos.dotatracker.database.AbilityDao;
import com.rachierudragos.dotatracker.match.MatchDetailActivity;
import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Wrapper.ItemDetail;
import com.rachierudragos.dotatracker.Wrapper.database.HeroDatabase;
import com.rachierudragos.dotatracker.vars.App;
import com.rachierudragos.dotatracker.vars.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a single Match detail screen.
 * This fragment is either contained in a {@link MatchDetailActivity}
 * in two-pane mode (on tablets) or a {@link MatchPlayerDetailActivity}
 * on handsets.
 */
public class MatchDetailFragment extends Fragment {

    private MatchPlayer mItem;
    TextView textKDA;
    TextView textLHDN;
    TextView textLvl;
    TextView textGPM;
    TextView textXPM;
    TextView textNet;
    LineChart chart;
    List<ImageView> ivs;
    LineData lineData;
    private LineDataSet dataSetGold;
    private LineDataSet dataSetXp;
    private List<Ability> abilities;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player_detail, container, false);

        textKDA = rootView.findViewById(R.id.kda_detail);
        textLHDN = rootView.findViewById(R.id.lhd_detail);
        textLvl = rootView.findViewById(R.id.lvl_detail);
        textGPM = rootView.findViewById(R.id.gpm_detail);
        textXPM = rootView.findViewById(R.id.xpm_detail);
        textNet = rootView.findViewById(R.id.net_detail);
        chart = rootView.findViewById(R.id.chart_player_detail);
        chart.getXAxis().setGranularity(1);
        chart.getXAxis().setTextColor(Color.WHITE);
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setTextColor(Color.WHITE);
        chart.setScaleYEnabled(false);
        chart.getDescription().setEnabled(false);

        recyclerView = rootView.findViewById(R.id.recycler_abilities);

        ivs = new ArrayList<>();
        ivs.add((ImageView) rootView.findViewById(R.id.item_0));
        ivs.add((ImageView) rootView.findViewById(R.id.item_1));
        ivs.add((ImageView) rootView.findViewById(R.id.item_2));
        ivs.add((ImageView) rootView.findViewById(R.id.item_3));
        ivs.add((ImageView) rootView.findViewById(R.id.item_4));
        ivs.add((ImageView) rootView.findViewById(R.id.item_5));
        ivs.add((ImageView) rootView.findViewById(R.id.back_0));
        ivs.add((ImageView) rootView.findViewById(R.id.back_1));
        ivs.add((ImageView) rootView.findViewById(R.id.back_2));
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            if (getArguments().containsKey("player"))
                mItem = ((MatchDetailActivity) getActivity()).getMatchDetail().players.get(getArguments().getInt("player"));
            else
                mItem = ((MatchPlayerDetailActivity) getActivity()).getPlayer();
        } else {
            mItem = savedInstanceState.getParcelable("player");
        }
        //abilities
        abilities = new ArrayList<>();
        AbilityDao abilityDao = ((App)getActivity().getApplication()).getDaoSession().getAbilityDao();
        for(int i:mItem.ability_upgrades_arr) {
            abilities.add(abilityDao.loadByRowId(i));
        }


        //gold graph
        List<Entry> entries = new ArrayList<>();
        for(int i=0;i<mItem.gold_t.size();++i) {
            entries.add(new Entry(i,mItem.gold_t.get(i)));
        }
        dataSetGold = new LineDataSet(entries,"Gold");
        dataSetGold.setDrawValues(false);
        dataSetGold.setDrawCircles(false);
        dataSetGold.setColor(ContextCompat.getColor(getActivity(),R.color.yellow));

        //xp graph
        entries = new ArrayList<>();
        for(int i=0;i<mItem.xp_t.size();++i) {
            entries.add(new Entry(i,mItem.xp_t.get(i)));
        }
        dataSetXp = new LineDataSet(entries,"XP");
        dataSetXp.setDrawValues(false);
        dataSetXp.setDrawCircles(false);
        dataSetXp.setColor(ContextCompat.getColor(getActivity(),R.color.blue));

        lineData = new LineData(dataSetGold,dataSetXp);
        //title
        CollapsingToolbarLayout appBarLayout = getActivity().findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(HeroDatabase.getHeroName(mItem.hero_id));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        textKDA.setText(getActivity().getString(R.string.format_KDA, mItem.kills, mItem.deaths, mItem.assists));
        textLvl.setText(String.valueOf(mItem.level));
        textGPM.setText(String.valueOf(mItem.gold_per_min));
        textXPM.setText(String.valueOf(mItem.xp_per_min));
        textLHDN.setText(getActivity().getString(R.string.format_LHDN, mItem.last_hits, mItem.denies));
        textNet.setText(String.valueOf(mItem.total_gold));
        List<Integer> iteme = mItem.getItems();
        chart.setData(lineData);
        chart.getViewPortHandler().setMaximumScaleX(10f);
        int pos = 0;
        for (ImageView i : ivs) {
            if (ItemDetail.getName(iteme.get(pos)).startsWith("recipe"))
                i.setImageResource(getResources().getIdentifier("recipe", "drawable", getActivity().getPackageName()));
            else
                i.setImageResource(getResources().getIdentifier(ItemDetail.getName(iteme.get(pos)), "drawable", getActivity().getPackageName()));
            ++pos;
        }
        recyclerView.setAdapter(new HeroAbilitiesAdapter(getContext(),abilities));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("player", mItem);
    }

}

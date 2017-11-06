package com.rachierudragos.dotatracker.match.detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rachierudragos.dotatracker.Wrapper.match.MatchPlayer;
import com.rachierudragos.dotatracker.match.MatchDetailActivity;
import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Wrapper.ItemDetail;
import com.rachierudragos.dotatracker.Wrapper.database.HeroDatabase;

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
    List<ImageView> ivs;

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
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(HeroDatabase.getHeroName(mItem.hero_id));
            }
        } else {
            mItem = savedInstanceState.getParcelable("player");
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
        int pos = 0;
        for (ImageView i : ivs) {
            if (ItemDetail.getName(iteme.get(pos)).startsWith("recipe"))
                i.setImageResource(getResources().getIdentifier("recipe", "drawable", getActivity().getPackageName()));
            else
                i.setImageResource(getResources().getIdentifier(ItemDetail.getName(iteme.get(pos)), "drawable", getActivity().getPackageName()));
            ++pos;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("player", mItem);
    }

}

package com.rachierudragos.dotatracker;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rachierudragos.dotatracker.Wrapper.HeroDetail;
import com.rachierudragos.dotatracker.Wrapper.ItemDetail;
import com.rachierudragos.dotatracker.Wrapper.domain.matchdetail.MatchDetailPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a single Match detail screen.
 * This fragment is either contained in a {@link MatchListActivity}
 * in two-pane mode (on tablets) or a {@link MatchDetailActivity}
 * on handsets.
 */
public class MatchDetailFragment extends Fragment {

    /**
     * The dummy content this fragment is presenting.
     */
    private MatchDetailPlayer mItem;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MatchDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey("player")) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = (MatchDetailPlayer) getArguments().getSerializable("player");

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(HeroDetail.getHeroName(mItem.getHeroId()));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.player_detail, container, false);
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.kda_detail)).setText(mItem.getKills()+"/"+mItem.getDeaths()+"/"+mItem.getAssists());
            ((TextView) rootView.findViewById(R.id.lvl_detail)).setText(""+mItem.getHeroLevel());
            ((TextView) rootView.findViewById(R.id.gpm_detail)).setText(String.valueOf(mItem.getGoldPerMinute()));
            ((TextView) rootView.findViewById(R.id.xpm_detail)).setText(String.valueOf(mItem.getXPPerMinute()));
            ((TextView) rootView.findViewById(R.id.lhd_detail)).setText(mItem.getLastHits()+"/"+mItem.getDenies());
            ((TextView) rootView.findViewById(R.id.net_detail)).setText(String.valueOf(mItem.getGoldSpent()+mItem.getGold()));
            List<Integer> iteme = mItem.getItems();
            int displayWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth();
            int displayHeight = displayWidth * 8 / 11;
            ArrayList<ImageView> ivs = new ArrayList<>();
            ivs.add((ImageView) rootView.findViewById(R.id.item_0));
            ivs.add((ImageView) rootView.findViewById(R.id.item_1));
            ivs.add((ImageView) rootView.findViewById(R.id.item_2));
            ivs.add((ImageView) rootView.findViewById(R.id.item_3));
            ivs.add((ImageView) rootView.findViewById(R.id.item_4));
            ivs.add((ImageView) rootView.findViewById(R.id.item_5));
            ivs.add((ImageView) rootView.findViewById(R.id.back_0));
            ivs.add((ImageView) rootView.findViewById(R.id.back_1));
            ivs.add((ImageView) rootView.findViewById(R.id.back_2));
            int pos = 0;
            for(ImageView i:ivs) {
                i.setImageResource(getResources().getIdentifier(ItemDetail.getName(iteme.get(pos)), "drawable", getActivity().getPackageName()));
                i.getLayoutParams().width = displayWidth / 3;
                i.getLayoutParams().height = displayHeight / 3;
                i.setScaleType(ImageView.ScaleType.FIT_XY);
                ++pos;
            }
        }
        return rootView;
    }
}

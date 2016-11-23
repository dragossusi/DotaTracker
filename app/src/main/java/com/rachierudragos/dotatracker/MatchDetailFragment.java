package com.rachierudragos.dotatracker;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rachierudragos.dotatracker.Wrapper.HeroDetail;
import com.rachierudragos.dotatracker.Wrapper.domain.matchdetail.MatchDetailPlayer;

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
            ((TextView) rootView.findViewById(R.id.kda_detail)).setText(mItem.getKills()+"/"+mItem.getAssists()+"/"+mItem.getDeaths());
            try {
                ((TextView) rootView.findViewById(R.id.lvl_detail)).setText(""+mItem.getHeroLevel());
                ((TextView) rootView.findViewById(R.id.gpm_detail)).setText(String.valueOf(mItem.getGoldPerMinute()));
                ((TextView) rootView.findViewById(R.id.xpm_detail)).setText(String.valueOf(mItem.getXPPerMinute()));
                ((TextView) rootView.findViewById(R.id.net_detail)).setText(String.valueOf(mItem.getGoldSpent()));
                ((TextView) rootView.findViewById(R.id.lhd_detail)).setText(mItem.getLastHits()+"/"+mItem.getDenies());
            }catch (Exception e) {
                System.out.println("eroare aici boss"+e);
            }
        }
        return rootView;
    }
}

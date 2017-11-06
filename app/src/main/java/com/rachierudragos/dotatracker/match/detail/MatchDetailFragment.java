package com.rachierudragos.dotatracker.match.detail;

import android.app.Activity;
import android.os.Bundle;
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
import com.rachierudragos.dotatracker.vars.Utils;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey("player") && mItem==null) {
            if(getActivity() instanceof MatchDetailActivity)
                mItem = ((MatchDetailActivity) getActivity()).getMatchDetail().players.get(getArguments().getInt("player"));
            else
                mItem = ((MatchPlayerDetailActivity)getActivity()).getPlayer();
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(HeroDatabase.getHeroName(mItem.hero_id));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.player_detail, container, false);
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.kda_detail)).setText(mItem.kills + "/" + mItem.deaths + "/" + mItem.assists);
            ((TextView) rootView.findViewById(R.id.lvl_detail)).setText("" + mItem.level);
            ((TextView) rootView.findViewById(R.id.gpm_detail)).setText(String.valueOf(mItem.gold_per_min));
            ((TextView) rootView.findViewById(R.id.xpm_detail)).setText(String.valueOf(mItem.xp_per_min));
            ((TextView) rootView.findViewById(R.id.lhd_detail)).setText(mItem.last_hits + "/" + mItem.denies);
            ((TextView) rootView.findViewById(R.id.net_detail)).setText(String.valueOf(mItem.total_gold));
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
            for (ImageView i : ivs) {
                if (ItemDetail.getName(iteme.get(pos)).startsWith("recipe"))
                    i.setImageResource(getResources().getIdentifier("recipe", "drawable", getActivity().getPackageName()));
                else
                    i.setImageResource(getResources().getIdentifier(ItemDetail.getName(iteme.get(pos)), "drawable", getActivity().getPackageName()));
                i.getLayoutParams().width = displayWidth / 3;
                i.getLayoutParams().height = displayHeight / 3;
                i.setScaleType(ImageView.ScaleType.FIT_XY);
                ++pos;
            }

            //ImageView imageView = getActivity().findViewById(R.id.image_hero);
            //imageView.setImageResource(getActivity().getResources().getIdentifier(HeroDatabase.getHeroIdName(mItem.hero_id), "drawable", getActivity().getPackageName()));
        }
        return rootView;
    }
}
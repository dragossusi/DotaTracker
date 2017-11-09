package com.rachierudragos.dotatracker.Wrapper;

import com.rachierudragos.dotatracker.Wrapper.account.AccountDetail;
import com.rachierudragos.dotatracker.Wrapper.account.PlayerWinLose;
import com.rachierudragos.dotatracker.Wrapper.filter.MatchesFilter;
import com.rachierudragos.dotatracker.Wrapper.hero.HeroDetail;
import com.rachierudragos.dotatracker.Wrapper.match.MatchDetail;
import com.rachierudragos.dotatracker.Wrapper.match.MatchPreview;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Dragos on 02-Nov-17.
 */

public interface ODotaAPI2 {
    @GET("players/{accid}")
    Call<AccountDetail> getPlayer(@Path("accid") int id);

    @GET("players/{accid}/wl")
    Call<PlayerWinLose> getPlayerWinLose(@Path("accid")int id);

    @GET("players/{accid}/matches")
    Call<List<MatchPreview>> getMatchesPreviews(@Path("accid")int id, @QueryMap Map<String,String> query);
    @GET("players/{accid}/heroes")
    Call<List<HeroDetail>> getHeroDetails(@Path("accid")int id, @QueryMap Map<String,String> query);

    @GET("matches/{matchid}")
    Call<MatchDetail> getMatch(@Path("matchid") long match_id);

}

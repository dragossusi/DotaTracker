package com.rachierudragos.dotatracker.Wrapper.match;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MatchDetail implements Parcelable{
    public long match_id;
    public int barracks_status_dire;
    public int barracks_status_radiant;
    public List<Chat> chat;
    public int cluster;
    public int dire_score;
    public int duration;
    public int engine;
    public int first_blood_time;
    public int game_mode;
    public int human_players;
    public int leagueid;
    public int lobby_type;
    public int negative_votes;
    public int positive_votes;
    public List<Integer> radiant_gold_adv;
    public int radiant_score;
    public boolean radiant_win;
    public List<Integer> radiant_xp_adv;
    public long start_time;
    public int tower_status_dire;
    public int tower_status_radiant;
    public int version;
    public long replay_salt;
    public int series_id;
    public int series_type;
    public List<MatchPlayer> players;
    public int patch;
    public int region;
    public Map<String, Integer> all_word_counts;
    public int comeback;

    public class PurchaseLog implements Serializable {
        public String key;
        public int time;
    }

    protected MatchDetail(Parcel in) {
        match_id = in.readLong();
        barracks_status_dire = in.readInt();
        barracks_status_radiant = in.readInt();
        chat = in.createTypedArrayList(Chat.CREATOR);
        cluster = in.readInt();
        dire_score = in.readInt();
        duration = in.readInt();
        engine = in.readInt();
        first_blood_time = in.readInt();
        game_mode = in.readInt();
        human_players = in.readInt();
        leagueid = in.readInt();
        lobby_type = in.readInt();
        negative_votes = in.readInt();
        positive_votes = in.readInt();
        radiant_score = in.readInt();
        radiant_win = in.readByte() != 0;
        start_time = in.readLong();
        tower_status_dire = in.readInt();
        tower_status_radiant = in.readInt();
        version = in.readInt();
        replay_salt = in.readLong();
        series_id = in.readInt();
        series_type = in.readInt();
        players = in.createTypedArrayList(MatchPlayer.CREATOR);
        patch = in.readInt();
        region = in.readInt();
        comeback = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(match_id);
        dest.writeInt(barracks_status_dire);
        dest.writeInt(barracks_status_radiant);
        dest.writeTypedList(chat);
        dest.writeInt(cluster);
        dest.writeInt(dire_score);
        dest.writeInt(duration);
        dest.writeInt(engine);
        dest.writeInt(first_blood_time);
        dest.writeInt(game_mode);
        dest.writeInt(human_players);
        dest.writeInt(leagueid);
        dest.writeInt(lobby_type);
        dest.writeInt(negative_votes);
        dest.writeInt(positive_votes);
        dest.writeInt(radiant_score);
        dest.writeByte((byte) (radiant_win ? 1 : 0));
        dest.writeLong(start_time);
        dest.writeInt(tower_status_dire);
        dest.writeInt(tower_status_radiant);
        dest.writeInt(version);
        dest.writeLong(replay_salt);
        dest.writeInt(series_id);
        dest.writeInt(series_type);
        dest.writeTypedList(players);
        dest.writeInt(patch);
        dest.writeInt(region);
        dest.writeInt(comeback);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MatchDetail> CREATOR = new Creator<MatchDetail>() {
        @Override
        public MatchDetail createFromParcel(Parcel in) {
            return new MatchDetail(in);
        }

        @Override
        public MatchDetail[] newArray(int size) {
            return new MatchDetail[size];
        }
    };
}

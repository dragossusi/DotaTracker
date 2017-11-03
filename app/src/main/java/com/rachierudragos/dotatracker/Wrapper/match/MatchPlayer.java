package com.rachierudragos.dotatracker.Wrapper.match;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Dragos on 03-Nov-17.
 */

public class MatchPlayer implements Parcelable {
    public long match_id;
    public int player_slot;
    //ability_upgrades_arr	[19]
    //ability_uses	Object
    public long account_id;
    public List<MatchUnit> additional_units;
    public int assists;
    public int backpack_0;
    public int backpack_1;
    public int backpack_2;
    public int camps_stacked;
    public int creeps_stacked;
    public int deaths;
    public int denies;
    public int gold;
    public int gold_per_min;
    public int gold_spent;
    public int hero_damage;
    public int hero_healing;
    public int hero_id;

    public int item_0;
    public int item_1;
    public int item_2;
    public int item_3;
    public int item_4;
    public int item_5;

    protected MatchPlayer(Parcel in) {
        match_id = in.readLong();
        player_slot = in.readInt();
        account_id = in.readLong();
        additional_units = in.createTypedArrayList(MatchUnit.CREATOR);
        assists = in.readInt();
        backpack_0 = in.readInt();
        backpack_1 = in.readInt();
        backpack_2 = in.readInt();
        camps_stacked = in.readInt();
        creeps_stacked = in.readInt();
        deaths = in.readInt();
        denies = in.readInt();
        gold = in.readInt();
        gold_per_min = in.readInt();
        gold_spent = in.readInt();
        hero_damage = in.readInt();
        hero_healing = in.readInt();
        hero_id = in.readInt();
        item_0 = in.readInt();
        item_1 = in.readInt();
        item_2 = in.readInt();
        item_3 = in.readInt();
        item_4 = in.readInt();
        item_5 = in.readInt();
        kills = in.readInt();
        last_hits = in.readInt();
        leaver_status = in.readInt();
        level = in.readInt();
        obs_placed = in.readInt();
        party_id = in.readInt();
        rune_pickups = in.readInt();
        sen_placed = in.readInt();
        stuns = in.readFloat();
        tower_damage = in.readInt();
        xp_per_min = in.readInt();
        personaname = in.readString();
        radiant_win = in.readByte() != 0;
        start_time = in.readLong();
        duration = in.readInt();
        cluster = in.readInt();
        lobby_type = in.readInt();
        game_mode = in.readInt();
        patch = in.readInt();
        region = in.readInt();
        isRadiant = in.readByte() != 0;
        win = in.readInt();
        total_gold = in.readInt();
        total_xp = in.readInt();
        kills_per_min = in.readFloat();
        kda = in.readFloat();
        abandons = in.readInt();
        neutral_kills = in.readInt();
        tower_kills = in.readInt();
        courier_kills = in.readInt();
        hero_kills = in.readInt();
        roshan_kills = in.readInt();
        buyback_count = in.readInt();
        lane_efficiency_pct = in.readInt();
        lane = in.readInt();
        lane_role = in.readInt();
    }

    public static final Creator<MatchPlayer> CREATOR = new Creator<MatchPlayer>() {
        @Override
        public MatchPlayer createFromParcel(Parcel in) {
            return new MatchPlayer(in);
        }

        @Override
        public MatchPlayer[] newArray(int size) {
            return new MatchPlayer[size];
        }
    };

    public List<Integer> getItems() {
        List<Integer> items = new ArrayList<>();
        items.add(item_0);
        items.add(item_1);
        items.add(item_2);
        items.add(item_3);
        items.add(item_4);
        items.add(item_5);
        items.add(backpack_0);
        items.add(backpack_1);
        items.add(backpack_2);
        return items;
    }

    public Map<String, Integer> item_uses;
    public Map<Integer, Integer> kill_streaks;
    public Map<String, Integer> killed_by;
    public int kills;
    //kills_log	[17]
    public int last_hits;
    public int leaver_status;
    public int level;
    //max_hero_hit	Object
    public Map<Integer, Integer> multi_kills;
    public int obs_placed;
    public int party_id;
    //permanent_buffs	[]
    public List<MatchDetail.PurchaseLog> purchase_log;
    public int rune_pickups;
    public int sen_placed;
    public float stuns;
    public int tower_damage;
    public int xp_per_min;
    public String personaname;
    public Date last_login;
    public boolean radiant_win;
    public long start_time;
    public int duration;
    public int cluster;
    public int lobby_type;
    public int game_mode;
    public int patch;
    public int region;
    public boolean isRadiant;
    public int win;
    public int total_gold;
    public int total_xp;
    public float kills_per_min;
    public float kda;
    public int abandons;
    public int neutral_kills;
    public int tower_kills;
    public int courier_kills;
    public int hero_kills;
    public int roshan_kills;
    public int buyback_count;
    public int lane_efficiency_pct;
    public int lane;
    public int lane_role;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(match_id);
        parcel.writeInt(player_slot);
        parcel.writeLong(account_id);
        parcel.writeTypedList(additional_units);
        parcel.writeInt(assists);
        parcel.writeInt(backpack_0);
        parcel.writeInt(backpack_1);
        parcel.writeInt(backpack_2);
        parcel.writeInt(camps_stacked);
        parcel.writeInt(creeps_stacked);
        parcel.writeInt(deaths);
        parcel.writeInt(denies);
        parcel.writeInt(gold);
        parcel.writeInt(gold_per_min);
        parcel.writeInt(gold_spent);
        parcel.writeInt(hero_damage);
        parcel.writeInt(hero_healing);
        parcel.writeInt(hero_id);
        parcel.writeInt(item_0);
        parcel.writeInt(item_1);
        parcel.writeInt(item_2);
        parcel.writeInt(item_3);
        parcel.writeInt(item_4);
        parcel.writeInt(item_5);
        parcel.writeInt(kills);
        parcel.writeInt(last_hits);
        parcel.writeInt(leaver_status);
        parcel.writeInt(level);
        parcel.writeInt(obs_placed);
        parcel.writeInt(party_id);
        parcel.writeInt(rune_pickups);
        parcel.writeInt(sen_placed);
        parcel.writeFloat(stuns);
        parcel.writeInt(tower_damage);
        parcel.writeInt(xp_per_min);
        parcel.writeString(personaname);
        parcel.writeByte((byte) (radiant_win ? 1 : 0));
        parcel.writeLong(start_time);
        parcel.writeInt(duration);
        parcel.writeInt(cluster);
        parcel.writeInt(lobby_type);
        parcel.writeInt(game_mode);
        parcel.writeInt(patch);
        parcel.writeInt(region);
        parcel.writeByte((byte) (isRadiant ? 1 : 0));
        parcel.writeInt(win);
        parcel.writeInt(total_gold);
        parcel.writeInt(total_xp);
        parcel.writeFloat(kills_per_min);
        parcel.writeFloat(kda);
        parcel.writeInt(abandons);
        parcel.writeInt(neutral_kills);
        parcel.writeInt(tower_kills);
        parcel.writeInt(courier_kills);
        parcel.writeInt(hero_kills);
        parcel.writeInt(roshan_kills);
        parcel.writeInt(buyback_count);
        parcel.writeInt(lane_efficiency_pct);
        parcel.writeInt(lane);
        parcel.writeInt(lane_role);
    }
}

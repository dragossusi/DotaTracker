package com.rachierudragos.dotatracker.Wrapper.domain.matchdetail.impl;

import com.rachierudragos.dotatracker.Wrapper.domain.LeaverStatus;
import com.rachierudragos.dotatracker.Wrapper.domain.impl.MatchOverviewPlayerImpl;
import com.rachierudragos.dotatracker.Wrapper.domain.matchdetail.MatchDetailPlayer;

import java.util.ArrayList;
import java.util.Map;

public class MatchDetailPlayerImpl extends MatchOverviewPlayerImpl implements
		MatchDetailPlayer {

	private static final String KEY_ITEM_0 = "item_0";
	private static final String KEY_ITEM_1 = "item_1";
	private static final String KEY_ITEM_2 = "item_2";
	private static final String KEY_ITEM_3 = "item_3";
	private static final String KEY_ITEM_4 = "item_4";
	private static final String KEY_ITEM_5 = "item_5";
	private static final String KEY_BACK_0 = "backpack_0";
	private static final String KEY_BACK_1 = "backpack_1";
	private static final String KEY_BACK_2 = "backpack_2";

	private static final String KEY_KILLS = "kills";
	private static final String KEY_DEATHS = "deaths";
	private static final String KEY_ASSISTS = "assists";
	private static final String KEY_HUMAN_PLAYERS = "human_players";
	private static final String KEY_LEAVER_STATUS = "leaver_status";

	private static final String KEY_GOLD = "gold";
	private static final String KEY_LAST_HITS = "last_hits";
	private static final String KEY_DENIES = "denies";
	private static final String KEY_GOLD_PER_MIN = "gold_per_min";
	private static final String KEY_XP_PER_MIN = "xp_per_min";
	private static final String KEY_GOLD_SPENT = "gold_spent";
	private static final String KEY_HERO_DAMAGE = "hero_damage";
	private static final String KEY_TOWER_DAMAGE = "tower_damage";
	private static final String KEY_HERO_HEALING = "tower_damage";
	private static final String KEY_LEVEL = "level";

	private static final String KEY_ABILITY_UPGRADES = "ability_upgrades";
	private static final String KEY_ABILITY_UPGRADES_ABILITY = "ability";
	private static final String KEY_ABILITY_UPGRADES_TIME = "time";
	private static final String KEY_ABILITY_UPGRADES_LEVEL = "level";

	private static final String KEY_ADDITIONAL_UNITS = "additional_units";
	private static final String KEY_ADDITIONAL_UNITS_ITEM_0 = "item_0";
	private static final String KEY_ADDITIONAL_UNITS_ITEM_1 = "item_1";
	private static final String KEY_ADDITIONAL_UNITS_ITEM_2 = "item_2";
	private static final String KEY_ADDITIONAL_UNITS_ITEM_3 = "item_3";
	private static final String KEY_ADDITIONAL_UNITS_ITEM_4 = "item_4";
	private static final String KEY_ADDITIONAL_UNITS_ITEM_5 = "item_5";

	public MatchDetailPlayerImpl(Map<String, Object> jsonMap) {
		super(jsonMap);
	}

	@Override
	public ArrayList<Integer> getItems() {
		ArrayList<Integer> items= new ArrayList<>();
		items.add(((Double) jsonMap.get(KEY_ITEM_0)).intValue());
		items.add(((Double) jsonMap.get(KEY_ITEM_1)).intValue());
		items.add(((Double) jsonMap.get(KEY_ITEM_2)).intValue());
		items.add(((Double) jsonMap.get(KEY_ITEM_3)).intValue());
		items.add(((Double) jsonMap.get(KEY_ITEM_4)).intValue());
		items.add(((Double) jsonMap.get(KEY_ITEM_5)).intValue());
		items.add(((Double) jsonMap.get(KEY_BACK_0)).intValue());
		items.add(((Double) jsonMap.get(KEY_BACK_1)).intValue());
		items.add(((Double) jsonMap.get(KEY_BACK_2)).intValue());
		return items;
	}

	@Override
	public int getKills() {
		return ((Double) jsonMap.get(KEY_KILLS)).intValue();
	}

	@Override
	public int getDeaths() {
		return ((Double) jsonMap.get(KEY_DEATHS)).intValue();
	}

	@Override
	public int getAssists() {
		return ((Double) jsonMap.get(KEY_ASSISTS)).intValue();
	}

	@Override
	public LeaverStatus getLeaverStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGold() {
		return ((Double) jsonMap.get(KEY_GOLD)).intValue();
	}

	@Override
	public int getLastHits() {
		return ((Double) jsonMap.get(KEY_LAST_HITS)).intValue();
	}

	@Override
	public int getDenies() {
		return ((Double) jsonMap.get(KEY_DENIES)).intValue();
	}

	@Override
	public int getGoldPerMinute() {
		return ((Double) jsonMap.get(KEY_GOLD_PER_MIN)).intValue();
	}

	@Override
	public int getXPPerMinute() {
		return ((Double) jsonMap.get(KEY_XP_PER_MIN)).intValue();
	}

	@Override
	public int getGoldSpent() {
		return ((Double) jsonMap.get(KEY_GOLD_SPENT)).intValue();
	}

	@Override
	public int getHeroDamageDealt() {
		return ((Double) jsonMap.get(KEY_HERO_DAMAGE)).intValue();
	}

	@Override
	public int getTowerDamageDealt() {
		return ((Double) jsonMap.get(KEY_TOWER_DAMAGE)).intValue();
	}

	@Override
	public int getHeroDamageHealt() {
		return ((Double) jsonMap.get(KEY_HERO_HEALING)).intValue();
	}

	@Override
	public int getHeroLevel() {
		return ((Double) jsonMap.get(KEY_LEVEL)).intValue();
	}

	@Override
	public int[] getSkilledAbilityOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("MatchDetailPlayer object:\n");
		sb.append("---- Kills: " + getKills() + "\n");
		sb.append("---- Deaths: " + getDeaths() + "\n");
		sb.append("---- Assists: " + getAssists() + "\n");
		sb.append("---- XPM: " + getXPPerMinute() + "\n");
		sb.append("---- GPM: " + getGoldPerMinute() + "\n");
		sb.append("---- HeroLevel: " + getHeroLevel() + "\n");	

		return super.toString() + sb.toString();
	}

}

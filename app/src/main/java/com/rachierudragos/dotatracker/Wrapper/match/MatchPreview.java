package com.rachierudragos.dotatracker.Wrapper.match;

import java.io.Serializable;

public class MatchPreview implements Serializable {
	public long match_id;
	public int player_slot;
	public boolean radiant_win;
	public int duration;
	public int game_mode;
	public int lobby_type;
	public int hero_id;
	public long start_time;
	public int version;
	public int kills;
	public int deaths;
	public int assists;
}

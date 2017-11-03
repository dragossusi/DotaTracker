package com.rachierudragos.dotatracker.Wrapper.match;

public class MatchPreview {
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
	public String getDurationText() {
		int secs = duration%60;
        int mins = duration/60;
        int hours = mins/60;
        mins%=60;
        String seconds = (secs < 10 ? "0" : "") + secs;
        String minutes = (mins<10?"0":"") + mins;
        return (hours==0?"":hours+":")+minutes+":"+seconds;
	}
	public boolean hasWon() {
        return radiant_win==(player_slot<128);
    }
}

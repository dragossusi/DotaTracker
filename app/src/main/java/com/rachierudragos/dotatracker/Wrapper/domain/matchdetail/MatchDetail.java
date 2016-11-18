package com.rachierudragos.dotatracker.Wrapper.domain.matchdetail;

import com.rachierudragos.dotatracker.Wrapper.domain.GameMode;
import com.rachierudragos.dotatracker.Wrapper.domain.MatchOverview;

import java.io.Serializable;
import java.util.List;

public interface MatchDetail extends Serializable {

	public boolean didRadianWin();

	public int getDurationOfMatch();

	public MatchOverview getMatchOverview();

	public int getFirstBloodTime();

	public int getHumanPlayer();

	public int getLeagueId();

	public int getPositiveVotes();

	public int getNegativeVotes();

	public GameMode getGameMode();

	public List<MatchDetailPlayer> getPlayers();

}

package com.rachierudragos.dotatracker.Wrapper.domain;

import java.util.List;

public interface MatchOverview {

	public List<MatchOverviewPlayer> getPlayers();

	public long getMatchId();

	public long getMatchSeqNumber();

	public long getStartTime();

	public int getLobbyType();

}

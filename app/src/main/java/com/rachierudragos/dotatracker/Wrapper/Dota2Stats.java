package com.rachierudragos.dotatracker.Wrapper;

import com.rachierudragos.dotatracker.Wrapper.domain.filter.MatchHistoryFilter;
import com.rachierudragos.dotatracker.Wrapper.domain.matchdetail.MatchDetail;
import com.rachierudragos.dotatracker.Wrapper.domain.matchhistory.MatchHistory;
import com.rachierudragos.dotatracker.Wrapper.domain.playersearch.PlayerSearchResult;
import com.rachierudragos.dotatracker.Wrapper.domain.playerstats.PlayerStats;
import com.rachierudragos.dotatracker.Wrapper.exceptions.Dota2StatsAccessException;

import java.util.List;

public interface Dota2Stats {

	public List<PlayerSearchResult> searchByPlayerName(String name) throws Dota2StatsAccessException;
	
	public MatchHistory getMostRecentMatchHistory() throws Dota2StatsAccessException;

	public MatchHistory getMatchHistory(MatchHistoryFilter filter) throws Dota2StatsAccessException;
	
	public MatchDetail getMatchDetails(long matchId) throws Dota2StatsAccessException;	

	public PlayerStats getStats(long accountId, MatchHistoryFilter filter) throws Dota2StatsAccessException;

	public PlayerStats getStats(long accountId, int numberOfMatches) throws Dota2StatsAccessException;

}

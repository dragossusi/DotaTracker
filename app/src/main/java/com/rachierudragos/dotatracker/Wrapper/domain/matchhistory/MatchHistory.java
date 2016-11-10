package com.rachierudragos.dotatracker.Wrapper.domain.matchhistory;

import java.util.List;

import com.rachierudragos.dotatracker.Wrapper.domain.MatchOverview;

public interface MatchHistory {

	public int getStatus();

	public int getNumberOfResults();

	public int getTotalNumberOfResults();

	public int getResultsRemaining();

	public List<MatchOverview> getMatchOverviews();

}

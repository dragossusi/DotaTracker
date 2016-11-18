package com.rachierudragos.dotatracker.Wrapper.domain;

import java.io.Serializable;

public interface MatchOverviewPlayer extends Serializable {

	public long getAccountId();

	public int getPlayerSlots();

	public int getHeroId();

}

package com.rachierudragos.dotatracker.Wrapper.domain;

import java.io.Serializable;

public enum LeaverStatus implements Serializable {

	Stayed(0), SaveLeaver(1), AbandonedGame(2), Bot(4);

	private final int value;

	private LeaverStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public LeaverStatus getType(int value) {

		return LeaverStatus.values()[value];

	}

}

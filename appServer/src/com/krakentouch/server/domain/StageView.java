package com.krakentouch.server.domain;

public class StageView {
	
	private String stageSN;
	
	private int Status;
	
	private String hostIndex;
	
	private String gameId;
	
	private String playerId;

	public String getStageSN() {
		return stageSN;
	}

	public void setStageSN(String stageSN) {
		this.stageSN = stageSN;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public String getHostIndex() {
		return hostIndex;
	}

	public void setHostIndex(String hostIndex) {
		this.hostIndex = hostIndex;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	
}

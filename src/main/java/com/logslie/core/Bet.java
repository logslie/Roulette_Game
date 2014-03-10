/**
 * 
 * Creation Date Mar 10, 2014
 * 
 * @author Laura Garcia Perez
 * @email logslie@gmail.com
 *        www.logslie.com
 */
package com.logslie.core;

/**
 * Defines a bet, which contains the player's name, the type's bet ([1-36] |
 * EVEN | ODD) and how much the use wants to bet.
 * 
 */
public class Bet {

	private String playerName;
	private String betType;
	private double bet;

	/**
	 * @param playerName
	 * @param betType
	 * @param betValueDouble
	 */
	public Bet(String playerName, String betType, double betValueDouble) {
		super();
		this.playerName = playerName;
		this.betType = betType;
		this.bet = betValueDouble;
	}

	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @param playerName
	 *            the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * @return the betType
	 */
	public String getBetType() {
		return betType;
	}

	/**
	 * @param betType
	 *            the betType to set
	 */
	public void setBetType(String betType) {
		this.betType = betType;
	}

	/**
	 * @return the bet
	 */
	public double getBet() {
		return bet;
	}

	/**
	 * @param bet
	 *            the bet to set
	 */
	public void setBet(float bet) {
		this.bet = bet;
	}

}


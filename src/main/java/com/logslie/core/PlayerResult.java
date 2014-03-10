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
 * Defines the player's result for a game, which contains the player's name,
 * bet's type, outcome, winnings, total win and total bet.
 * 
 */
public class PlayerResult {
	private Player player;
	private String typeBet;
	private OutcomeType outcome;
	private double winnings;

	/**
	 * @param player
	 * @param typeBet
	 * @param outcome
	 * @param winCount
	 */
	public PlayerResult(Player player, String typeBet, OutcomeType outcome, double winCount) {
		super();
		this.player = player;
		this.typeBet = typeBet;
		this.outcome = outcome;
		this.winnings = winCount;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player
	 *            the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the typeBet
	 */
	public String getTypeBet() {
		return typeBet;
	}

	/**
	 * @param typeBet
	 *            the typeBet to set
	 */
	public void setTypeBet(String typeBet) {
		this.typeBet = typeBet;
	}

	/**
	 * @return the outcome
	 */
	public OutcomeType getOutcome() {
		return outcome;
	}

	/**
	 * @param outcome
	 *            the outcome to set
	 */
	public void setOutcome(OutcomeType outcome) {
		this.outcome = outcome;
	}

	/**
	 * @return the winnings
	 */
	public double getWinnings() {
		return winnings;
	}

	/**
	 * @param winnings
	 *            the winnings to set
	 */
	public void setWinnings(double winnings) {
		this.winnings = winnings;
	}

	/**
	 * 
	 * @param pr
	 * @return true when this object has the same information than the pr object
	 */
	public boolean isEquals(PlayerResult pr) {
		if (pr != null) {
			return this.player.isEquals(pr.getPlayer()) && this.outcome == pr.getOutcome()
					&& this.typeBet.equals(pr.getTypeBet()) && this.winnings == pr.getWinnings();
		} else {
			return false;
		}
	}

}

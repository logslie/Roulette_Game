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
 * Defines a player, which is defined by his name (unique ID in the system), the
 * total amount the user has won and the total amount the user has bet.
 * 
 * 
 */
public class Player {

	private String name;
	private double totalWin;
	private double totalBet;

	/**
	 * @param name
	 * @param totalWinValue
	 * @param totalBetValue
	 */
	public Player(String name, double totalWinValue, double totalBetValue) {
		super();
		this.name = name;
		this.totalWin = totalWinValue;
		this.totalBet = totalBetValue;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the totalWin
	 */
	public double getTotalWin() {
		return totalWin;
	}

	/**
	 * @param totalWin
	 *            the totalWin to set
	 */
	public void setTotalWin(double totalWin) {
		this.totalWin = totalWin;
	}

	/**
	 * @return the totalBet
	 */
	public double getTotalBet() {
		return totalBet;
	}

	/**
	 * @param totalBet
	 *            the totalBet to set
	 */
	public void setTotalBet(double totalBet) {
		this.totalBet = totalBet;
	}

	/**
	 * 
	 * @param player
	 * @return true when this object has the same information than the pr object
	 */
	public boolean isEquals(Player player) {
		if (player != null) {
			return this.name.equals(player.getName()) && this.totalBet == player.getTotalBet()
					&& this.totalWin == player.getTotalWin();
		} else {
			return false;
		}

	}
}

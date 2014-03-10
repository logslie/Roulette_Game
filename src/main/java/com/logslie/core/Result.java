/**
 * 
 * Creation Date Mar 10, 2014
 * 
 * @author Laura Garcia Perez
 * @email logslie@gmail.com
 *        www.logslie.com
 */
package com.logslie.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Defines the result's game, which contains the number that was chosen, and a
 * list with the player's result.
 * 
 */
public class Result {
	private int number;
	private HashMap<String, PlayerResult> playerResultList;

	/**
	 * @param number
	 * @param playerResultList
	 */
	public Result(int number, HashMap<String, PlayerResult> playerResultList) {
		super();
		this.number = number;
		this.playerResultList = playerResultList;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the playerResultList
	 */
	public HashMap<String, PlayerResult> getPlayerResultList() {
		return playerResultList;
	}

	/**
	 * @param playerResultList
	 *            the playerResultList to set
	 */
	public void setPlayerResultList(HashMap<String, PlayerResult> playerResultList) {
		this.playerResultList = playerResultList;
	}

	/**
	 * 
	 * @param result
	 * @return true when this object has the same information than the result object
	 */
	public boolean isEquals(Result result) {
		if (result != null) {
			if (this.number == result.number) {
				boolean exist = true;
				if (result.getPlayerResultList() != null && this.playerResultList != null
						&& result.getPlayerResultList().size() == this.playerResultList.size()) {
					Iterator<Map.Entry<String, PlayerResult>> it = this.playerResultList.entrySet().iterator();
					while (it.hasNext() && exist) {
						Map.Entry<String, PlayerResult> entry = it.next();
						PlayerResult exPr = entry.getValue();
						PlayerResult pr = result.getPlayerResultList().get(entry.getKey());
						exist = exist && exPr.isEquals(pr);
					}
					return exist;
				} else {
					return false;
				}

			} else {
				return false;
			}
		} else {
			return false;
		}

	}

}

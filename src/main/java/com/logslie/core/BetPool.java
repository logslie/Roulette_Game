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
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author logslie
 * 
 */
public class BetPool {

	private HashMap<String, Bet> betPool = new HashMap<String, Bet>();
	private Lock lock = new ReentrantLock();

	/**
	 * 
	 */
	public BetPool() {
		super();
	}

	/**
	 * Inserts a bet in the concurrent hashmap
	 * 
	 * @param bet
	 */
	public void insertBet(Bet bet) {
		this.lock.lock();
		this.betPool.put(bet.getPlayerName(), bet);
		this.lock.unlock();
	}

	/**
	 * 
	 * @param usernameBet
	 * @return A Bet by player
	 */
	public Bet getBet(String usernameBet) {
		this.lock.lock();
		Bet bet = this.betPool.get(usernameBet);
		this.lock.unlock();
		return bet;

	}

	/**
	 * 
	 * @return A LinkedList of bets
	 */
	public LinkedList<Bet> getListBets() {
		LinkedList<Bet> betList = new LinkedList<Bet>();
		this.lock.lock();
		Iterator<Map.Entry<String, Bet>> betIterator = this.betPool.entrySet().iterator();
		while (betIterator.hasNext()) {
			betList.add(betIterator.next().getValue());
		}
		this.betPool.clear();
		this.lock.unlock();
		return betList;
	}

	public int getSize() {
		int size = 0;
		this.lock.lock();
		size = this.betPool.size();
		this.lock.unlock();
		return size;
	}
}

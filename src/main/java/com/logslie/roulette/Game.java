/**
 * 
 * Creation Date Mar 10, 2014
 * 
 * @author Laura Garcia Perez
 * @email logslie@gmail.com
 *        www.logslie.com
 */
package com.logslie.roulette;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.logslie.core.Bet;
import com.logslie.core.BetPool;
import com.logslie.core.OutcomeType;
import com.logslie.core.Player;
import com.logslie.core.PlayerResult;
import com.logslie.core.RandomNumber;
import com.logslie.core.Result;

/**
 * @author logslie
 * 
 */
public class Game extends Thread {

	private Timer timer;
	private HashMap<String, Player> players;
	private BetPool betPool;
	private ConcurrentLinkedQueue<Result> resultQueue;

	/**
	 * 
	 */
	public Game(BetPool betPool, HashMap<String, Player> players, ConcurrentLinkedQueue<Result> resultQueue) {
		super();
		this.betPool = betPool;
		this.players = players;
		this.resultQueue = resultQueue;

	}

	private void processGame() {
		// Get the bet list at this moment
		LinkedList<Bet> betList = this.betPool.getListBets();

		// Generates the random number
		int number = RandomNumber.getRandomNumber();

		// Generates the result
		if (betList != null && !betList.isEmpty()) {
			
			this.generateResult(betList, number);
		}
		// Starts the timer
		this.startTimer();

	}

	private void generateResult(LinkedList<Bet> betList, int number) {
		Iterator<Bet> betIterator = betList.iterator();
		HashMap<String,PlayerResult> playerResultList = new HashMap<String,PlayerResult>();

		while (betIterator.hasNext()) {
			// Get bet
			Bet bet = betIterator.next();

			// Get player object
			Player player = this.players.get(bet.getPlayerName());

			// Get player information
			double totalWin = player.getTotalWin();
			double totalBet = player.getTotalBet();
			String betType = bet.getBetType();
			double betValue = bet.getBet();
			double winCount = 0.0;
			OutcomeType outcome = OutcomeType.LOSE;

			if (number != 0) {

				// EVEN
				if (number % 2 == 0) {
					if (betType.equals("EVEN")) {
						winCount = betValue * 2;
						outcome = OutcomeType.WIN;
					}

				} // ODD
				else {
					if (betType.equals("ODD")) {
						winCount = betValue * 2;
						outcome = OutcomeType.WIN;
					}

				}

				// Number
				if (!betType.equals("EVEN") && !betType.equals("ODD")) {
					int betNumber = Integer.parseInt(betType);
					if (betNumber == number) {
						winCount = betValue * 36;
						outcome = OutcomeType.WIN;
					}
				}
			}
			totalBet = totalBet + bet.getBet();
			totalWin = totalWin + winCount;
			player.setTotalBet(totalBet);
			player.setTotalWin(totalWin);
			PlayerResult playerResult = new PlayerResult(player, betType, outcome, winCount);
			playerResultList.put(player.getName(),playerResult);
			this.players.put(bet.getPlayerName(), player);
		}

		Result result = new Result(number, playerResultList);
		this.resultQueue.add(result);

	}

	private void startTimer() {
		this.timer = new Timer();
		timer.schedule(new RemindTask(), 30 * 1000);
	}

	public void run() {
		this.startTimer();

	}

	class RemindTask extends TimerTask {
		public void run() {

			processGame();

		}
	}

	
}

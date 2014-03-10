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
import java.util.Scanner;


import com.logslie.core.Bet;
import com.logslie.core.BetPool;
import com.logslie.core.Player;

/**
 * @author logslie
 * 
 */
public class ReaderBet extends Thread {
	
	private HashMap<String, Player> players;
	private BetPool betPool;
	private Scanner console;

	public ReaderBet(BetPool betPool, HashMap<String, Player> players) {
		this.players = players;
		this.betPool = betPool;
	}

	public void run() {

		System.out.println("Insert bet. Example: Player1 EVEN 3.0");
		console = new Scanner(System.in);
		while (console.hasNextLine()) {
			String bet = console.nextLine();
			System.out.println("Received bet:"+bet);
			this.processBet(bet);
		}

	}

	/**
	 * Validate and process the bet
	 * 
	 * @param bet
	 */
	private void processBet(String bet) {
		if (bet != null && !bet.equalsIgnoreCase("")) {
			String[] betSplit = bet.split(" ");
			if (betSplit.length == 3) {
				String username = betSplit[0];
				String betType = betSplit[1];
				String betValue = betSplit[2];
				if (this.players.containsKey(username)) {
					boolean isValidBetType = this.validateBetType(betType);
					if (isValidBetType) {
						try {
							double betValueDouble = Double.parseDouble(betValue);
							if (betValueDouble > 0.0) {
								Bet betObject = new Bet(username, betType, betValueDouble);
								this.betPool.insertBet(betObject);
							} else {
								System.err.println("Incorrect bet value, please choose a positive number");
							}
						} catch (NumberFormatException ex) {
							System.err.println("Incorrect bet value, please choose a positive number");
						}
					}

				} else {
					System.err.println("Unknown player " + username);
				}
			} else {
				System.err
				.println("Inserted bet:"+ bet);
				System.err
						.println("Expected bet with format: PlayerName {number | ODD | EVEN } bet. Example: Player1 EVEN 2.0");
			}
		}
	}

	/**
	 * Validates the bet type.
	 * 
	 * @param betType
	 * @return True if the bet is EVEN, ODD or a number from 1-36
	 */
	private boolean validateBetType(String betType) {
		return betType.equals("EVEN") || betType.equals("ODD") || this.numberInRange(betType);
	}

	/**
	 * Validates the bet type in case that is a number
	 * 
	 * @param betType
	 * @return True if the bet is a number from 1-36
	 */
	private boolean numberInRange(String betType) {
		try {
			int number = Integer.parseInt(betType);
			return number >= 1 && number <= 36;
		} catch (NumberFormatException e) {
			System.err.println("Incorrect number, please choose a number from 1-36");
			return false;
		}
	}
}

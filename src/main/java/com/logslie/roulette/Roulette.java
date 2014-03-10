/**
 * 
 * Creation Date Mar 10, 2014
 * 
 * @author Laura Garcia Perez
 * @email logslie@gmail.com
 *        www.logslie.com
 */
package com.logslie.roulette;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import com.logslie.core.BetPool;
import com.logslie.core.Player;
import com.logslie.core.Result;

import static java.lang.System.*;

/**
 * @author logslie
 * 
 */
public class Roulette {

	public final static Logger loggerDevelopment = Logger.getLogger("DEVELOPMENT");
	public final static Charset ENCODING = StandardCharsets.UTF_8;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashMap<String, Player> players = null;
		BetPool betPool = new BetPool();
		ConcurrentLinkedQueue<Result> resultQueue = new ConcurrentLinkedQueue<Result>();

		if (args.length != 1) {
			String errorMessage = "Expected player file argument";
			loggerDevelopment.error(errorMessage);
			err.println(errorMessage);
		} else {
			String playerFile = args[0];
			players = loadFile(playerFile);
		}
		if (players != null) {
			WriterResult writerResult = new WriterResult(resultQueue);
			Game game = new Game(betPool, players, resultQueue);
			ReaderBet roulette = new ReaderBet(betPool, players);
			writerResult.start();
			game.start();
			roulette.start();
			while(true){
				
			}
		}
		

	}

	/**
	 * Load the players' file and create the players' list
	 * 
	 * @param playerFile
	 * @return Player's LinkedList
	 */
	private static HashMap<String, Player> loadFile(String playerFile) {
		HashMap<String, Player> players = new HashMap<String, Player>();
		out.println("Loading players' file...");
		
		try {
			File file = new File(playerFile);
			Boolean correctFile = true;
			Scanner scanner = new Scanner(file, ENCODING.name());
			while (scanner.hasNextLine() && correctFile) {
				String line = scanner.nextLine();
				Player player = processLine(line);
				if (player != null) {
					if(!players.containsKey(player.getName())){
						out.println("Loaded player: "+player.getName());
						players.put(player.getName(), player);
					}else{
						players = null;
						correctFile = false;
						err.println("The user "+player.getName()+" already exists");
						loggerDevelopment.error("The user "+player.getName()+" already exists");
					}
				} else {
					correctFile = false;
				}
			}
			if (!correctFile) {
				players = null;
				err.println("Bad format file!");
				loggerDevelopment.error("Bad format file!");
			}
			scanner.close();

		} catch (FileNotFoundException e) {
			players = null;
			err.println(playerFile + ": File not found");
			loggerDevelopment.info(e.getMessage());

		}
		return players;

	}

	/**
	 * Creates a Player instance
	 * 
	 * @param line
	 * @return Player Object
	 */
	private static Player processLine(String line) {
		Player player = null;
		String[] playerString = line.split(",");
		String username = "";
		String totalWin = "";
		String totalBet = "";

		if (playerString.length == 3) {
			// Player with totals
			username = playerString[0];
			totalWin = playerString[1];
			totalBet = playerString[2];
		} else if (playerString.length == 1) {
			// Player with totals
			username = playerString[0];
			totalWin = "0.0";
			totalBet = "0.0";
		} else {

			return null;
		}

		// Validate information
		String regexPlayer = "[a-zA-Z0-9_-]{5,15}";
		if (username.matches(regexPlayer)) {
			try {
				double totalWinValue = Double.parseDouble(totalWin);
				double totalBetValue = Double.parseDouble(totalBet);
				if (totalWinValue >= 0.0 && totalBetValue >= 0.0) {
					player = new Player(username, totalWinValue, totalBetValue);
				}

			} catch (NumberFormatException e) {
				err.println("Expected total win and total bet with a double value for the username: " + username);
				loggerDevelopment.info("Expected total win and total bet with a double value for the username: "
						+ username);
			}

		} else {
			err.println("Expected username. Length 5-15 characters.Valid characters a-z, A-Z, -, _ for the username: "
					+ username);
			loggerDevelopment
					.info("Expected username. Length 5-15 characters.Valid characters a-z, A-Z, -, _ for the username: "
							+ username);
		}

		return player;

	}

}


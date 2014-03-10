/**
 * 
 * Creation Date Mar 10, 2014
 * 
 * @author Laura Garcia Perez
 * @email logslie@gmail.com
 *        www.logslie.com
 */
package com.logslie.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;


import org.junit.BeforeClass;
import org.junit.Test;

import com.logslie.core.Bet;
import com.logslie.core.BetPool;
import com.logslie.core.Player;
import com.logslie.roulette.ReaderBet;

public class ReaderBetTest {

	private static HashMap<String, Player> players;
	private static BetPool betPool;
	private static HashMap<String, Bet> expectedBetPool;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		players = new HashMap<String, Player>();
		betPool = new BetPool();
		players.put("Tiki_Monkey", new Player("Tiki_Monkey", 1.0, 2.0));
		players.put("Barbara", new Player("Barbara", 2.0, 1.0));
		expectedBetPool = new HashMap<String, Bet>();
		expectedBetPool.put("Tiki_Monkey", new Bet("Tiki_Monkey", "2", 1.0));
		expectedBetPool.put("Barbara", new Bet("Barbara", "EVEN", 3.0));

	}

	@Test
	public void test() {

		try {
			String data = "Tiki_Monkey 2 1.0\nBarbara EVEN 3.0\n";
			InputStream testInput = new ByteArrayInputStream(data.getBytes());
			InputStream old = System.in;
			System.setIn( testInput );
			ReaderBet readerBet = new ReaderBet(betPool, players);
			readerBet.start();		
			Thread.sleep(1000);
			System.setIn( old );
			boolean correct = true;
			if (betPool.getSize() == expectedBetPool.size()) {
				Iterator<Bet> itBet = betPool.getListBets().iterator();
				while (itBet.hasNext() && correct) {
					Bet bet = itBet.next();
					Bet betExpected = expectedBetPool.get(bet.getPlayerName());
					if (betExpected != null) {
						if (!bet.getBetType().equals(betExpected.getBetType()) || bet.getBet() != betExpected.getBet()) {
							correct = false;
							fail("Expected Bet: " + betExpected.getPlayerName() + "," + betExpected.getBetType() + ","
									+ betExpected.getBet());
						}

					} else {
						correct = false;
						fail("Unexpected bet: " + bet.getPlayerName());
					}
				}
			} else {
				fail("ReaderBet Test fail!");
			}
			if (correct) {
				assertTrue("ReaderBet test passed!", correct);
			}

		} catch (Exception ex) {
			fail("ReaderBet Test fail!");
		}
	}

}

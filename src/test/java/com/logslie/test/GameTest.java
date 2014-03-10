/**
 * 
 * Creation Date Mar 10, 2014
 * 
 * @author Laura Garcia Perez
 * @email logslie@gmail.com
 *        www.logslie.com
 */
package com.logslie.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.logslie.core.Bet;
import com.logslie.core.BetPool;
import com.logslie.core.OutcomeType;
import com.logslie.core.Player;
import com.logslie.core.PlayerResult;
import com.logslie.core.RandomNumber;
import com.logslie.core.Result;
import com.logslie.roulette.Game;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RandomNumber.class)
public class GameTest {

	private static HashMap<String, Player> players;
	private static BetPool betPool;
	private static ConcurrentLinkedQueue<Result> resultQueue;
	private static HashMap<String,PlayerResult> plist;
	private static Result expectedResult;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		players = new HashMap<String, Player>();
		betPool = new BetPool();
		resultQueue = new ConcurrentLinkedQueue<Result>();
		plist = new HashMap<String,PlayerResult>();
		plist.put("Tiki_Monkey",new PlayerResult(new Player("Tiki_Monkey",1.0,3.0),"2",OutcomeType.LOSE, 0.0));
		plist.put("Barbara",new PlayerResult(new Player("Barbara",8.0,4.0),"EVEN",OutcomeType.WIN, 6.0));
		expectedResult = new Result(8, plist);
		betPool.insertBet(new Bet("Tiki_Monkey", "2", 1.0));
		betPool.insertBet(new Bet("Barbara", "EVEN", 3.0));
		players.put("Tiki_Monkey", new Player("Tiki_Monkey", 1.0, 2.0));
		players.put("Barbara", new Player("Barbara", 2.0, 1.0));
	}

	@Test
	public void test() {

		try {
			
			 	PowerMockito.mockStatic(RandomNumber.class);
			 	Mockito.when(RandomNumber.getRandomNumber()).thenReturn(8);
		        Game game = new Game(betPool,players,resultQueue);
		        game.start();
		        Result result = null;
		        while (result == null) {
					result = resultQueue.poll();
				}
		      
		        boolean areEquals = expectedResult.isEquals(result);
		        assertEquals(true, areEquals);
		
			
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("ReaderBet Test fail!");
		}
	}
}

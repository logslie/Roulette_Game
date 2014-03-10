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

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import com.logslie.core.Player;
import com.logslie.roulette.Roulette;

/**
 * @author logslie
 * 
 */
public class RouletteTest {
	public final static Logger loggerDevelopment = Logger.getLogger("DEVELOPMENT");
	private static HashMap<String, Player> expectedPlayerList = new HashMap<String, Player>();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		expectedPlayerList.put("Tiki_Monkey", new Player("Tiki_Monkey", 1.0, 2.0));
		expectedPlayerList.put("Barbara", new Player("Barbara", 2.0, 1.0));
	}

	/**
	 * Test method for
	 * {@link com.logslie.roulette.Roulette#main(java.lang.String[])}.
	 */
	@Test
	public void testMainNoArguments() {
		PrintStream originalOut = System.out;
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setErr(ps);
		Roulette.main(new String[] {});
		assertEquals("Expected player file argument\n", os.toString());
		System.setErr(originalOut);

	}

	/**
	 * Test method for
	 * {@link com.logslie.roulette.Roulette#main(java.lang.String[])}.
	 */
	@Test
	public void testMainInvalidArguments() {
		PrintStream originalOut = System.out;
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setErr(ps);
		String file = "/usr/local/src/test.txt";
		Roulette.main(new String[] { file });
		assertEquals(file + ": File not found\n", os.toString());
		System.setErr(originalOut);

	}

	/**
	 * Test method for
	 * {@link com.logslie.roulette.Roulette#main(java.lang.String[])}.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testMainLoadingFile() {
		URL playerFile = ClassLoader.getSystemClassLoader().getResource("players.txt");
		String playerPath = playerFile.getPath();

		try {

			// Get method loadFile and change it to accessible to allow the
			// access and test it
			Roulette main = new Roulette();
			Class<?> mainClass = main.getClass();
			Class<?> params[] = new Class[1];
			params[0] = String.class;
			Method loadFile = mainClass.getDeclaredMethod("loadFile", params);
			loadFile.setAccessible(true);

			// Call to method loadFile
			HashMap<String, Player> playersList = (HashMap<String, Player>) loadFile.invoke(mainClass, playerPath);
			boolean correct = true;
			if (playersList != null) {
				Iterator<Map.Entry<String, Player>> playerIterator = expectedPlayerList.entrySet().iterator();

				while (playerIterator.hasNext() && correct) {
					Map.Entry<String, Player> expectedPlayer = playerIterator.next();
					Player player = playersList.get(expectedPlayer.getKey());
					if (player != null && expectedPlayer != null
							&& !player.getName().equals(expectedPlayer.getValue().getName())
							|| player.getTotalWin() != expectedPlayer.getValue().getTotalWin()
							|| player.getTotalBet() != expectedPlayer.getValue().getTotalBet()) {

						correct = false;
						fail("Expected player: " + expectedPlayer.getKey() + ","
								+ expectedPlayer.getValue().getTotalWin() + ","
								+ expectedPlayer.getValue().getTotalBet());
					} else {
						loggerDevelopment.info("New player:" + player.getName() + "," + player.getTotalWin() + ","
								+ player.getTotalBet());
					}
				}

				assertTrue("LoadFile test passed!", correct);
			} else {
				fail("Bad format file!");
			}

		} catch (NoSuchMethodException e) {
			fail(e.getMessage());
		} catch (SecurityException e) {
			fail(e.getMessage());
		} catch (IllegalAccessException e) {
			fail(e.getMessage());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		} catch (InvocationTargetException e) {
			fail(e.getMessage());
		}

	}
	
	
	
}

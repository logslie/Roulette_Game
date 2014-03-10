/**
 * 
 * Creation Date Mar 10, 2014
 * 
 * @author Laura Garcia Perez
 * @email logslie@gmail.com
 *        www.logslie.com
 */
package com.logslie.roulette;


import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.logslie.core.PlayerResult;
import com.logslie.core.Result;
import static java.lang.System.*;

/**
 * @author logslie
 * 
 */
public class WriterResult extends Thread {

	private ConcurrentLinkedQueue<Result> resultQueue;

	/**
	 * @param resultQueue
	 */
	public WriterResult(ConcurrentLinkedQueue<Result> resultQueue) {
		super();
		this.resultQueue = resultQueue;
	}

	public void run() {
		while (true) {
			Result result = this.resultQueue.poll();
			if (result != null) {
				int number = result.getNumber();
				out.println("Number: " + number);
				String leftAlignFormat = "| %-15s | %-4s | %-7s | %-8s | %-9s | %-8s  |%n";
				out.format("+=================+======+=========+==========+===========+===========+%n");
				out.printf("| Player          | Bet  | Outcome | Winnings | Total Win | Total Bet |%n");
				out.format("+=================+======+=========+==========+===========+===========+%n");
				Iterator<Map.Entry<String,PlayerResult>> resultPlayerIt = result.getPlayerResultList().entrySet().iterator();
				while (resultPlayerIt.hasNext()) {
					Map.Entry<String,PlayerResult> rp = resultPlayerIt.next();
					out.format(leftAlignFormat, rp.getKey(), rp.getValue().getTypeBet(), rp.getValue().getOutcome(),
							rp.getValue().getWinnings(), rp.getValue().getPlayer().getTotalWin(), rp.getValue().getPlayer().getTotalBet());
				}
				out.format("+=================+======+=========+==========+===========+==========+%n");
			}
		}
	}

}


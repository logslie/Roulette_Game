package com.logslie.core;

import java.util.Random;



public class RandomNumber {

	/**
	 * 
	 * @return A random number between [0,36]
	 */
	public static synchronized int getRandomNumber() {
		int first = 0;
		int last = 36;
		Random random = new Random();
		long range = (long) last - (long) first + 1;
		long fraction = (long) (range * random.nextDouble());
		int randomNumber = (int) (fraction + first);
		return randomNumber;
	}
}

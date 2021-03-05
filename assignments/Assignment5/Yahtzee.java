/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

/*
 * Decomposition
 * First make a dice roller and get that working at least once
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		/* You fill this in */
		int[] test = diceRoll();
		for(int i = 0; i < test.length; i++) {
			println(test[i]);
		}
	}
	
	
	/**
	 * Method: diceRoll
	 * Makes an integer array of size N_DICE and uses a pseudorandrom number
	 * 	generator to generate an integer from 1 to 6 for each element in the
	 * 	array.
	 * @return int[] roll: int array of length N_DICE that has random values
	 * 	from 1 to 6 for each element in the array.
	 */
	private int[] diceRoll() {
		int[] roll = new int[N_DICE];
		for (int i = 0; i < N_DICE; i++) {
			roll[i] = rgen.nextInt(1, 6);
		}
		return roll;
	}
		
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}

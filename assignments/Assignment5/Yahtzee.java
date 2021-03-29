/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

/*
 * Plan:
 * 1. Roll dice and store values
 * 	Done
 * 2. Display dice and let them be rerolled with 'roll again' button
 * 	Done
 * 3. Let dice be selected for reroll, and reroll
 * 	Done
 * 4. Let categories be selected for assertion against dice roll
 * 	Done
 * 5. Assert dice roll against categories with YahtzeeMagicStub.checkCategory()
 * 	Done
 * 6. Display and store 0 if not pass, value if pass.
 * 	Done
 * 7. Have Total reflect total
 * 	Done
 * 8. Allow for turns
 * 	Done
 * 9. Prevent user from clicking same category twice
 * 10. Define Category rules and return values
 * Second get the dice roll to show up on the display.
 * 
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
//		IODialog dialog = getDialog();
//		nPlayers = dialog.readInt("Enter number of players");
//		playerNames = new String[nPlayers];
//		
//
//		playerNames[1] = "you";
//		
//		for (int i = 1; i <= nPlayers; i++) {
//			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
//		}
		
		/*
		 * Temporary for fast testing
		 */
		nPlayers = 1;
		playerNames = new String[nPlayers];
		playerNames[0] = "me";
		players = new YahtzeePlayer[nPlayers];
		players[0] = new YahtzeePlayer(playerNames[0]);
		
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		/* You fill this in */

		int[] roll;
		int turns = 0;
		
		while (turns < N_TURNS) {
			display.printMessage(
				players[0].getName() + "'s turn to roll!"
			);
			roll = rollPlayer(0);
			display.printMessage(
				players[0].getName() + " select a category."
			);
			scorePlayer(0, roll);
			turns++;
		}
		display.printMessage(
			players[0].getName() + 
			", you won with a score of " +
			players[0].getTotal() + "!"
		);	
	}
	
	/**
	 * TODO: Test
	 * Current status: does not break on full house anymore!
	 * Make sure each roll can get a zero for fail and calculate pass.
	 * 20210328 test, one player:
	 * Three of a kind passes when it should not.
	 * Four of a kind passes when it should not.
	 * Yahtzee passes when it should not.
	 * Full house passes when it should not.
	 * Straights and singles fail successfully.
	 * Singles and straights work.
	 * Full house can fail, why not consistent?
	 * Three of a kind fails.
	 */
	
	/*
	 * Make a test class to make the scorer work without running
	 * the actual program.
	 */
	

	
	
	/**
	 * Method: diceRoll
	 * Takes an integer array of size N_DICE and uses a pseudorandrom number
	 * 	generator to generate an integer from 1 to 6 for each element in the
	 * 	array noted to need a roll. The elements in the array are noted to be
	 * 	rolled if an array of size N_DICE is passed with true values at the
	 * 	corresponding index. The parameter array of rolls is modified in place.
	 * @param int[] roll: int array of length N_DICE that has values from
	 * 	1 to 6 for each element in the array.
	 * @param boolean[] isRollNeeded: boolean array of length N_DICE that has
	 * 	values true at each index if user noted values at the same index within
	 * 	the roll array need a re-roll.
	 * @return void The argument roll array is modified in place.
	 */
	private void diceRoll(int[] roll, boolean[] isRollNeeded) {		
		// Roll the dice where isRollNeeded is true for the roll index.
		for (int i = 0; i < roll.length; i++) {
			if (isRollNeeded[i]) {
				roll[i] = rgen.nextInt(1, 6);
			}
		}
	}
	
	/**
	 * Method: displayDice
	 * Displays a dice roll to the display.
	 * @param int[] roll: int array of length N_DICE that has values from 1 to
	 * 	6 for each element in the array
	 * @return void
	 */
	private void displayDice(int[] roll) {
		display.displayDice(roll);
	}
	
	/**
	 * Method: clearDiceStatus
	 * Takes an boolean array and sets all values to false.
	 * @param boolean[] isRollNeeded: boolean array.
	 * @return void The argument isRollNeeded array is modified in place.
	 */
	private void clearDiceStatus(boolean[] isRollNeeded) {
		for (int i = 0; i < isRollNeeded.length; i++) {
			isRollNeeded[i] = false;
		}
	}
	
	
	/**
	 * Method: rollPlayer
	 * Handles user interaction and performs rolls necessary for a turn in
	 * 	Yahtzee. Takes an int for the player index to roll. Returns the final
	 * 	dice rolls in an int array.
	 * @param int player: int representing the player index. Player indices
	 * 	can be any number greater than or equal to zero.
	 * @return int[] roll: int array of length N_DICE that has values from 1 to
	 * 	6 for each element in the array.
	 */
	private int[] rollPlayer(int player) {
		// Initialize the roll and dice to roll.
		int[] roll = new int[N_DICE];
		boolean[] isRollNeeded = {true, true, true, true, true, true};

		/*
		 * Once player clicks roll, do roll for all dice.
		 * Note that the display methods need player + 1 since they
		 * start with index 1.
		 */
		display.waitForPlayerToClickRoll(player + 1);
		diceRoll(roll, isRollNeeded);
		displayDice(roll);
		// Un-select all dice for re-roll.
		clearDiceStatus(isRollNeeded);
		
		// Let player re-roll two times.
		for (int i = 0; i < 2; i++) {
			display.printMessage(
				playerNames[player] + 
				" select dice for reroll and select 'Roll Again'."
			);
			display.waitForPlayerToSelectDice();
			// Reassign isRollNeeded to value of isDieSelected for each die.
			for (int j = 0; j < isRollNeeded.length - 1; j++) {
				isRollNeeded[j] = display.isDieSelected(j);
			}
			// Roll dice, clear dice re-roll status, and update dice display.
			diceRoll(roll, isRollNeeded);
			clearDiceStatus(isRollNeeded);
			displayDice(roll);
		}
		return roll;
	}
	
	/**
	 * Method: scorePlayer
	 * Handles user interaction and handles player scoring for a given roll.
	 * 	The user selects a category for their roll, and the score is displayed
	 * 	for the category based on the validity of the category with the roll.
	 * 	The roll is asserted here, and if true, a score greater than 0 is
	 * 	updated; otherwise the score for the category is assigned 0.
	 * @param int player: int representing the player index. Player indices
	 * 	can be any number greater than or equal to zero.
	 * @param int[] roll: int array representing the player's roll. 
	 * 	Size of N_DICE.
	 * @return void The player's total score are modified in place!
	 */
	private void scorePlayer(int player, int[] roll) {
		// Initialize variables.
		int score = 0;
		int category = 0;
		// Wait for the user to select a category, and assign category.
		while (true) {
			category = display.waitForPlayerToSelectCategory();
			// Only move forward if category not selected before.
			if (!players[player].isInSelectedCategories(category)) {
				players[player].addSelectedCategory(category);
				score = scorer.checkCategory(roll, category);
				// Break only if the category was unselected before.
				break;
			} else {
				display.printMessage(
					players[player].getName() +
					" try again, that category was already selected."
				);
			}
		}
		// Display method needs player starting index of 1.
		display.updateScorecard(category, player + 1, score);
		// Update total.
		//TODO separate between upper and lower scores, only update total at end.
		players[player].updateTotal(score);
		display.updateScorecard(
			TOTAL, 
			player + 1, 
			players[player].getTotal()
		);
	}
		
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private YahtzeePlayer[] players;
	private YahtzeeScorer scorer = new YahtzeeScorer();

/* Constants */
	private static final int N_TURNS = 13;
}

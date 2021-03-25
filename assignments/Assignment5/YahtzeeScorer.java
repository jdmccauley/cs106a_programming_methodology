/*
 * File: YahtzeeScorer.java
 * ------------------------
 * This file contains the class definition for YahtzeeScorer used in a game
 * 	of Yahtzee.
 */

import java.util.*;

public class YahtzeeScorer implements YahtzeeConstants {
	public YahtzeeScorer() {
		this.score = 0;
	}
	
	
	/**
	 * Method: checkCategory
	 * Takes a case to test the roll against, assesses the condition of whether
	 * 	the roll passes the case, and then returns a score of 0 if the roll
	 * 	fails the case, or a value greater than 0. The value greater than zero
	 * 	is computed based on the category's rule.
	 * @param int[] roll: int array representing a roll.
	 * @param int category: int representing a category to test.
	 * @return int score: int with value of corresponding to pass in category,
	 * 	and computed value based on case success.
	 */
	public int checkCategory(int[] roll, int category) {
		// Convert the roll into an ArrayList for ease of use.
		rollList = new ArrayList();
		for (int i = 0; i < roll.length; i++) {
			rollList.add(roll[i]);
		}
		
		switch (category) {
			case ONES:
				/* Number of ones. */
				score = sumValueOnDice(rollList, roll, ONES);
				break;
				
			case TWOS:
				/* Number of twos. */
				score = sumValueOnDice(rollList, roll, TWOS);
				break;
				
			case THREES:
				/* Number of threes. */
				score = sumValueOnDice(rollList, roll, THREES);
				break;
				
			case FOURS:
				/* Number of fours. */
				score = sumValueOnDice(rollList, roll, FOURS);
				break;
				
			case FIVES:
				/* Number of fives. */
				score = sumValueOnDice(rollList, roll, FIVES);
				break;
				
			case SIXES:
				/* Number of sixes. */
				score = sumValueOnDice(rollList, roll, SIXES);
				break;
			
			case THREE_OF_A_KIND:
				/* Contains three of a kind. */
				if (isContainingMultiple(rollList, 3) != 0) {
					score = sumDice(roll);
				}
				break;
			
			case FOUR_OF_A_KIND:
				/* Contains four of a kind. */
				if (isContainingMultiple(rollList, 4) != 0) {
					score = sumDice(roll);
				}
				break;
			
			case FULL_HOUSE:
				/* Contains three of a kind and two of a kind. */
				// TODO this breaks.
				int firstMultiple = isContainingMultiple(rollList, 3);
				if (firstMultiple != 0) {
					// If firstMultiple is a number, remove those.
					for (int i = 0; i < 3; i++) {
						rollList.remove(firstMultiple);
					}
					// Then test if a two of a kind is in list.
					if (isContainingMultiple(rollList, 2) != 0) {
						score = 25;
					}
				} else {
					score = 0;
				}
				break;
			
			case SMALL_STRAIGHT:
				// Temporary for testing.
				score = 30;
				break;
			
			case LARGE_STRAIGHT:
				// Temporary for testing.
				score = 40;
				break;
			
			case YAHTZEE:
				/* Contains 6 of a kind. */
				if (isContainingMultiple(rollList, 6) != 0) {
					score = 50;
				}
				break;
			
			case CHANCE:
				/* Sum of dice values. */
				score = sumDice(roll);
				break;

			default:
				score = 0;
				break;
		}
		
		return score;
	}
	
	
	/**
	 * Method: sumDice
	 * Takes a roll and sums the values of the dice in the roll.
	 * @param int[] roll: int array representing a roll.
	 * @return int total: int representing the sum of values on the dice.
	 */
	private int sumDice(int[] roll) {
		int total = 0;
		for (int i = 0; i < roll.length; i++) {
			total += roll[i];
		}
		
		return total;
	}
	
	
	/**
	 * Method: sumValueOnDice
	 * Takes a roll and a number to count, and returns how many of that number
	 * 	are in the roll.
	 * @param ArrayList<Integer> roll: ArrayList containing 6 Integers
	 * 	from 1 to 6.
	 * 
	 * @param int counter: int to count.
	 * @return int total: int representing total number of counter in roll.
	 */
	private int sumValueOnDice(
			ArrayList<Integer> rollList, 
			int[] roll, 
			int counter
		) {
		int total = 0;
		if (rollList.contains(counter)) {
			for (int i = 0; i < roll.length; i++) {
				if (roll[i] == counter) {
					total += roll[i];
				}
			}
		} else {
			total = 0;
		}
		
		return total;
	}
	

	
	/**
	 * Method: isContainingMultiple
	 * Takes a roll, a number of times to find value, and returns value
	 * 	a number between 1 and 6 is found that number of times in the roll.
	 * 	Otherwise returns 0.
	 * @param ArrayList<Integer> rollList: ArrayList containing 6 Integers with
	 * 	values from 1 to 6.
	 * @param count: int of number of times to count the value in the roll.
	 * @return int whichContaining: value found count number of times in the
	 * 	roll. Otherwise returns 0.
	 */
	private int isContainingMultiple(
			ArrayList<Integer> rollList, 
			int count
	) {
		boolean isContaining = false;
		// Cycle through 1-6 to serve as value.
		for (int i = 1; i < 7; i++) {
			// Assign isContaining to be true if value in roll, until
			// it is not.
			for (int j = 0; j < count; j++) {
				if (rollList.contains(i)) {
					isContaining = true;
				} else {
					isContaining = false;
				}
			}
			// If still true for number of count, end cycle of 1-6.
			if (isContaining) return i;
		}
		
		return 0;
	}
	
	
	/* Instance Variables */
	private ArrayList<Integer> rollList;
	private int score;
}

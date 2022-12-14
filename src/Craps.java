import java.util.Random;

/**
 * Purpose:Create craps class for LO Object Oriented Design
 * 
 */

public class Craps {

	// create fields for the craps game
	private static Random roll = new Random();
	private int dice = 6;
	private int score;

	/**
	 * Purpose: create getter to roll 2 dices
	 * 
	 * @return value of two dices
	 */
	public int rollValue() {
		int roll1 = roll.nextInt(dice) + 1;
		int roll2 = roll.nextInt(dice) + 1;
		this.score = roll1 + roll2;

		return score;

	}

	/**
	 * Purpose: create winning/losing rules for craps game
	 * 
	 * @param int current
	 * @return game result
	 */
	public String rule(int current) {

		if (current == 7 || current == 11) {
			return "You win";
		}

		else if (current == 2 || current == 3 || current == 12) {
			return "You lost";
		}

		else {
			return "reroll";
		}
	}

	/**
	 * Purpose: create rulebook for craps game for user to read
	 * 
	 * @return String rule
	 */

	public String ruleBook2() {
		return "There are two dices, we roll the two dices and add the numbers for each roll.\n"
				+ "There is one roll and two bets, one is the Pass Line Bet, and one is the Don't Pass Bet\n"
				+ "In the Pass Line Bet, you are betting the first roll is a winner.\n"
				+ "You win if the sum of the two dices are 7 or 11, you lose if it is 2, 3, or 12\n"
				+ "If you made the Don't Pass Bet, you win if the rolls are 2, 3, 12, and the losing is 7 or 11\n"
				+ "----------------------------------------------------------------------------------------------";
	}
}

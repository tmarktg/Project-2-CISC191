

/**
 * Purpose: create Blackjack class to create a blackjack object in casino
 */
import java.util.Random;

public class Blackjack {
	// create fields for blackjack game
	private static Random roll = new Random();
	private int r1, r2, rr;
	private int score;
	private String[] suits = { "Spades", "Hearts", "Diamonds", "Clubs" };
	private int[] ranks = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	private String s1, s2;

	/**
	 * Purpose: create getter to get card values for blackjack
	 * 
	 * @return suit and card value
	 */
	public String getDeck() {
		// sets the ranks for card 1 and 2
		this.r1 = ranks[roll.nextInt(10)];
		this.r2 = ranks[roll.nextInt(10)];
		this.s1 = suits[roll.nextInt(4)];
		this.s2 = suits[roll.nextInt(4)];

		return s1 + " " + r1 + " and " + s2 + " " + r2;
	}

	/**
	 * Purpose: create getter for start score of player
	 * 
	 * @return int score
	 */

	public int startScore() {
		this.score = r1 + r2;

		return this.score;
	}

	/**
	 * Purpose: create getter for suits of cards
	 * 
	 * @return suit of card
	 */

	public String getExtra() {
		this.rr = ranks[roll.nextInt(10)];

		return suits[roll.nextInt(4)] + " " + rr;
	}

	/**
	 * Purpose: create getter for adding the value of the cards to blackjack game
	 * 
	 * @return score value
	 */

	public int addScore() {
		return rr;
	}

	/**
	 * Purpose: create winning/losing rules for Blackjack game
	 * 
	 * @param int player
	 * @param int opp
	 * @return game result
	 */

	public String rule(int player, int opp) {
		if (player < opp && player <= 21 && opp <= 21) {
			return "You lost";
		}

		else if (player > 21) {
			return "You lost";
		}

		else if (opp > 21) {
			return "You win";
		}

		return "";
	}

	/**
	 * Purpose: create rulebook for Blackjack game for user to read
	 * 
	 * @return String rule
	 */

	public String ruleBook1() {
		return "Rule: You will get assigned 2 cards\n"
				+ "continue drawing until you get a total value of 16 or higher\n"
				+ "you will lose if your total card value goes above 21\n"
				+ "you will be competing against a computer \n" + "so make sure your score is higher than it!!!\n"
				+ "--------------------------------------";
	}

}

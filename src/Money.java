/**
 * Purpose: create money class to create object in casino
 */
public class Money {

	// create fields for money class
	private int current;

	/**
	 * Purpose: create setter for condition when player loses game
	 * 
	 * @param int current
	 * @param int bet
	 */
	public void setLoseBet(int current, int bet) {
		this.current = current - bet;

	}

	/**
	 * Purpose: create getter for when player loses game
	 * 
	 * @return money lost to player
	 */
	public int getLoseBet() {
		return current;
	}
	
	/**
	 * Purpose: create setter for when player wins bet
	 * 
	 * @param int current
	 * @param int bet
	 */
	public void setWinBet(int current, int bet) {
		this.current = current + bet;

	}

	/**
	 * Purpose: create getter for when player wins bet
	 * @return value of bet when player wins
	 */
	
	public int getWinBet() {
		return current;
	}

	/**
	 * Purpose: prompt user to ask how much they are putting in the casino as a line of credit
	 * @return String how much they're putting in casino
	 */
	public String getStartCredit() {
		return "How much are you entering the casino with? Enter your credit of choice:";
	}
}
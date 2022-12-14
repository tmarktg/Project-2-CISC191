import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Lead Author(s):
 * 
 * @author Quoc Minh Tran
 * @author Mark Truong
 * 
 * Other contributors:  
 * Tony Gaddis: Starting Out With Java, From Control Structures to Objects
 *         Responsibilities of class: Create a project that fulfills all 6 LOs
 * Stack Overflow: https://stackoverflow.com/questions/12828771/how-to-go-back-to-first-if-statement-if-no-choices-are-valid
 * 
 * Version/date: 10/19/2022
 * 
 * Purpose: Create a Casino class for the keyboard input and use
 *         other classes to create logic for game main method: create objects
 *         from classes and keyboard
 */
 
public class Casino
{

	public static void main(String[] args) throws FileNotFoundException 
	{
		new WelcomeFrame();
		
		Scanner keyboard = new Scanner(System.in); // Create a Scanner object to
													// read the system input
		// Create objects needed to run a Casino
		Blackjack card = new Blackjack();
		Craps dice = new Craps();
		Money cred = new Money();
		PrintWriter pWriter = new PrintWriter("receipts.txt"); // Create
																// PrintWriter
																// object to
																// write in to a
																// text file
		// Create variables to hold users input
		int yesorno, bet, credit = 0, current;
		boolean wantToPlayGame = true;

		
		//Loop the try/catch block
		while(wantToPlayGame) {
			try
			{	
				//Get user input and check if it's a valid integer
				yesorno = keyboard.nextInt();
				yesorno = validInput(yesorno, keyboard);

				//Get user credit and check if it's a valid credit
				System.out.println(cred.getStartCredit());
				credit = keyboard.nextInt();
				credit = validCredit(credit, keyboard);

				//Print starting credit into text file
				pWriter.println("You entered the Casino with $" + credit);
				pWriter.println("");
				
				// Continue looping until user opt to leave
				while (wantToPlayGame)
				{
					if (yesorno == 1) //Run Blackjack
					{
						System.out.println(card.ruleBook1());
						//Continue looping while user still has credit to play
						do
						{   
							//Get user credit and check if user have enough credit to bet
							System.out.println("Place bet:");
							bet = keyboard.nextInt();
							bet = validBet(bet, credit, keyboard);

							//Show what card user draws and get a starting score
							System.out.println("You draw " + card.getDeck());
							current = card.startScore();
							System.out.println(
									"Your starting score is: " + current);
							//Loop while the current score is less than 21
							do
							{
								//Get user input and check if it's a valid integer
								System.out.println(
										"Would you like to countinue: 1 for yes , 2 for no");
								yesorno = keyboard.nextInt();
								yesorno = validInput(yesorno, keyboard);

								if (yesorno == 1) //Roll a new card and add it the current score
								{
									System.out.println(
											"You draw " + card.getExtra());
									current += card.addScore();
									System.out.println("Your current score is: "
											+ current);
								}
								else if (yesorno == 2) //End the round and get final score
									System.out.println(
										"Your final score is: " + current);
							} while (current <= 21 && yesorno == 1);

							//Holds the computer score
							int oppCurrent = card.startScore();
							//Add scores to the computer current score until it's bigger than the user's
							do
							{
								card.getExtra();
								oppCurrent += card.addScore();
							} while (oppCurrent <= current);

							//Show results
							if (current <= 21) 
								System.out.println("Opponent final score is: " + oppCurrent);
								System.out.println(card.rule(current, oppCurrent));
								System.out.println("");

							//Print the new current credit after user lost bet
							if (card.rule(current, oppCurrent) == "You lost")
							{
								cred.setLoseBet(credit, bet);
								credit = cred.getLoseBet();
								pWriter.println("Blackjack -$" + bet);
								System.out.println(
										"Your current balance: " + credit);
							}

							//Print the new current credit after user win bet
							if (card.rule(current, oppCurrent) == "You win")
							{
								cred.setWinBet(credit, bet);
								credit = cred.getWinBet();
								pWriter.println("Blackjack +$" + bet);
								System.out.println(
										"Your current balance: " + credit);
							}

							//Ask if user want to play again and check if it's a valid integer
							System.out.println();
							System.out.println(
									"Would you like to continue playing? 1 for yes , 2 for no");
							yesorno = keyboard.nextInt();
							yesorno = validInput(yesorno, keyboard);

						} while (credit != 0 && yesorno == 1);

						//Runs if user has no money left and still wants to play
						if (credit == 0 && yesorno == 1)
						{
							wantToPlayGame = false;
							yesorno = 0;
							System.out.println(
									"You have run out of money to play with!");
						}
						
						//Runs if user has no money left and still doesn't to play
						if (credit == 0 && yesorno == 2)
						{
							wantToPlayGame = false;
							yesorno = 0;
							System.out.println("Thank you for playing.");
						}
						
						//Ask the user if they want to play another game if they still has money
						if (credit != 0 && yesorno == 2)
						{
							System.out.println(
									"Would you like to change game? 2 to change, 3 to leave");
							yesorno = keyboard.nextInt();

							while (yesorno != 2 && yesorno != 3)
							{
								System.out.println(
										"You didn't type in the right number, please type in the correct number");
								yesorno = keyboard.nextInt();
							}
						}
					}

					if (yesorno == 2) //Run Craps
					{
						System.out.println(dice.ruleBook2());
						
						//Continue looping while user still has credit to play
						do
						{
							//Get user credit and check if you have enough credit to bet
							System.out.println("Place bet:");
							bet = keyboard.nextInt();
							bet = validBet(bet, credit, keyboard);

							//Get a start roll
							current = dice.rollValue();
							System.out.println(
									"Your dice roll score is " + current);

							//Loop while user does not have a winning or losing value
							while (dice.rule(current) == "reroll" && yesorno == 1)
							{
								//Get user input and check if it's a valid integer
								System.out.println(
										"Would you like roll again? You neither got a winning or losing score. 1 to roll again 2 to leave");
								yesorno = keyboard.nextInt();
								yesorno = validInput(yesorno, keyboard);

								if (yesorno == 1)
								{
									current = dice.rollValue();
									System.out
											.println("Your dice roll score is " + current);
								}
								if (yesorno == 2)
									System.out.println("Thank you for playing");
							}

							//Print the new current credit after user lost bet
							if (dice.rule(current) == "You lost")
							{
								cred.setLoseBet(credit, bet);
								credit = cred.getLoseBet();
								pWriter.println("Craps -$" + bet);
								System.out.println(dice.rule(current));
								System.out.println(
										"Your current balance: " + credit);
							}

							//Print the new current credit after user win bet
							if (dice.rule(current) == "You won")
							{
								cred.setWinBet(credit, bet);
								credit = cred.getWinBet();
								pWriter.println("Craps +$" + bet);
								System.out.println(dice.rule(current));
								System.out.println(
										"Your current balance: " + credit);
							}
							//Ask if user want to play again and check if it's a valid integer
							System.out.println(
									"Would you like to continue playing? 1 for yes , 2 for no");
							yesorno = keyboard.nextInt();
							yesorno = validInput(yesorno, keyboard);

						} while (credit != 0 && yesorno == 1);

						//Runs if user has no money left and still wants to play
						if (credit == 0 && yesorno == 1) System.out.println(
								"You have run out of money to play with!");
						
						//Runs if user has no money left and still doesn't to play
						if (credit == 0 && yesorno == 2)
							System.out.println("Thank you for playing.");
						
						//Ask the user if they want to play another game if they still has money
						if (credit != 0 && yesorno == 2)
						{
							System.out.println(
									"Would you like to change game? 1 to change, 3 to leave ");
							yesorno = keyboard.nextInt();

							while (yesorno != 1 && yesorno != 3)
							{
								System.out.println(
										"You didn't type in the right number, please type in the correct number");
								yesorno = keyboard.nextInt();
							}
						}
					}

					if (yesorno == 3) //Leave the game
					{
						yesorno = 0;
						System.out.println("Thank you for playing.");
						wantToPlayGame = false;
					}

				}
			}
			catch (InputMismatchException e) // Catch if user input a non-integer value
			{
				keyboard.nextLine();
				System.out.println("Please enter the valid number");
			}
		}
		//Print out final credit in the text file
		pWriter.println();
		pWriter.println("Your final credit is: $" + credit);
		pWriter.println("Thank you for playing.");
		pWriter.close(); //Close file
		keyboard.close(); //Close scanner
	}

	/**
	 * Purpose: Check if user input a valid integer
	 * @param int value
	 * 		  Scanner keyboard
	 * @return value 
	 */
	public static int validInput(int value, Scanner keyboard)
	{
		//Ask the user to input a new value if it is not valid
		while (value != 1 && value != 2)
		{
			System.out.println(
					"You didn't type in the right number, please type in the correct number");

			value = keyboard.nextInt();

		}
		return value;
	}

	/**
	 * Purpose: Check if user input a valid credit
	 * @param int value
	 * 		  Scanner keyboard
	 * @return value  
	 */
	public static int validCredit(int value, Scanner keyboard)
	{
		//Ask the user to input a new value if it is not valid
		while (value <= 0)
		{
			System.out.println(
					"You can not enter the casino with no money, enter the casino with a new credit: ");

			value = keyboard.nextInt();

		}
		return value;
	}

	/**
	 * Purpose: Check if user input a valid bet
	 * @param int bet, credit
	 * 		  Scanner keyboard
	 * @return bet 
	 */
	public static int validBet(int bet, int credit, Scanner keyboard)
	{
		//Ask the user to input a new value if it is not valid
		while (bet > credit)
		{
			System.out.println(
					"You can not bet over your credit, type in a valid bet.");

			bet = keyboard.nextInt();
		}
		return bet;
	}
}

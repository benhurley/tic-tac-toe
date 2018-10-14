// Benjamin Hurley
// TicTacToe.java

import java.util.Scanner;	// simple text scanner for parsing
import java.util.Random;	// library for random number generation

public class TicTacToe
{
	public static void main(String[] args)
	{
		// useful variables
		int currentPlayer = 1;
		boolean playerOneComp = false;
		boolean playerTwoComp = false;
		boolean usage = false;

		// 2-D array for game board
		char [][] gameboard = new char[3][3]; 

		// array to mark used indexes (1-9)
		boolean[] usedNums = new boolean[10];

		// populate with false
		for (int i = 0; i < usedNums.length; i++)
			usedNums[i] = false;

		// set up random number generator
		Random r = new Random();

		// check for computer players
		// defaults to 2 humans until changed

		if (args.length > 0 && args[0].equals("-c") || args.length == 0)
		{
			// computer players present
		   if (args.length == 1)
		   {
				// 2 computer players
				playerOneComp = true;
				playerTwoComp = true;

				System.out.println();
				System.out.println("Player 1 - Computer");
				System.out.println("Player 2 - Computer");
				System.out.println();

		   }
		   else if (args.length > 1 && (args[1].equals("1") || args[1].equals("2")))
		   {
				// one computer player
				if (args[1].equals("1"))
				{
			   		playerOneComp = true;
			   		System.out.println();
			   		System.out.println("Player 1 - Computer");
			   		System.out.println("Player 2 - Keyboard");
			   		System.out.println();
				}
				else if (args[1].equals("2"))
				{
			   		playerTwoComp = true;
			   		System.out.println();
			   		System.out.println("Player 1 - Keyboard");
			   		System.out.println("Player 2 - Computer");
			   		System.out.println();
				}
		   }
		   else if (args.length == 0)
		   {
				// no computer players
			   	System.out.println();
			   	System.out.println("Player 1 - Keyboard");
			   	System.out.println("Player 2 - Keyboard");
			   	System.out.println();
		   }
		   else
		   {
				System.out.println("Usage: " + args);
				usage = true;
		   }
		}
		else
		{
			System.out.println("Usage: " + args);
		    usage = true;
		}

		if (usage != true)
		{

			// input stream
			Scanner input = new Scanner(System.in);

			// fill board with blanks
		  	for (int i = 0; i <= 2; i++)
				for (int j = 0; j <= 2; j++)
					gameboard[i][j] = ' ';

			boolean gameover = false;

	       	// Begin to Play Tic Tac Toe
	       	for (int i = 1; i < 10; i++)
	       	{
		    	boolean outcome = true;
		    	int choice = 0;
		    	int block = 0;

				do
				{
					// Print Board
					System.out.println("Turn #" + i + ":");

					PrintBoard(gameboard);

					if ((playerOneComp == true && currentPlayer == 1) ||
						(playerTwoComp == true && currentPlayer == 2))
					{
					// check if there is a winning position
						choice = PotentialWin(gameboard, usedNums, currentPlayer);

					// check if there is a necessary block
						if (choice == -1)
						{
							block = Block(gameboard, usedNums, currentPlayer);

							if (block == -1)
							{

								// check if middle is open
								if (gameboard[1][1] != 'X' && gameboard[1][1] != 'O')
								{
									choice = 5;
								}
								else
								{
								// pick at random
									do
									{
										choice = r.nextInt(9) + 1;
									} while (usedNums[choice] == true);
								}
							}
							else
							{
								choice = block;
							}
						}
					}
					else
					{
						System.out.print("Player " + currentPlayer + ", please enter a move (1-9): ");
						choice = input.nextInt();
					}

					// See if it is a valid move
					outcome = Play(choice, gameboard, currentPlayer);

					if (outcome == false)
					{
						System.out.println();
						System.out.println("Player " + currentPlayer + " selected: " + choice);
						System.out.println();
						System.out.println("Unable to make that move. Please try again.");
						System.out.println();
					}

				}
				while (outcome == false);

				// if move is valid, continue

				System.out.println();
				System.out.println("Player " + currentPlayer + " selected: " + choice);
				System.out.println();
				usedNums[choice] = true;

				// check to see if any winners
				int winner = ThreeInARow(gameboard, currentPlayer);

				if (winner == 1)
				{
					PrintBoard(gameboard);
					System.out.println();
					System.out.println("Player 1 wins the game!");
					gameover = true;
					break;
				}
				else if (winner == 2)
				{
					PrintBoard(gameboard);
					System.out.println();
					System.out.println("Player 2 wins the game!");
					gameover = true;
					break;
				}

				if (currentPlayer == 1)
					currentPlayer = 2;
				else
					currentPlayer = 1;

			}	// end of for loop

		if (gameover == false)
		{
		   // if you get here, it's a tie
		   PrintBoard(gameboard);
		   System.out.println("The game is a tie!");
		}

		input.close();
		System.out.println();
		System.out.println("Thanks for playing!");

	} // close usage loop;
}

	// function to add new move to gameboard
    public static boolean Play(int decision, char[][] gameboard, int currentPlayer)
    {
		if (decision == 1)
		{
			if (gameboard[0][0] != 'X' && gameboard[0][0] != 'O')
			{
				if (currentPlayer == 1) gameboard[0][0] = 'X';
				else gameboard[0][0] = 'O';

				return true;
			}
			else
				return false;
		}
		else if (decision == 2)
		{
	   		if (gameboard[0][1] != 'X' && gameboard[0][1] != 'O')
	   		{
	      		if (currentPlayer == 1) gameboard[0][1] = 'X';
	      		else gameboard[0][1] = 'O';
		
				return true;
	   		}
	   		else
				return false;
		}
		else if (decision == 3)
		{
	   		if (gameboard[0][2] != 'X' && gameboard[0][2] != 'O')
	   		{
	      		if (currentPlayer == 1) gameboard[0][2] = 'X';
	     		else gameboard[0][2] = 'O';

				return true;
	   		}
	   		else
				return false;
		}
		else if (decision == 4)
		{
	   		if (gameboard[1][0] != 'X' && gameboard[1][0] != 'O')
	   		{
	      		if (currentPlayer == 1) gameboard[1][0] = 'X';
	      		else gameboard[1][0] = 'O';

				return true;
	   		}
	   		else
				return false;
		}
		else if (decision == 5)
		{
	   		if (gameboard[1][1] != 'X' && gameboard[1][1] != 'O')
	   		{
	      		if (currentPlayer == 1) gameboard[1][1] = 'X';
	      		else gameboard[1][1] = 'O';

				return true;
	   		}
	   		else
				return false;
		}
		else if (decision == 6)
		{
	   		if (gameboard[1][2] != 'X' && gameboard[1][2] != 'O')
	   		{
	      		if (currentPlayer == 1) gameboard[1][2] = 'X';
	      		else gameboard[1][2] = 'O';

				return true;
	   		}
	   		else
				return false;
		}
		else if (decision == 7)
		{
	   		if (gameboard[2][0] != 'X' && gameboard[2][0] != 'O')
	  		{
	      		if (currentPlayer == 1) gameboard[2][0] = 'X';
	      		else gameboard[2][0] = 'O';

				return true;
	   		}
	   		else
				return false;
		}
		else if (decision == 8)
		{
	   		if (gameboard[2][1] != 'X' && gameboard[2][1] != 'O')
	   		{
	      		if (currentPlayer == 1) gameboard[2][1] = 'X';
	      		else gameboard[2][1] = 'O';

				return true;
	  		}
	   		else
				return false;
		}
		else if (decision == 9)
		{
	   		if (gameboard[2][2] != 'X' && gameboard[2][2] != 'O')
	   		{
	      		if (currentPlayer == 1) gameboard[2][2] = 'X';
	      		else gameboard[2][2] = 'O';

				return true;
	   		}
	   		else
				return false;
		}
		else
		{
			return false;
		}
    }
	// check for a win
    public static int ThreeInARow(char[][] gameboard, int currentPlayer)
    {
		if (currentPlayer == 1)
		{
			// X
			// horizontal
	   		if ((gameboard[0][0] == 'X' && gameboard[0][1] == 'X' && gameboard[0][2] == 'X') ||
				(gameboard[1][0] == 'X' && gameboard[1][1] == 'X' && gameboard[1][2] == 'X') ||
				(gameboard[2][0] == 'X' && gameboard[2][1] == 'X' && gameboard[2][2] == 'X'))
				return 1;
			// vertical
	   		else if ((gameboard[0][0] == 'X' && gameboard[1][0] == 'X' && gameboard[2][0] == 'X') ||
				(gameboard[0][1] == 'X' && gameboard[1][1] == 'X' && gameboard[2][1] == 'X') ||
				(gameboard[0][2] == 'X' && gameboard[1][2] == 'X' && gameboard[2][2] == 'X'))
				return 1;
			// diagonal
	   		else if ((gameboard[0][0] == 'X' && gameboard[1][1] == 'X' && gameboard[2][2] == 'X') ||
				(gameboard[0][2] == 'X' && gameboard[1][1] == 'X' && gameboard[2][0] == 'X'))
				return 1;
		}
		else if (currentPlayer == 2)
		{
			// O
			// horizontal
	   		if ((gameboard[0][0] == 'O' && gameboard[0][1] == 'O' && gameboard[0][2] == 'O') ||
				(gameboard[1][0] == 'O' && gameboard[1][1] == 'O' && gameboard[1][2] == 'O') ||
				(gameboard[2][0] == 'O' && gameboard[2][1] == 'O' && gameboard[2][2] == 'O'))
				return 2;
			// vertical
	   		else if ((gameboard[0][0] == 'O' && gameboard[1][0] == 'O' && gameboard[2][0] == 'O') ||
				(gameboard[0][1] == 'O' && gameboard[1][1] == 'O' && gameboard[2][1] == 'O') ||
				(gameboard[0][2] == 'O' && gameboard[1][2] == 'O' && gameboard[2][2] == 'O'))
				return 2;
			// diagonal
	   		else if ((gameboard[0][0] == 'O' && gameboard[1][1] == 'O' && gameboard[2][2] == 'O') ||
				(gameboard[0][2] == 'O' && gameboard[1][1] == 'O' && gameboard[2][0] == 'O'))
				return 2;
		}

	return 0;

    }

	// check for a next-move-win
    public static int PotentialWin(char[][] gameboard, boolean[] usedNums, int currentPlayer)
    {
		if (currentPlayer == 1)
		{  	// horizontal
	   		if (gameboard[0][0] == 'X' && gameboard[0][1] == 'X')
				if (usedNums[3] == false)
				return 3;
	   		if (gameboard[1][0] == 'X' && gameboard[1][1] == 'X')
				if (usedNums[6] == false)
				return 6;
	   		if (gameboard[2][0] == 'X' && gameboard[2][1] == 'X')
				if (usedNums[9] == false)
				return 9;
	   		if (gameboard[0][0] == 'X' && gameboard[0][2] == 'X')
				if (usedNums[2] == false)
				return 2;
	   		if (gameboard[1][0] == 'X' && gameboard[1][2] == 'X')
				if (usedNums[5] == false)
				return 5;
	   		if (gameboard[2][0] == 'X' && gameboard[2][2] == 'X')
				if (usedNums[8] == false)
				return 8;
	   		if (gameboard[0][1] == 'X' && gameboard[0][2] == 'X')
				if (usedNums[1] == false)
				return 1;
	   		if (gameboard[1][1] == 'X' && gameboard[1][2] == 'X')
				if (usedNums[4] == false)
				return 4;
	   		if (gameboard[2][1] == 'X' && gameboard[2][2] == 'X')
				if (usedNums[7] == false)
				return 7;

	   		// vertical
	    	if (gameboard[0][0] == 'X' && gameboard[1][0] == 'X')
				if (usedNums[7] == false)
				return 7;
	    	if (gameboard[0][1] == 'X' && gameboard[1][1] == 'X')
				if (usedNums[8] == false)
				return 8;
	    	if (gameboard[0][2] == 'X' && gameboard[1][2] == 'X')
				if (usedNums[9] == false)
				return 9;
	    	if (gameboard[0][0] == 'X' && gameboard[2][0] == 'X')
				if (usedNums[4] == false)
				return 4;
	    	if (gameboard[0][1] == 'X' && gameboard[2][1] == 'X')
				if (usedNums[5] == false)
				return 5;
	    	if (gameboard[0][2] == 'X' && gameboard[2][2] == 'X')
				if (usedNums[6] == false)
				return 6;
	    	if (gameboard[1][0] == 'X' && gameboard[2][0] == 'X')
				if (usedNums[1] == false)
				return 1;
	    	if (gameboard[1][1] == 'X' && gameboard[2][1] == 'X')
				if (usedNums[2] == false)
				return 2;
	    	if (gameboard[1][2] == 'X' && gameboard[2][2] == 'X')
				if (usedNums[3] == false)
				return 3;

	    	//diagonal
	    	if (gameboard[0][0] == 'X' && gameboard[1][1] == 'X')
				if (usedNums[9] == false)
				return 9;
	    	if (gameboard[0][0] == 'X' && gameboard[2][2] == 'X')
				if (usedNums[5] == false)
				return 5;
	    	if (gameboard[1][1] == 'X' && gameboard[2][2] == 'X')
				if (usedNums[1] == false)
				return 1;
	    	if (gameboard[1][1] == 'X' && gameboard[2][0] == 'X')
				if (usedNums[3] == false)
				return 3;
	    	if (gameboard[0][2] == 'X' && gameboard[2][0] == 'X')
				if (usedNums[5] == false)
				return 5;
	    	if (gameboard[0][2] == 'X' && gameboard[1][1] == 'X')
				if (usedNums[7] == false)
				return 7;
		}
		else if (currentPlayer == 2)
		{
			// hortizontal
			if (gameboard[0][0] == 'O' && gameboard[0][1] == 'O')
				if (usedNums[3] == false)
				return 3;
			if (gameboard[1][0] == 'O' && gameboard[1][1] == 'O')
				if (usedNums[6] == false)
				return 6;
			if (gameboard[2][0] == 'O' && gameboard[2][1] == 'O')
				if (usedNums[9] == false)
				return 9;
			if (gameboard[0][0] == 'O' && gameboard[0][2] == 'O')
				if (usedNums[2] == false)
				return 2;
			if (gameboard[1][0] == 'O' && gameboard[1][2] == 'O')
				if (usedNums[5] == false)
				return 5;
			if (gameboard[2][0] == 'O' && gameboard[2][2] == 'O')
				if (usedNums[8] == false)
				return 8;
			if (gameboard[0][1] == 'O' && gameboard[0][2] == 'O')
				if (usedNums[1] == false)
				return 1;
			if (gameboard[1][1] == 'O' && gameboard[1][2] == 'O')
				if (usedNums[4] == false)
				return 4;
			if (gameboard[2][1] == 'O' && gameboard[2][2] == 'O')
				if (usedNums[7] == false)
				return 7;

			// vertical
			if (gameboard[0][0] == 'O' && gameboard[1][0] == 'O')
				if (usedNums[7] == false)
				return 7;
			if (gameboard[0][1] == 'O' && gameboard[1][1] == 'O')
				if (usedNums[8] == false)
				return 8;
			if (gameboard[0][2] == 'O' && gameboard[1][2] == 'O')
				if (usedNums[9] == false)
				return 9;
			if (gameboard[0][0] == 'O' && gameboard[2][0] == 'O')
				if (usedNums[4] == false)
				return 4;
			if (gameboard[0][1] == 'O' && gameboard[2][1] == 'O')
				if (usedNums[5] == false)
				return 5;
			if (gameboard[0][2] == 'O' && gameboard[2][2] == 'O')
				if (usedNums[6] == false)
				return 6;
			if (gameboard[1][0] == 'O' && gameboard[2][0] == 'O')
				if (usedNums[1] == false)
				return 1;
			if (gameboard[1][1] == 'O' && gameboard[2][1] == 'O')
				if (usedNums[2] == false)
				return 2;
			if (gameboard[1][2] == 'O' && gameboard[2][2] == 'O')
				if (usedNums[3] == false)
				return 3;

			//diagonal
			if (gameboard[0][0] == 'O' && gameboard[1][1] == 'O')
				if (usedNums[9] == false)
				return 9;
			if (gameboard[0][0] == 'O' && gameboard[2][2] == 'O')
				if (usedNums[5] == false)
				return 5;
			if (gameboard[1][1] == 'O' && gameboard[2][2] == 'O')
				if (usedNums[1] == false)
				return 1;
			if (gameboard[1][1] == 'O' && gameboard[2][0] == 'O')
				if (usedNums[3] == false)
				return 3;
			if (gameboard[0][2] == 'O' && gameboard[2][0] == 'O')
				if (usedNums[5] == false)
				return 5;
			if (gameboard[0][2] == 'O' && gameboard[1][1] == 'O')
				if (usedNums[7] == false)
				return 7;
		}

		return -1;
    }

	// block a move that would make you lose
    public static int Block(char[][] gameboard, boolean[] usedNums, int currentPlayer)
    {
		if (currentPlayer == 1)
			return PotentialWin(gameboard, usedNums, 2);
		else
			return PotentialWin(gameboard, usedNums, 1);
    }

    public static void PrintBoard(char[][] gameboard)
    {
		// print game board, ask for next move

		System.out.println();
		System.out.println("         Game Board			(Positions)");
		System.out.println();
		System.out.printf("	 %c | %c | %c			  1 | 2 | 3 \n", gameboard[0][0], gameboard[0][1], gameboard[0][2]);
		System.out.printf("	-----------			 -----------\n");
		System.out.printf("	 %c | %c | %c			  4 | 5 | 6 \n", gameboard[1][0], gameboard[1][1], gameboard[1][2]);
		System.out.printf("	-----------			 -----------\n");
		System.out.printf("	 %c | %c | %c			  7 | 8 | 9 \n", gameboard[2][0], gameboard[2][1], gameboard[2][2]);
		System.out.println();
    }

} // end of class block

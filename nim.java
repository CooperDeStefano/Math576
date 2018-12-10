//MATH 576 project by Cooper DeStefano & Jacob Strom
//This game simulates the game Nim

import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class nim{
	public static void main(String[] args) {
		boolean player1Win = false; //condition if the player wins
		boolean CPUWin = false; //condition if the computer wins
		Scanner keyboard = new Scanner(System.in); //for taking in user input
		
		Random rand = new Random(); //for generating random numbers
		int numHeaps = rand.nextInt(10) + 3; //Randomly generating the number of heaps in the nim game
		
		int heaps[] = new int[numHeaps]; //creating the heaps
		
		for(int i = 0; i < heaps.length; i++) //for each heap
		{
			heaps[i] = rand.nextInt(10) + 1; //generate a random number of blocks in the heap
		}
		
		for(int i = 0; i < heaps.length; i++)
		{
			System.out.print(heaps[i] + "\t");//prints out the heaps
		}
		System.out.println();
		
		int turn = rand.nextInt(3)%2; //randomly chooses whether the player or computer goes first
		
		while(!player1Win && !CPUWin) //the game goes while neither player has won
		{	
			if(turn%2 == 0) // player turn
			{
				boolean done = false; //checking whether the player has made a correct selection
				int picked = 0; //the column picked
				int remove = 0; //how many to remove
				while (!done) {	//while the player has not selected a move
					System.out.println("Pick a heap");
					picked = keyboard.nextInt(); //the player selects a heap
					picked--; //the number of the heap is converted into a computer science form (where the first heap is 0)
					if (picked >= 0 && picked < heaps.length && heaps[picked] != 0) { //if the heap is one that exists and is non-zero
						done = true;
					}
				}
				done = false;
				while(!done) {
					System.out.println("How many do you want to remove?");
					remove = keyboard.nextInt();
					if (remove > 0 && remove <= heaps[picked]) { //if the player has selected a number that can be removed
						done = true;
					}
				}

				heaps[picked] = heaps[picked] - remove; //the selected heap has the selected numbers removed
			}		
			else if(turn%2 == 1) //CPU turn
			{
				int[] temp = new int[heaps.length]; //creates a temporary copy of the heap, so that the computer can check possible moves
				for (int i = 0; i < heaps.length; i++) {
					temp[i] = heaps[i];
				}
				int bestHeap = -1; //the best heap to select
				int bestMove = -1; //the best amount of blocks to remove
				int nimmed = 15; //the worst possible nim value this game could generate
				for(int i = 0; i < temp.length; i++) //runs through the columns
				{
					for(int j = 1; j <= heaps[i]; j++) //checks each possible removal of heaps
					{
						temp[i] = temp[i] - j; //temporarily removes j blocks from heap i
						if(nimmed > nimAdd(temp)) //if the nim value of that move is the best so far, update the best move
						{
							nimmed = nimAdd(temp); //update best nim value to new value
							bestHeap = i; //update best heap to heap i
							bestMove = j; //update best move to j blocks
						}
						temp[i] = heaps[i]; //reset the temporary heap to the original
					}
				}
				heaps[bestHeap] = heaps[bestHeap] - bestMove; //the computer will select the best possible move
			}
			
			for(int i = 0; i < heaps.length; i++)//reprint the game
			{
				System.out.print(heaps[i] + "\t");
			}
			System.out.println();
			
			for(int i = 0; i < heaps.length; i++)//checks whether the game has finished
			{
				if(heaps[i] != 0)//if a nonzero column is found the game is not over
				{
					player1Win = false;
					CPUWin = false;
					break;
				}
				if(turn%2 == 0) //if the game is over and it is player 1's turn, the game ends and you win
				{
					player1Win = true;
				}
				else //if the game is over and it is the CPU's turn, the game ends and you lose
				{
					CPUWin = true;
				}
			}
			turn = (turn+1)%2; //changes whose turn it is.
		}
		if(player1Win) //End statement for when you win
		{
			System.out.println("You win!");
		}
		else{ //End statement for when you lose
			System.out.println("You lose!");
		}
	}
	
	public static int nimAdd(int a, int b) { //function for nim addition of two numbers
		String aString = toBinary(a); //converts the two numbers to binary strings
		String bString = toBinary(b);

		String retString = ""; //the string that will be returned

		if (aString.length() < bString.length()) { //selects aString to be the longer string
			String temp = aString;
			aString = bString;
			bString = temp;
		}
		
		int aPos = 0; //the position in the string that is being checked
		int bPos = 0;
		
		/*does nim addition based on the length of the shorter string
		The beginning of the longer string will remain the same
		*/
		for (int i = 0; i < bString.length(); i++) {
			aPos = aString.length() -1 -i;
			bPos = bString.length() -1 -i;
			String val = nand(aString.charAt(aPos), bString.charAt(bPos)); //if the values are the same, set to 0, if different, 1
			retString = val + retString; //constructs the new binary value
		}
		retString = aString.substring(0, aPos) + retString; //adds the beginning of the longer string, as that part has not changed
		
		return toDecimal(retString); //converts the return string to a decimal value
	}
	
	public static int nimAdd(int[] heaps) //function for nim addition with an array of numbers
	{
		int retVal = 0; //the value to be returned
		for (int i = 0; i < heaps.length; i++) { //runs through the array, doing nimAdd for all the numbers
			retVal = nimAdd(retVal, heaps[i]);
		}
		return retVal; //returns the new nim value of the heap
	}
	
	public static String nand(char a, char b) {
		if (a == b) { //if a and b are the same (0,0) or (1,1), return 0
			return "0";
		}
		else { //if a and b are different, (0,1) or (1,0), return 1
			return "1";
		}
	}
	
	public static String toBinary(int a) { //converts decimal numbers to binary strings
		String retVal = ""; //the value to be returned
		if (a == 0) { //if the number is zero, return 0
			return "0";
		}
		while ( a > 0 ) { //if the number is more than zero, continue
			int t = a % 2; //gets the number mod 2
			retVal = t + retVal; //add that binary number to the string
			a = a/2; //divides a in half
		}
		return retVal; //returns the binary string
	}
	
	public static int toDecimal(String a) { // converts binary strings to decimal
		if(a.isEmpty()) { //if the string is empty, return 0
			return 0;
		}
		else { //if the string is not empty convert to decimal
			return Integer.parseInt(a, 2);
		}
	}
}
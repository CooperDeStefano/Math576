import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class game{
	public static void main(String[] args) {
		boolean player1Win = false;
		boolean player2Win = false;
		Scanner keyboard = new Scanner(System.in);
		
		int n = 0;
		System.out.println("Please enter a size for the game");
		n = keyboard.nextInt();
		String[] field = new String[n];
		for(int i = 0; i < field.length; i++)
		{
			field[i] = "_ ";
		}
		
		System.out.println();
		for(int i = 0; i < n; i++)
		{
			System.out.print(field[i]);
		}
		System.out.println();
		for(int i = 0; i < n; i++)
		{
			System.out.print(i+1 + " ");
		}
		System.out.println();
		
		int x = 0;
		while(!player1Win && !player2Win)
		{	
			int s = 0;
			if(x%2 == 0) // if it is the player's turn
			{
				System.out.println();
				System.out.println("Please enter your selection");
				do{
					s = keyboard.nextInt();
					if(field[s-1] == "x ")
					{
						System.out.println("This space is taken. Please select another");
					}
				}while(field[s-1] == "x ");
				field[s-1] = "x ";
			}
			else{ //if it is the computer's turn;
				s = AIMove(field);
				
				field[s] = "x ";
			}
			
			System.out.println();
			for(int i = 0; i < n; i++)
			{
				System.out.print(field[i]);
			}
			System.out.println();
			for(int i = 0; i < n; i++)
			{
				System.out.print(i+1 + " ");
			}
			System.out.println();
			
			if(threeInARow(field) && x%2 == 0)
			{
				player1Win = true;
				System.out.println("You won!");
			}
			else if(threeInARow(field) && x%2 == 1)
			{
				player2Win = true;
				System.out.println("The computer won!");
			}
			
			
			x = (x+1)%2;
		}
	}
	
	public static boolean threeInARow(String[] x)
	{
		int check = 0;
		for(int i = 0; i < x.length; i++)
		{
			if(x[i] == "x ")
			{
				check++;
			}
			else
			{
				check = 0;
			}
			if(check >= 3)
			{
				return true;
			}
		}
		return false;
	}
	
	public static int AIMove(String[] x)
	{
		int selection = twoInARow(x);
		if(selection > 0)
		{
			return selection;
		}
		
		selection = gap(x);
		if(selection > 0)
		{
			return selection;
		}
		
		for(int i = 0; i < x.length; i = i+3)
		{
			if(x[i] == "_ ")
			{
				return i;
			}
		}
		
		return 0;
	}
	int gamevalue[] = {0,0,1,1,1,2,2,0,3,3,1,1,1,0,4,3,3,3,2,2,2,4,4,0,5,5,2,2,2,3,3,0,5}; //length 33
	public static int twoInARow(String[] x)
	{
		int check = 0;
		for(int i = 0; i < x.length; i++)
		{
			if(x[i] == "x ")
			{
				check++;
			}
			else
			{
				check = 0;
			}
			if(check >= 2 && i-2 > 0)
			{
				return i-2;
			}
			else if(check >= 2 && i+1 < x.length)
			{
				return i+1;
			}
		}
		return -1;
	}
	
	public static int gap(String[] x)
	{
		int check = 0;
		for(int i = 2; i < x.length-2; i++)
		{
			if(x[i-2] == "x " && x[i] == "x ")
			{
				return i-1;
			}
			else if(x[i+2] == "x " && x[i] == "x ")
			{
				return i+1;
			}
		}
		return -1;
	}
	
	public int nimAdd(int a, int b) {
		String aString = toBinary(a);
		String bString = toBinary(b);
		String retString = ""
		if (aString.length < bString.length) {
			String temp = aString;
			aString = bString;
			bString = temp;
		}
		int aPos = 0;
		int bPos = 0;
		for (int i = 0; i < bString.length; i++) {
			aPos = aString.length -1 -i;
			bPos = bString.length -1 -i;
			String val = nand(aString.charAt(aPos), bString.charAt(bPos));
			retString = val + retString;
		}
		retString = aString.substring(0, aPos);
		return toDecimal(retString);
	}
	
	public String nand(char a, char b) {
		if (a == b) {
			return "0";
		}
		else {
			return "1";
		}
	}
	
	public String toBinary(int a) {
		String retVal = "";
		while ( a > 0 ) {
			int t = a % 2;
			retVal = retVal + t;
			a = a/2;
		}
		return retVal;
	}
	
	public int toDecimal(String a) {
		return Integer.parseInt(a, 2);
	}
	
}

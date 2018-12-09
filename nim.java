import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class nim{
	public static void main(String[] args) {
		boolean player1Win = false;
		boolean CPUWin = false;
		Scanner keyboard = new Scanner(System.in);
		
		Random rand = new Random();
		int numHeaps = rand.nextInt(10) + 1;
		
		int heaps[] = new int[numHeaps];
		
		for(int i = 0; i < heaps.length; i++)
		{
			heaps[i] = rand.nextInt(10) + 1;
		}
		
		for(int i = 0; i < heaps.length; i++)
		{
			System.out.print(heaps[i] + "\t");
		}
		System.out.println();
		
		int x = rand.nextInt();
		//int x = 1;
		while(!player1Win && !CPUWin)
		{	
			if(x%2 == 0) // player turn
			{
				boolean done = false;
				int picked = 0;
				int remove = 0;
				while (!done) {	
					System.out.println("Pick a heap");
					picked = keyboard.nextInt();
					picked--;
					if (picked >= 0 && picked < heaps.length && heaps[picked] != 0) {
						done = true;
					}
				}
				done = false;
				while(!done) {
					System.out.println("How many do you want to remove?");
					remove = keyboard.nextInt();
					if (remove > 0 && remove <= heaps[picked]) {
						done = true;
					}
				}

				heaps[picked] = heaps[picked] - remove;
			}
			else if(x%2 == 1) //CPU turn
			{
				int[] temp = new int[heaps.length];
				for (int i = 0; i < heaps.length; i++) {
					temp[i] = heaps[i];
				}
				int bestHeap = -1;
				int bestMove = -1;
				int nimmed = nimAdd(heaps);
				//TODO need to do something if 0 game
				for(int i = 0; i < temp.length; i++)
				{
					for(int j = 1; j <= heaps[i]; j++)
					{

						/*
						System.out.print("look here\t");
						for(int k = 0; k < heaps.length; k++)
						{
							
							System.out.print(heaps[k] + "\t");
						}
						System.out.println();
						*/
						temp[i] = temp[i] - j;
						nimmed = nimAdd(temp);
						if(nimmed == 0)
						{
							bestHeap = i;
							bestMove = j;
							//System.out.println("The best move is column " + bestHeap + ". The best move is " + bestMove);
						}
						temp[i] = heaps[i];
					}
				}
				heaps[bestHeap] = heaps[bestHeap] - bestMove;
			}
			for(int i = 0; i < heaps.length; i++)
			{
				System.out.print(heaps[i] + "\t");
			}
			System.out.println();
			for(int i = 0; i < heaps.length; i++)
			{
				if(heaps[i] != 0)
				{
					player1Win = false;
					CPUWin = false;
					break;
				}
				if(x%2 == 0)
				{
					player1Win = true;
				}
				else
				{
					CPUWin = true;
				}
			}
			x = (x+1)%2;
		}
		if(player1Win)
		{
			System.out.println("You win!");
		}
		else{
			System.out.println("You lose!");
		}
	}
	public static int nimAdd(int a, int b) {
		String aString = toBinary(a);
		String bString = toBinary(b);
		//System.out.println("a = " + a + " b = " + b);
		String retString = "";
		if (aString.length() < bString.length()) {
			String temp = aString;
			aString = bString;
			bString = temp;
		}
		int aPos = 0;
		int bPos = 0;
		for (int i = 0; i < bString.length(); i++) {
			aPos = aString.length() -1 -i;
			bPos = bString.length() -1 -i;
			String val = nand(aString.charAt(aPos), bString.charAt(bPos));
			retString = val + retString;
		}
		retString = aString.substring(0, aPos) + retString;
		//System.out.println("retString = " + retString);
		return toDecimal(retString);	
	}
	
	public static int nimAdd(int[] heaps)
	{
		int retVal = 0;
		for (int i = 0; i < heaps.length; i++) {
			retVal = nimAdd(retVal, heaps[i]);
			//System.out.println("retVal = " + retVal);
		}
		return retVal;
	}
	
	public static String nand(char a, char b) {
		if (a == b) {
			return "0";
		}
		else {
			return "1";
		}
	}
	
	public static String toBinary(int a) { 
		String retVal = "";
		if (a == 0) {
			return "0";
		}
		while ( a > 0 ) {
			int t = a % 2;
			retVal = t + retVal;	
			a = a/2;
		}
		return retVal;
	}
	
	public static int toDecimal(String a) {
		if(a.isEmpty()) {
			return 0;
		}
		else {
			return Integer.parseInt(a, 2);
		}
	}
}
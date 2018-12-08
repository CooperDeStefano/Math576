import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class nim{
	public static void main(String[] args) {
		boolean player1Win = false;
		boolean CPUWin = false;
		Scanner keyoard = new Scanner(System.in);
		
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
		while(!player1Win && !CPUWin)
		{	
			if(x%2 == 0) // player turn
			{
				System.out.println("Pick a heap");
				int picked = keyboard.nextInt();
				//TODO add constraints
				System.out.println("How many do you want to remove?");
				int remove = keyboard.nextInt();
				//TODO add constraints
				heaps[picked] = heaps[picked] - remove;
			}
			
			if(x%2 == 1) //CPU turn
			{
				int[] temp = heaps;
				int bestHeap = -1;
				int bestMove = -1;
				int nimmed = nimAdd(heaps);
				
				for(int i = 0; i < temp.length; i++)
				{
					for(int j = 1; j <= heaps[i]; j++)
					{
						temp[i] = temp[i] - j;
						nimmed = nimAdd(temp);
						if(nimmed == 0)
						{
							bestHeap = i;
							bestMove = j;
						}
						temp[i] = heaps[i];
					}
				}
				heaps[bestHeap] = heaps[bestHeap] - bestMove;
			}
			
			x = (x+1)%2;
		}
	}
	public int nimAdd(int a, int b) {
		String aString = toBinary(a);
		String bString = toBinary(b);
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
		return toDecimal(retString);	
	}
	
	public int nimAdd(int[] heaps)
	{
		int retVal = 0;
		for (int i = 0; i < heaps.length; i++) {
			retVal = nimAdd(retVal, heaps[i]);
		}
		return retVal;
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
			retVal = t + retVal;	
			a = a/2;
		}
		return retVal;
	}
	
	public int toDecimal(String a) {
		return Integer.parseInt(a, 2);
	}
}

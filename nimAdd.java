import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class nimAdd{
	public static void main(String[] args) {
		System.out.println(nimAdd(0,2));
	}
	
	public static int nimAdd(int a, int b) {
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
		System.out.println("retString = " + retString);
		return toDecimal(retString);	
	}
	
	public static int nimAdd(int[] heaps)
	{
		int retVal = 0;
		for (int i = 0; i < heaps.length; i++) {
			retVal = nimAdd(retVal, heaps[i]);
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
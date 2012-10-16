package games;

import java.util.Random;

public class Dice {
	static Random	r	= new Random();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public static int roll(int iCount, int iSides) {
		int iRoll;
		int iSum = 0;
		for (int i = 0; i < iCount; i++) {
			iRoll = r.nextInt(iSides) + 1;
			iSum += iRoll;
		}
		return iSum;
	}
}

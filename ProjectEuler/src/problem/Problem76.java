package problem;

import extras.Sums;

public class Problem76 {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Sums s = new Sums(100);
		System.out.println("It's: " + (s.allSumsUnder(100, 100) - 1));
	}
}

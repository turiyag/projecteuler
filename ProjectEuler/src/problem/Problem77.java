package problem;

import specialnumbers.Sums;

public class Problem77 {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Sums s = new Sums(100);
		int iFirst;
		for (iFirst = 1; s.allPrimeSumsUnder(iFirst, iFirst) < 5000; iFirst++) {
			// Do nothing
		}
		System.out.println("It's: " + iFirst);
	}
}

package problem;

import java.math.BigInteger;

import specialnumbers.BigSums;


public class Problem78 {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BigSums bs = new BigSums(50000000);
		for (int i = 1; i < 500000; i++) {
			if (bs.allSumsUnder(i).mod(BigInteger.valueOf(1000000)).equals(BigInteger.ZERO)) {
				System.out.println("It's " + i);
				return;
			}
		}
	}
}

package problem;

import specialnumbers.PythagoreanTriples;

public class Problem75 {
	
	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		int iIndex;
		int iMult;
		int iSum = 0;
		final PythagoreanTriples pt = new PythagoreanTriples(1500000);
		final int[] iaPrimeLengths = pt.getPrimitiveLengths();
		final int[] iaMultLens = new int[1500000 + 1];
		for (iIndex = 0; iIndex <= 1500000; iIndex++) {
			if (iaPrimeLengths[iIndex] > 0) {
				for (iMult = 1; iIndex * iMult <= 1500000; iMult++) {
					iaMultLens[iIndex * iMult] += iaPrimeLengths[iIndex];
				}
			}
		}
		
		for (iIndex = 0; iIndex <= 1500000; iIndex++) {
			if (iaMultLens[iIndex] == 1) {
				iSum++;
			}
		}
		System.out.println("Its: " + iSum);
	}
	
}

//import java.math.BigInteger;
//import java.util.Random;

public class MainClass {
	static Primes p;
	static long[] laSquares = new long[1500001];
	static int[] iaCount = new int[1500001];

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// PrimeClient pc = new PrimeClient(Primes.PRIME_200SEC);
		// Primes p = new Primes(pc.GetPrimes());

		long lNum = 0;
		for (int i = 1; i <= 1500000; i++) {
			lNum++;
			laSquares[i] = lNum * lNum;
		}
		int iSqrt;
		int iLim;
		for (int i = 1; i <= 1500000; i++) {
			iLim = i;
			for (int j = 1; j < iLim; j++) {
				iSqrt = (int) Math.sqrt(laSquares[i] - laSquares[j]);
				if (laSquares[iSqrt] == laSquares[i] - laSquares[j]) {
					iaCount[i + j + iSqrt]++;
					iLim = iSqrt;
				}
			}
			if ((i % 10000) == 0) {
				System.out.println(i);
			}
		}
		int iSum = 0;
		for (int i = 1; i <= 1500000; i++) {
			if (iaCount[i] == 1) {
				iSum++;
			}
		}
		System.out.println(iSum);
		// for (int i = 2; i <= 12000; i++) {
		// bSum = bSum.add(NumHelp.itob(p.Totient(i)));
		// }
		// System.out.println(bSum.toString());
		// // System.out.println(iSum);
	}

}

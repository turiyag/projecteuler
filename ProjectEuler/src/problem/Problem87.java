package problem;

import primenumber.Primes;

public class Problem87 {
	final static int	MAX_SUM	= 50000000;
	static Primes		_p;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean[] baOn = new boolean[50000000];
		_p = new Primes(MAX_SUM);
		
		int iCount = 0;
		
		for (int iZ = 0; !tooHigh(0, 0, iZ); iZ++) {
			for (int iY = 0; !tooHigh(0, iY, iZ); iY++) {
				for (int iX = 0; !tooHigh(iX, iY, iZ); iX++) {
					baOn[calcVal(iX, iY, iZ)] = true;
					// System.out.println(_p.getPrime(iX) + "^2 + " + _p.getPrime(iY) + "^3 + " + _p.getPrime(iZ) + "^4 = " + calcVal(iX, iY, iZ));
				}
			}
		}
		for (int i = 0; i < MAX_SUM; i++) {
			if (baOn[i]) {
				iCount++;
			}
		}
		System.out.println(iCount);
	}
	
	private static boolean tooHigh(int iX, int iY, int iZ) {
		return calcVal(iX, iY, iZ) >= MAX_SUM;
	}
	
	private static int calcVal(int iX, int iY, int iZ) {
		int iX2 = _p.getPrime(iX);
		iX2 *= iX2;
		int iY3 = _p.getPrime(iY);
		iY3 *= iY3 * iY3;
		int iZ4 = _p.getPrime(iZ);
		iZ4 *= iZ4 * iZ4 * iZ4;
		return iX2 + iY3 + iZ4;
	}
}

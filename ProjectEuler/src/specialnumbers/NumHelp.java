package specialnumbers;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumHelp {
	static BigInteger[]	_laFactorial;
	
	public NumHelp() {
		
	}
	
	public static BigInteger itob(final int i) {
		return BigInteger.valueOf(i);
	}
	
	public static BigInteger ltob(final long l) {
		return BigInteger.valueOf(l);
	}
	
	public static BigInteger choose(final int n, final int r) {
		return factorial(n).divide(factorial(r).multiply(factorial(n - r)));
	}
	
	public static BigInteger factorial(final int i) {
		if (_laFactorial[i] != null) {
			return _laFactorial[i];
		} else if (i == 0) {
			_laFactorial[i] = itob(1);
			return _laFactorial[i];
		} else {
			_laFactorial[i] = factorial(i - 1).multiply(itob(i));
			return _laFactorial[i];
		}
	}
	
	public static int DigitSum(final String s) {
		final char[] c = s.toCharArray();
		int iRet = 0;
		for (final char element : c) {
			iRet += Character.getNumericValue(element);
		}
		return iRet;
	}
	
	/**
	 * Compute the square root of x to a given scale, x >= 0. Use Newton's algorithm.
	 * 
	 * @param x
	 *            the value of x
	 * @param scale
	 *            the desired scale of the result
	 * @return the result value
	 */
	public static BigDecimal sqrt(BigDecimal x, int scale) {
		// Check that x >= 0.
		if (x.signum() < 0) {
			throw new IllegalArgumentException("x < 0");
		}
		
		// n = x*(10^(2*scale))
		BigInteger n = x.movePointRight(scale << 1).toBigInteger();
		
		// The first approximation is the upper half of n.
		int bits = (n.bitLength() + 1) >> 1;
		BigInteger ix = n.shiftRight(bits);
		BigInteger ixPrev;
		
		// Loop until the approximations converge
		// (two successive approximations are equal after rounding).
		do {
			ixPrev = ix;
			
			// x = (x + n/x)/2
			ix = ix.add(n.divide(ix)).shiftRight(1);
			
			Thread.yield();
		} while (ix.compareTo(ixPrev) != 0);
		
		return new BigDecimal(ix, scale);
	}
	
}

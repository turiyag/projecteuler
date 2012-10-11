package extras;

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
	
}

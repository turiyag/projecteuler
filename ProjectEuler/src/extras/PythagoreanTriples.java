package extras;

import primenumber.Primes;

public class PythagoreanTriples {
	
	int		_iaPrimitiveLengths[];
	Primes	_pPrimes;
	
	public PythagoreanTriples(final int iGenerateToLength) {
		super();
		_pPrimes = new Primes(iGenerateToLength);
		generateTriples(iGenerateToLength);
	}
	
	public int[] getPrimitiveLengths() {
		return _iaPrimitiveLengths;
	}
	
	private void generateTriples(final int iGenerateToLength) {
		int m;
		int n;
		int iMMax;
		int iNMax;
		
		_iaPrimitiveLengths = new int[iGenerateToLength + 1];
		iMMax = iGenerateToLength / 2;
		for (m = 2; m * (1 + m) <= iMMax; m++) {
			iNMax = iMMax / m;
			iNMax -= m;
			for (n = 1; n < m && n <= iNMax; n++) {
				if (isPrimitive(m, n)) {
					_iaPrimitiveLengths[2 * m * (n + m)]++;
				}
			}
		}
		return;
	}
	
	final private boolean isPrimitive(final int m, final int n) {
		try {
			if ((m - n) % 2 == 1) {
				return _pPrimes.areCoprime(m, n);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}

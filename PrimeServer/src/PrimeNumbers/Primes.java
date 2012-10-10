package PrimeNumbers;

import java.math.BigInteger;
import java.util.Arrays;

public class Primes {
	protected int		_iaPrimes[];
	protected int		_iaTotients[];
	
	static final int	DEFAULT_LIMIT	= 1000000;
	
	/**
	 * Creates a new object and generates the initial primes
	 * 
	 * @param iLimit
	 *            The upper limit of the range to search for primes.
	 */
	public Primes(final int iLimit) {
		sieveOfAtkin(iLimit);
	}
	
	/**
	 * Creates a new object
	 * 
	 */
	
	public Primes() {
		// Do nothing
	}
	
	/**
	 * Generates the initial primes. This method is deprecated in favor of the Sieve Of Atkin.
	 * 
	 * @param iCount
	 *            The number of primes to generate
	 * @deprecated
	 */
	@Deprecated
	public void sieveOfEratosthenes(final int iCount) {
		int lNum = 0;
		int lRoot = 0;
		int k = 0;
		final long lTime = System.nanoTime();
		boolean bIsComposite = false;
		
		_iaPrimes = new int[iCount];
		
		lNum = 2;
		_iaPrimes[0] = 2;
		lNum = 1;
		for (int i = 1; i < iCount;) {
			lNum += 2;
			
			lRoot = (int) Math.sqrt(lNum);
			k = 0;
			bIsComposite = false;
			while (_iaPrimes[k] <= lRoot && bIsComposite == false) {
				if (lNum % _iaPrimes[k] == 0) {
					bIsComposite = true;
				}
				k++;
			}
			if (bIsComposite == false) {
				_iaPrimes[i] = lNum;
				i++;
			}
		}
		System.out.println(iCount + " primes up to " + _iaPrimes[iCount - 1] + " generated in " + (System.nanoTime() - lTime) / 1000000 + "ms");
	}
	
	/**
	 * Generates the initial primes via a quick algorithm, which is orders of magnitude better than the Sieve of Erasthenes. This refactoring was done to improve the speed of the program.
	 * 
	 * @param iLimit
	 *            The upper limit of the range to search for primes.
	 */
	public void sieveOfAtkin(final int iLimit) {
		// For tracking performance
		// final long lTime = System.nanoTime();
		final boolean[] isPrime = new boolean[iLimit + 1];
		int x;
		int y;
		int n;
		int i;
		int iCount;
		int xSquared;
		int nSquared;
		int nMod12;
		final int sqrtLimit = (int) Math.sqrt(iLimit);
		
		switch (iLimit) {
			case 0:
			case 1:
				_iaPrimes = null;
				break;
			case 2:
				_iaPrimes = new int[1];
				_iaPrimes[0] = 2;
				break;
			case 3:
				_iaPrimes = new int[2];
				_iaPrimes[0] = 2;
				_iaPrimes[1] = 3;
				break;
			case 4:
				_iaPrimes = new int[2];
				_iaPrimes[0] = 2;
				_iaPrimes[1] = 3;
				break;
			case 5:
				_iaPrimes = new int[3];
				_iaPrimes[0] = 2;
				_iaPrimes[1] = 3;
				_iaPrimes[2] = 5;
				break;
			default:
				for (x = 1; x <= sqrtLimit; x++) {
					xSquared = x * x;
					if (4 * xSquared + 1 <= iLimit) {
						for (y = 1; y <= sqrtLimit; y++) {
							n = 4 * xSquared + y * y;
							if (n <= iLimit) {
								nMod12 = n % 12;
								if (nMod12 == 1 || nMod12 == 5) {
									isPrime[n] = !isPrime[n];
								}
							} else {
								y = sqrtLimit + 1;
							}
						}
					}
				}
				for (x = 1; x <= sqrtLimit; x++) {
					xSquared = x * x;
					if (3 * xSquared + 1 <= iLimit) {
						for (y = 1; y <= sqrtLimit; y++) {
							n = 3 * xSquared + y * y;
							if (n <= iLimit) {
								if (n % 12 == 7) {
									isPrime[n] = !isPrime[n];
								}
							} else {
								y = sqrtLimit + 1;
							}
						}
					}
				}
				for (x = 1; x <= sqrtLimit; x++) {
					xSquared = x * x;
					if (2 * (xSquared - x) + 1 <= iLimit) {
						for (y = x - 1; y > 0; y--) {
							n = 3 * xSquared - y * y;
							if (n <= iLimit) {
								if (n % 12 == 11) {
									isPrime[n] = !isPrime[n];
								}
							} else {
								y = 0;
							}
						}
					}
				}
				for (n = 5; n < sqrtLimit; n += 2) {
					if (isPrime[n]) {
						nSquared = n * n;
						for (i = nSquared; i < iLimit; i += nSquared) {
							isPrime[i] = false;
						}
					}
				}
				iCount = 3;
				for (n = 7; n < isPrime.length; n += 2) {
					if (isPrime[n]) {
						iCount++;
					}
				}
				_iaPrimes = new int[iCount];
				_iaPrimes[0] = 2;
				_iaPrimes[1] = 3;
				i = 2;
				for (n = 5; n < isPrime.length; n += 2) {
					if (isPrime[n]) {
						_iaPrimes[i] = n;
						i++;
					}
				}
		}
		// Print metrics
		// System.out.println(_iaPrimes.length + " primes up to " + _iaPrimes[_iaPrimes.length - 1] + " generated in " + (System.nanoTime() - lTime) / 1000000 + "ms");
	}
	
	public int getPrime(final int i) {
		return _iaPrimes[i];
	}
	
	// Retrieves an array of length=iLength of prime numbers from the iFrom'th
	// number
	public int[] getPrimes(final int iFrom, final int iTo) throws Exception {
		if (iFrom >= 0 && iTo >= iFrom && iTo <= _iaPrimes.length) {
			return Arrays.copyOfRange(_iaPrimes, iFrom, iTo);
		} else {
			throw new Exception("Not enough primes generated to fulfill request");
		}
	}
	
	public int getCount() {
		return _iaPrimes.length;
	}
	
	public int findPrime(final int iPrime) {
		return findPrime(iPrime, 0, getCount());
	}
	
	public int findPrime(final int iPrime, final int iStart, final int iEnd) {
		final int iMid = (iEnd - iStart) / 2 + iStart;
		if (iMid >= getCount()) {
			return 0;
		}
		if (_iaPrimes[iMid] == iPrime) {
			return iMid;
		} else if (iStart == iEnd) {
			return 0;
		} else if (_iaPrimes[iMid] < iPrime) {
			return findPrime(iPrime, iMid + 1, iEnd);
		} else {
			return findPrime(iPrime, iStart, iMid);
		}
	}
	
	public int[] factor(int iNum) {
		final int[] iaDivs = new int[50];
		int iCount = 0;
		for (int i = 0; _iaPrimes[i] <= iNum; i++) {
			if (iNum % _iaPrimes[i] == 0) {
				iNum /= _iaPrimes[i];
				iaDivs[iCount] = i;
				iCount++;
				i--;
			}
		}
		final int[] iaRet = new int[iCount];
		for (int k = 0; k < iCount; k++) {
			iaRet[k] = iaDivs[k];
		}
		return iaRet;
	}
	
	public void genTotients(final int iCount) {
		if (iCount > _iaPrimes[getCount() - 1]) {
			System.out.println("More prime numbers needed for totient function");
			return;
		}
		_iaTotients = new int[iCount];
		
		for (int i = 0; i < iCount; i++) {
			_iaTotients[i] = totient(i);
		}
		
	}
	
	public int totient(final int iNum) {
		final int[] iaFactors = factor(iNum);
		if (iaFactors.length == 1) {
			return iNum - 1;
		}
		final int iMax = iNum / _iaPrimes[iaFactors[1]];
		int iCoprimeCount = 0;
		for (int i = 1; i < iMax; i++) {
			for (int k = 0; k < iaFactors.length; k++) {
				if (i % _iaPrimes[iaFactors[k]] == 0) {
					k = iaFactors.length;
					iCoprimeCount--;
				}
			}
			iCoprimeCount++;
		}
		iCoprimeCount++;
		return iCoprimeCount;
	}
	
	public static boolean isPrime(final int iNum) {
		return PrimeTestMillerRabin.GoodTest(iNum);
	}
	
	public static boolean isPrime(final BigInteger biNum) {
		return PrimeTestMillerRabin.MRIsLikelyPrime(biNum);
	}
}

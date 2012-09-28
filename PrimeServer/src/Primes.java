import java.util.Arrays;

public class Primes {
	static final public int	PRIME_1SEC		= 197438;
	static final public int	PRIME_3SEC		= 499349;
	static final public int	PRIME_5SEC		= 749724;
	static final public int	PRIME_10SEC		= 1248507;
	static final public int	PRIME_20SEC		= 1986321;
	static final public int	PRIME_30SEC		= 2628901;
	static final public int	PRIME_50SEC		= 3776991;
	static final public int	PRIME_100SEC	= 6204466;
	static final public int	PRIME_200SEC	= 10192081;
	static final public int	PRIME_400SEC	= 16742539;
	static final public int	PRIME_800SEC	= 27502981;
	static final public int	PRIME_1600SEC	= 45179167;
	
	private int				_iPrimes[];
	private int				_iTotients[];
	
	/**
	 * Creates a new object and generates the initial primes
	 * 
	 * @param iCount
	 *            The number of primes to generate
	 */
	public Primes(final int iCount) {
		GenPrimes(iCount);
	}
	
	/**
	 * Creates a new object
	 */
	public Primes() {
	}
	
	/**
	 * Generates the initial primes
	 * 
	 * @param iCount
	 *            The number of primes to generate
	 */
	public void GenPrimes(final int iCount) {
		int lNum = 0;
		int lRoot = 0;
		int k = 0;
		final long lTime = System.nanoTime();
		boolean bIsComposite = false;
		
		_iPrimes = new int[iCount];
		System.out.println("Memory allocated");
		
		lNum = 2;
		_iPrimes[0] = 2;
		for (int i = 1; i < iCount;) {
			lNum++;
			
			lRoot = (int) Math.sqrt(lNum);
			k = 0;
			bIsComposite = false;
			while (_iPrimes[k] <= lRoot && bIsComposite == false) {
				if (lNum % _iPrimes[k] == 0) {
					bIsComposite = true;
				}
				k++;
			}
			if (bIsComposite == false) {
				_iPrimes[i] = lNum;
				i++;
			}
		}
		System.out.println(iCount + " primes up to " + _iPrimes[iCount - 1]
				+ " generated in " + (System.nanoTime() - lTime) / 1000000
				+ "ms");
	}
	
	public int GetPrime(final int i) {
		return _iPrimes[i];
	}
	
	// Retrieves an array of length=iLength of prime numbers from the iFrom'th
	// number
	public int[] GetPrimes(final int iFrom, final int iTo) {
		if (iFrom >= 0 && iTo >= iFrom && iTo <= _iPrimes.length) {
			return Arrays.copyOfRange(_iPrimes, iFrom, iTo);
		} else {
			return null;
		}
	}
	
	public int GetCount() {
		return _iPrimes.length;
	}
	
	public int FindPrime(final int iPrime) {
		return FindPrime(iPrime, 0, GetCount());
	}
	
	public int FindPrime(final int iPrime, final int iStart, final int iEnd) {
		final int iMid = (iEnd - iStart) / 2 + iStart;
		if (iMid >= GetCount()) {
			return 0;
		}
		if (_iPrimes[iMid] == iPrime) {
			return iMid;
		} else if (iStart == iEnd) {
			return 0;
		} else if (_iPrimes[iMid] < iPrime) {
			return FindPrime(iPrime, iMid + 1, iEnd);
		} else {
			return FindPrime(iPrime, iStart, iMid);
		}
	}
	
	public int[] Factor(int iNum) {
		final int[] iaDivs = new int[50];
		int iCount = 0;
		for (int i = 0; _iPrimes[i] <= iNum; i++) {
			if (iNum % _iPrimes[i] == 0) {
				iNum /= _iPrimes[i];
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
	
	public void GenTotients(final int iCount) {
		if (iCount > _iPrimes[GetCount() - 1]) {
			System.out
					.println("More prime numbers needed for totient function");
			return;
		}
		_iTotients = new int[iCount];
		
		for (int i = 0; i < iCount; i++) {
			_iTotients[i] = Totient(i);
		}
		
	}
	
	public int Totient(final int iNum) {
		final int[] iaFactors = Factor(iNum);
		if (iaFactors.length == 1) {
			return iNum - 1;
		}
		final int iMax = iNum / _iPrimes[iaFactors[1]];
		int iCoprimeCount = 0;
		for (int i = 1; i < iMax; i++) {
			for (int k = 0; k < iaFactors.length; k++) {
				if (i % _iPrimes[iaFactors[k]] == 0) {
					k = iaFactors.length;
					iCoprimeCount--;
				}
			}
			iCoprimeCount++;
		}
		iCoprimeCount++;
		return iCoprimeCount;
	}
}
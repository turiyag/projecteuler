package extras;

import java.util.ArrayList;
import java.util.LinkedList;
import java.math.BigInteger;

import specialnumbers.IntListing;

public class BigSums {
	private ArrayList<LinkedList<IntListing>>	_lilaSums;
	private int									_iMax;
	private BigInteger[]						_baaSumsUnder;
	
	public BigSums(int iMax) {
		super();
		_iMax = iMax + 1;
		// _baaSumsUnder = new BigInteger[_iMax][_iMax];
		_baaSumsUnder = new BigInteger[_iMax];
		_lilaSums = new ArrayList<LinkedList<IntListing>>(_iMax);
		LinkedList<IntListing> lil = new LinkedList<IntListing>();
		
		IntListing il = new IntListing(_iMax);
		il.add(1);
		lil.add(il);
		_lilaSums.add(lil);
		
		lil = new LinkedList<IntListing>();
		il = new IntListing(_iMax);
		il.add(1, 2);
		lil.add(il);
		il = new IntListing(_iMax);
		il.add(2);
		lil.add(il);
		_lilaSums.add(lil);
	}
	
	public BigInteger allSumsUnder(long iNum) {
		BigInteger bTotalCount;
		if (iNum == 1) {
			return BigInteger.ONE;
		} else if (iNum == 0) {
			return BigInteger.ONE;
		} else if (iNum < 0) {
			return BigInteger.ZERO;
		} else {
			if (iNum < _iMax) {
				if (_baaSumsUnder[(int) iNum] != null) {
					return _baaSumsUnder[(int) iNum];
				} else {
					bTotalCount = BigInteger.ZERO;
					for (long i = 1; ((3 * i - 1) * i) / 2 <= iNum; i++) {
						if (i % 2 == 0) {
							bTotalCount = bTotalCount.subtract(allSumsUnder(iNum - ((3 * i - 1) * i) / 2));
							bTotalCount = bTotalCount.subtract(allSumsUnder(iNum - ((3 * i + 1) * i) / 2));
						} else {
							bTotalCount = bTotalCount.add(allSumsUnder(iNum - ((3 * i - 1) * i) / 2));
							bTotalCount = bTotalCount.add(allSumsUnder(iNum - ((3 * i + 1) * i) / 2));
						}
					}
					_baaSumsUnder[(int) iNum] = bTotalCount;
					return bTotalCount;
				}
			} else {
				bTotalCount = BigInteger.ZERO;
				for (int i = 1; i <= iNum; i++) {
					if (i % 2 == 0) {
						bTotalCount = bTotalCount.subtract(allSumsUnder(iNum - ((3 * i - 1) * i) / 2));
						bTotalCount = bTotalCount.subtract(allSumsUnder(iNum - ((3 * i + 1) * i) / 2));
					} else {
						bTotalCount = bTotalCount.add(allSumsUnder(iNum - ((3 * i - 1) * i) / 2));
						bTotalCount = bTotalCount.add(allSumsUnder(iNum - ((3 * i + 1) * i) / 2));
					}
				}
				return bTotalCount;
			}
		}
	}
	
	public static BigInteger sumDigits(BigInteger b) {
		BigInteger bSum = BigInteger.ZERO;
		while (b.compareTo(BigInteger.ZERO) == 1) {
			bSum = bSum.add(b.mod(BigInteger.TEN));
			b = b.divide(BigInteger.TEN);
		}
		return bSum;
	}
	
}

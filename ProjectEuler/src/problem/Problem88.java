package problem;

import java.util.Arrays;

import primenumber.Primes;
import specialnumbers.Sums;

public class Problem88 {
	static final int	MAX_K	= 12000;
	static int[]		_iaMin;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Primes p = new Primes(1200000);
		int iN;
		_iaMin = new int[MAX_K * 2];
		for (iN = 4; iN < MAX_K; iN++) {
			addFactors(p.factor(iN), iN);
		}
		System.out.println(Arrays.toString(_iaMin));
	}
	
	private static void addFactors(int[] iaFactors, int iN) {
		int iSum = Sums.sumArray(iaFactors);
		int iOnes = iN - iSum;
		int iLen = iOnes + iaFactors.length;
		int[] iaNew;
		int iNextVal;
		if (_iaMin[iLen] == 0) {
			_iaMin[iLen] = iN;
			// System.out.println(iLen + "\t" + iN);
		}
		for (int i1 = 0; i1 < iaFactors.length; i1++) {
			for (int i2 = i1 + 1; i2 < iaFactors.length; i2++) {
				iaNew = new int[iaFactors.length - 1];
				iaNew[0] = iaFactors[i1] * iaFactors[i2];
				iNextVal = 1;
				for (int i = 0; i < iaFactors.length; i++) {
					if (i != i1) {
						if (i != i2) {
							iaNew[iNextVal] = iaFactors[i];
							iNextVal++;
						}
					}
				}
				addFactors(iaNew, iN);
			}
		}
	}
	/*
	 * private static void attempt1() {
	 * Primes p = new Primes(120000);
	 * int[] iaLowerBound = new int[MAX_K + 1];
	 * int[] iaLowest = new int[MAX_K];
	 * int[] iaCurr;
	 * int iMin;
	 * for (_iCurrK = 2; _iCurrK <= MAX_K; _iCurrK++) {
	 * _iBound = _iCurrK + 1;
	 * iaCurr = new int[_iCurrK];
	 * iaLowerBound[_iCurrK] = Integer.MAX_VALUE;
	 * for (int i = 0; i < iaCurr.length; i++) {
	 * iaCurr[i] = 1;
	 * }
	 * iaCurr[_iCurrK - 2] = 2;
	 * iaCurr[_iCurrK - 1] = 1;
	 * iMin = Math.max(0, _iCurrK - 5);
	 * while (iaCurr[iMin] < _iBound - 2) {
	 * incValue(iaCurr, _iCurrK - 1);
	 * if (isPSNumber(iaCurr)) {
	 * if (iaLowerBound[_iCurrK] > _iCurrVal) {
	 * iaLowerBound[_iCurrK] = _iCurrVal;
	 * }
	 * }
	 * }
	 * }
	 * System.out.println(Arrays.toString(iaLowerBound));
	 * }
	 * 
	 * private static void incValue(int[] iArray, int iIndex) {
	 * iArray[iIndex]++;
	 * if (iArray[iIndex] > _iBound) {
	 * if (iIndex > 0) {
	 * iArray[iIndex] = iArray[iIndex - 1];
	 * }
	 * incValue(iArray, iIndex - 1);
	 * }
	 * 
	 * }
	 * 
	 * private static boolean isPSNumber(int[] iaCurr) {
	 * int iSum = 0;
	 * int iProduct = 1;
	 * for (int i : iaCurr) {
	 * iSum += i;
	 * iProduct *= i;
	 * }
	 * _iCurrVal = iSum;
	 * return iSum == iProduct;
	 * }
	 */
}

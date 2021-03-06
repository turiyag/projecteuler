package problem;

import java.math.BigDecimal;
import java.math.BigInteger;


import specialnumbers.BigSums;
import specialnumbers.NumHelp;

public class Problem79 {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BigInteger bTotalSum = BigInteger.ZERO;
		BigInteger bTemp;
		BigDecimal bSqrt;
		for (int i = 2; i < 100; i++) {
			bSqrt = BigDecimal.valueOf(i);
			bSqrt = NumHelp.sqrt(bSqrt, 600);
			bTemp = new BigInteger(bSqrt.toString().substring(2, 101));
			if (!BigSums.sumDigits(bTemp).equals(BigInteger.ZERO)) {
				bTotalSum = bTotalSum.add(BigSums.sumDigits(bTemp));
				bTemp = bSqrt.toBigInteger();
				bTotalSum = bTotalSum.add(BigSums.sumDigits(bTemp));
			}
		}
		System.out.println(bTotalSum);
	}
	
}

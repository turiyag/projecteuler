package specialnumbers;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class BigSumsTest {
	
	@Test
	public void testAllSumsUnder() {
		BigSums bs = new BigSums(100);
		assertEquals(BigInteger.valueOf(1), bs.allSumsUnder(1));
		assertEquals(BigInteger.valueOf(2), bs.allSumsUnder(2));
		assertEquals(BigInteger.valueOf(3), bs.allSumsUnder(3));
		assertEquals(BigInteger.valueOf(5), bs.allSumsUnder(4));
		assertEquals(BigInteger.valueOf(7), bs.allSumsUnder(5));
	}
	
	@Test
	public void testSumDigits() {
		BigInteger bSum;
		BigInteger bVal;
		bVal = new BigInteger("1234");
		bSum = new BigInteger("10");
		assertEquals(bSum, BigSums.sumDigits(bVal));
		bVal = new BigInteger("12341234");
		bSum = new BigInteger("20");
		assertEquals(bSum, BigSums.sumDigits(bVal));
		bVal = new BigInteger("1000234");
		bSum = new BigInteger("10");
		assertEquals(bSum, BigSums.sumDigits(bVal));
		bVal = new BigInteger("12345555");
		bSum = new BigInteger("30");
		assertEquals(bSum, BigSums.sumDigits(bVal));
		bVal = new BigInteger("1234567890");
		bSum = new BigInteger("45");
		assertEquals(bSum, BigSums.sumDigits(bVal));
		bVal = new BigInteger("12345678901234567890");
		bSum = new BigInteger("90");
		assertEquals(bSum, BigSums.sumDigits(bVal));
	}
	
}

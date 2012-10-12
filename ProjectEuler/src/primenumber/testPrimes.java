package primenumber;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testPrimes {
	Primes	p;
	Primes	p2;
	Primes	pLarge;
	
	@Before
	public void setUp() throws Exception {
		p2 = new Primes(2); // Primes up to 2 (2)
		p = new Primes(100); // Primes up to 100 (97)
		pLarge = new Primes(10000000); // Primes up to 10 000 000 (9 999 991)
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetter() {
		try {
			p.getPrime(-1);
			fail("Array bound breach should have errored");
		} catch (final ArrayIndexOutOfBoundsException e) {
			// This is normal functionality
			
		}
		assertEquals(2, p.getPrime(0));
		assertEquals(3, p.getPrime(1));
		assertEquals(5, p.getPrime(2));
		assertEquals(7, p.getPrime(3));
		try {
			assertArrayEquals(new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97 }, p.getPrimes(0, p.getCount()));
		} catch (final Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			p.getPrime(100);
			fail("Array bound breach should have errored");
		} catch (final ArrayIndexOutOfBoundsException e) {
			// This is normal functionality
		}
		
		try {
			pLarge.getPrime(-1);
			fail("Array bound breach should have errored");
		} catch (final ArrayIndexOutOfBoundsException e) {
			// This is normal functionality
			
		}
		assertEquals(2, pLarge.getPrime(0));
		assertEquals(3, pLarge.getPrime(1));
		assertEquals(5, pLarge.getPrime(2));
		assertEquals(9999991, pLarge.getPrime(pLarge.getCount() - 1));
		try {
			pLarge.getPrime(pLarge.getCount());
			fail("Array bound breach should have errored");
		} catch (final ArrayIndexOutOfBoundsException e) {
			// This is normal functionality
		}
	}
	
	@Test
	public void testGetArray() {
		try {
			assertArrayEquals(new int[] { 2, 3, 5, 7, 11 }, p.getPrimes(0, 5));
			assertArrayEquals(new int[] { 5, 7, 11, 13, 17 }, p.getPrimes(2, 7));
			assertArrayEquals(null, p.getPrimes(7, 2));
			assertArrayEquals(new int[] {}, p.getPrimes(7, 7));
			assertArrayEquals(new int[] { 89, 97 }, p.getPrimes(p.getCount() - 2, p.getCount()));
			assertArrayEquals(null, p.getPrimes(98, 1001));
			assertArrayEquals(null, p.getPrimes(-2, 5));
			assertArrayEquals(null, p.getPrimes(-2, -5));
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}
	
	@Test
	public void testGetCount() {
		assertEquals(1, p2.getCount());
		assertEquals(25, p.getCount());
		assertEquals(664579, pLarge.getCount());
	}
	
	@Test
	public void testIsPrime() {
		assertTrue(Primes.isPrime(2));
		assertTrue(Primes.isPrime(3));
		assertFalse(Primes.isPrime(4));
		assertTrue(Primes.isPrime(5));
		assertTrue(Primes.isPrime(7));
		assertTrue(Primes.isPrime(11));
		assertTrue(Primes.isPrime(13));
		assertTrue(Primes.isPrime(17));
		assertTrue(Primes.isPrime(1061));
		assertTrue(Primes.isPrime(2029));
		assertTrue(Primes.isPrime(3253));
		assertTrue(Primes.isPrime(48611));
		
	}
	
	@Test
	public void testAreCoprime() {
		try {
			assertTrue(p.areCoprime(2, 3));
			assertTrue(p.areCoprime(2, 7));
			assertTrue(p.areCoprime(5, 9));
			assertFalse(p.areCoprime(6, 9));
			assertFalse(p.areCoprime(6, 9));
			assertFalse(p.areCoprime(6, 32));
			assertTrue(p.areCoprime(12, 17));
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			p.areCoprime(-1, 3);
			fail("This should have failed.");
		} catch (final Exception e) {
			
		}
	}
}

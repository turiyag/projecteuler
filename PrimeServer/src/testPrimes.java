import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testPrimes {
	Primes	p;
	Primes	pLarge;
	
	@Before
	public void setUp() throws Exception {
		p = new Primes(100);
		pLarge = new Primes(Primes.PRIME_5SEC);
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetter() {
		try {
			p.GetPrime(-1);
			fail("Array bound breach should have errored");
		} catch (final ArrayIndexOutOfBoundsException e) {
			// This is normal functionality
			
		}
		assertEquals(2, p.GetPrime(0));
		assertEquals(3, p.GetPrime(1));
		assertEquals(5, p.GetPrime(2));
		assertEquals(7, p.GetPrime(3));
		assertEquals(541, p.GetPrime(99));
		try {
			p.GetPrime(100);
			fail("Array bound breach should have errored");
		} catch (final ArrayIndexOutOfBoundsException e) {
			// This is normal functionality
		}
		
		try {
			pLarge.GetPrime(-1);
			fail("Array bound breach should have errored");
		} catch (final ArrayIndexOutOfBoundsException e) {
			// This is normal functionality
			
		}
		assertEquals(2, pLarge.GetPrime(0));
		assertEquals(3, pLarge.GetPrime(1));
		assertEquals(5, pLarge.GetPrime(2));
		assertEquals(11376979, pLarge.GetPrime(Primes.PRIME_5SEC - 1));
		try {
			pLarge.GetPrime(Primes.PRIME_5SEC);
			fail("Array bound breach should have errored");
		} catch (final ArrayIndexOutOfBoundsException e) {
			// This is normal functionality
		}
	}
	
	@Test
	public void testGetArray() {
		assertArrayEquals(new int[] { 2, 3, 5, 7, 11 }, p.GetPrimes(0, 5));
		assertArrayEquals(new int[] { 5, 7, 11, 13, 17 }, p.GetPrimes(2, 7));
		assertArrayEquals(null, p.GetPrimes(7, 2));
		assertArrayEquals(new int[] {}, p.GetPrimes(7, 7));
		assertArrayEquals(new int[] { 523, 541 }, p.GetPrimes(98, 100));
		assertArrayEquals(null, p.GetPrimes(98, 1001));
		assertArrayEquals(null, p.GetPrimes(-2, 5));
		assertArrayEquals(null, p.GetPrimes(-2, -5));
		
	}
}

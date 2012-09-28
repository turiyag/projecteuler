import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testPrimeClient {
	PrimeClient	_pc;
	PrimeClient	_pcLarge;
	
	@Before
	public void setUp() throws Exception {
		_pc = new PrimeClient(100);
		_pcLarge = new PrimeClient(PrimeClient.PRIME_1SEC);
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetter() {
		try {
			_pc.GetPrime(-1);
			fail("Array bound breach should have errored");
		} catch (final ArrayIndexOutOfBoundsException e) {
			// This is normal functionality
			
		}
		assertEquals(2, _pc.GetPrime(0));
		assertEquals(3, _pc.GetPrime(1));
		assertEquals(5, _pc.GetPrime(2));
		assertEquals(7, _pc.GetPrime(3));
		assertEquals(541, _pc.GetPrime(99));
		try {
			_pc.GetPrime(100);
			fail("Array bound breach should have errored");
		} catch (final ArrayIndexOutOfBoundsException e) {
			// This is normal functionality
		}
		
		try {
			_pcLarge.GetPrime(-1);
			fail("Array bound breach should have errored");
		} catch (final ArrayIndexOutOfBoundsException e) {
			// This is normal functionality
			
		}
		assertEquals(2, _pcLarge.GetPrime(0));
		assertEquals(3, _pcLarge.GetPrime(1));
		assertEquals(5, _pcLarge.GetPrime(2));
		assertEquals(2711743, _pcLarge.GetPrime(PrimeClient.PRIME_1SEC - 1));
		try {
			_pcLarge.GetPrime(PrimeClient.PRIME_1SEC);
			fail("Array bound breach should have errored");
		} catch (final ArrayIndexOutOfBoundsException e) {
			// This is normal functionality
		}
	}
	
}
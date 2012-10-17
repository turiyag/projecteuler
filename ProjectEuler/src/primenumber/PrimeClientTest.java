package primenumber;

import static org.junit.Assert.*;

import java.net.InetAddress;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PrimeClientTest {
	PrimeClient	_pcHost;
	PrimeClient	_pcIALimit;
	PrimeClient	_pcDefault;
	
	@Before
	public void setUp() throws Exception {
		InetAddress ia;
		ia = InetAddress.getByName("localhost");
		_pcHost = new PrimeClient("localhost");
		try {
			_pcIALimit = new PrimeClient(ia, PrimeClient.DEFAULT_PORT, PrimeServer.DEFAULT_LIMIT + 1);
			fail("This should be out of bounds for a normal server");
		} catch (final Exception e) {
			
		}
		_pcDefault = new PrimeClient();
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testAll() {
		getter(_pcHost);
		// getter(_pcIALimit); Don't test this, it's supposed to fail.
		getter(_pcDefault);
	}
	
	public void getter(final Primes p) {
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
			assertArrayEquals(new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97 }, p.getPrimes(0, 25));
		} catch (final Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			p.getPrime(p.getCount());
			fail("Array bound breach should have errored");
		} catch (final ArrayIndexOutOfBoundsException e) {
			// This is normal functionality
		}
	}
	
}

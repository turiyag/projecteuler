package primenumber;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PrimeServerTest {
	PrimeServer	_ps;
	
	@Before
	public void setUp() throws Exception {
		int i = 0;
		final PrimeServer _ps = new PrimeServer();
		i = _ps.getCount();
		i = i + 1;
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test() {
		assertEquals(2, _ps.getPrime(0));
		assertEquals(3, _ps.getPrime(1));
		assertEquals(5, _ps.getPrime(2));
		assertEquals(7, _ps.getPrime(3));
		
	}
	
}

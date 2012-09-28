import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testPrimeServer {
	
	@Before
	public void setUp() throws Exception {
		int i = 0;
		final PrimeServer ps = new PrimeServer();
		i = ps.getCount();
		i = i + 1;
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test() {
		
	}
	
}

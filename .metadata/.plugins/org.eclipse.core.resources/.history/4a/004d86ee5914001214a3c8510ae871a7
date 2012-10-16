package extras;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PythagoreanTriplesTest {
	PythagoreanTriples	_pt;
	int					_iaPrimeLengths[];
	
	@Before
	public void setUp() throws Exception {
		_pt = new PythagoreanTriples(1500000);
		_iaPrimeLengths = _pt.getPrimitiveLengths();
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test() {
		assertEquals(1, _iaPrimeLengths[12]);
		assertEquals(1, _iaPrimeLengths[40]);
		assertEquals(1, _iaPrimeLengths[30]);
		assertEquals(0, _iaPrimeLengths[36]);
		assertEquals(0, _iaPrimeLengths[1500000]);
		final int[] iaPrims = new int[] { 3 + 4 + 5, 5 + 12 + 13, 7 + 24 + 25, 8 + 15 + 17, 9 + 40 + 41, 11 + 60 + 61, 12 + 35 + 37, 13 + 84 + 85, 16 + 63 + 65, 20 + 21 + 29, 28 + 45 + 53, 33 + 56 + 65, 36 + 77 + 85, 39 + 80 + 89, 48 + 55 + 73, 65 + 72 + 97 };
		for (final int iLen : iaPrims) {
			assertEquals(1, _iaPrimeLengths[iLen]);
		}
	}
	
}

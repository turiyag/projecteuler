import static org.junit.Assert.assertEquals;

import org.junit.Test;

import asg2.Primes;

public class InspectorTest {
	
	@Test
	public void testTabs() {
		Inspector i = new Inspector();
		assertEquals(0, i._iTabLevel);
		i.tabIn();
		assertEquals(1, i._iTabLevel);
		System.out.println("Verify that there is 1 tab here");
		i.forTheRecordLn("Test");
		i.tabIn();
		assertEquals(2, i._iTabLevel);
		System.out.println("Verify that there are 2 tabs here");
		i.forTheRecordLn("Test for 2");
		i.tabIn();
		assertEquals(3, i._iTabLevel);
		i.tabIn();
		assertEquals(4, i._iTabLevel);
		i.tabOut();
		assertEquals(3, i._iTabLevel);
		i.tabOut();
		assertEquals(2, i._iTabLevel);
		i.tabOut();
		assertEquals(1, i._iTabLevel);
		i.tabOut();
		assertEquals(0, i._iTabLevel);
	}
	
	@Test
	public void testGetNiceName() {
		Inspector i = new Inspector();
		Primes p = new Primes(1000);
		Primes[] pa = new Primes[100];
		Primes[][] paa = new Primes[10][5];
		String sExpect = "asg2.Primes";
		assertEquals(sExpect, i.getNiceName(p.getClass()));
		for (Primes pInner : pa) {
			pInner = new Primes(1000);
		}
		assertEquals(sExpect + "[]", i.getNiceName(pa.getClass()));
		assertEquals(sExpect + "[][]", i.getNiceName(paa.getClass()));
		sExpect = "Interface java.util.Set";
		assertEquals(sExpect, i.getNiceName(java.util.Set.class));
		sExpect = "";
		assertEquals(sExpect, i.getNiceName(null));
	}
}

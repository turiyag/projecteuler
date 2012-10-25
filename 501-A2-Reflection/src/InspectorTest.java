import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
		assertEquals(sExpect + "[]", i.getNiceName(pa.getClass()));
		assertEquals(sExpect + "[][]", i.getNiceName(paa.getClass()));
		sExpect = "Interface java.util.Set";
		assertEquals(sExpect, i.getNiceName(java.util.Set.class));
		try {
			i.getNiceName(null);
			fail("This was supposed to die");
		} catch (Exception e) {
			// Intended behavior
		}
	}
	
	@Test
	public void testConstructorParameters() throws NoSuchMethodException, SecurityException {
		Inspector i = new Inspector();
		Primes p = new Primes(1000);
		assertEquals("", i.constructorParameters(p.getClass().getConstructor(null)));
		assertEquals("int", i.constructorParameters(p.getClass().getConstructor(int.class)));
	}
	
	@Test
	public void testMethodExceptions() throws NoSuchMethodException, SecurityException {
		Inspector i = new Inspector();
		Primes p = new Primes(1000);
		assertEquals("throws java.lang.Exception", i.methodExceptions(p.getClass().getMethod("areCoprime", int.class, int.class)));
		assertEquals("", i.methodExceptions(p.getClass().getMethod("isPrime", int.class)));
	}
	
	@Test
	public void testMethodParameters() throws NoSuchMethodException, SecurityException {
		Inspector i = new Inspector();
		Primes p = new Primes(1000);
		assertEquals("int, int", i.methodParameters(p.getClass().getMethod("areCoprime", int.class, int.class)));
	}
	
	@Test
	public void testIsPrimitiveWrapper() {
		Inspector i = new Inspector();
		Primes p = new Primes(1000);
		Integer iWrapper = new Integer(5);
		assertTrue(i.isPrimitiveWrapper(iWrapper.getClass()));
		assertFalse(i.isPrimitiveWrapper(p.getClass()));
	}
	
	@Test
	public void testPrintObject() {
		Inspector i = new Inspector();
		Primes p = new Primes(1000);
		Integer iWrapper = new Integer(5);
		Integer[] iaWrapper = new Integer[] { 5, 4, 3, 2, 1 };
		Integer[][] iaaWrapper = new Integer[][] { { 5, 4, 3, 2, 1 }, { 5, 4, 3, 2, 1 } };
		System.out.println("Verify that it prints 5");
		i.printObjectOrArray(iWrapper);
		System.out.println();
		System.out.println("Verify that it prints [5, 4, 3, 2, 1]");
		i.printObjectOrArray(iaWrapper);
		System.out.println();
		System.out.println("Verify that it prints [[5, 4, 3, 2, 1], [5, 4, 3, 2, 1]]");
		i.printObjectOrArray(iaaWrapper);
		System.out.println();
	}
	
	@Test
	public void testAAInspection() {
		Inspector i = new Inspector();
		System.out.println("====== Verify that this looks OK =====");
		i.inspect(new Primes(100), true);
		
		System.out.println("======================================");
	}
}
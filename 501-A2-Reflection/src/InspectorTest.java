import static org.junit.Assert.assertEquals;

import org.junit.Test;

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
	
}

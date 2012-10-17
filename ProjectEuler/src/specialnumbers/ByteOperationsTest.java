package specialnumbers;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ByteOperationsTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	public void testIntArrayToByteArray() {
		int[] ia;
		byte[] ba;
		ia = new int[] { 1, 2, 3 };
		ba = ByteOperations.intArraytoByteArray(ia);
		assertArrayEquals(ia, ByteOperations.byteArrayToIntArray(ba));
		ia = new int[] { -1, 0, 1 };
		ba = ByteOperations.intArraytoByteArray(ia);
		assertArrayEquals(ia, ByteOperations.byteArrayToIntArray(ba));
		ia = new int[] {};
		ba = ByteOperations.intArraytoByteArray(ia);
		assertArrayEquals(ia, ByteOperations.byteArrayToIntArray(ba));
		ia = new int[] { Integer.MIN_VALUE, 0, Integer.MAX_VALUE };
		ba = ByteOperations.intArraytoByteArray(ia);
		assertArrayEquals(ia, ByteOperations.byteArrayToIntArray(ba));
	}
	
	@Test
	public void testIntToByte() {
		byte[] ba = ByteOperations.intToFourByteArray(-5);
		assertArrayEquals(new byte[] { -5, -1, -1, -1 }, ba);
		assertEquals(-5, ByteOperations.fourByteArrayToInt(ba));
		int i;
		for (i = Integer.MIN_VALUE; i < Integer.MAX_VALUE - 2000; i += 1000) {
			ba = ByteOperations.intToFourByteArray(i);
			assertEquals(i, ByteOperations.fourByteArrayToInt(ba));
		}
		ba = ByteOperations.intToFourByteArray(Integer.MIN_VALUE);
		assertEquals(Integer.MIN_VALUE, ByteOperations.fourByteArrayToInt(ba));
		ba = ByteOperations.intToFourByteArray(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, ByteOperations.fourByteArrayToInt(ba));
		
	}
}

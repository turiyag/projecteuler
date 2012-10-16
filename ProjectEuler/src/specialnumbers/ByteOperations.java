package specialnumbers;

import java.util.Iterator;
import java.util.List;

/**
 * 
 */

/**
 * @author Mitchell Ludwig
 * 
 */
public class ByteOperations {
	public ByteOperations() {
		
	}
	
	/**
	 * @param ba
	 *            An array of bytes, whose length must be 4
	 * @return Returns an integer
	 */
	public static int fourByteArrayToInt(final byte[] ba) {
		int value = (ba[3] & 0xFF) << 24;
		value += (ba[2] & 0xFF) << 16;
		value += (ba[1] & 0xFF) << 8;
		value += ba[0] & 0xFF;
		return value;
	}
	
	/**
	 * @param baLarge
	 *            An array of bytes, whose length must be a multiple of 4
	 * @param offset
	 *            An offset into the array, to tease out a specific number
	 * @return Returns an integer
	 */
	public static int fourByteArrayToInt(final byte[] baLarge, final int offset) {
		int value = (baLarge[3 + offset] & 0xFF) << 24;
		value += (baLarge[2 + offset] & 0xFF) << 16;
		value += (baLarge[1 + offset] & 0xFF) << 8;
		value += baLarge[0 + offset] & 0xFF;
		return value;
	}
	
	/**
	 * @param intList
	 *            An List<Integer> object
	 * @return Returns an array of four bytes
	 */
	public static byte[] intToFourByteArray(final int value) {
		final byte[] baRet = new byte[4];
		baRet[0] = (byte) (value & 0xFF);
		baRet[1] = (byte) (value >> 8 & 0xFF);
		baRet[2] = (byte) (value >> 16 & 0xFF);
		baRet[3] = (byte) (value >> 24 & 0xFF);
		
		return baRet;
	}
	
	/**
	 * @param iaNums
	 *            An array of integers
	 * @return Returns an array of bytes, whose length is 4 * iaNums.length
	 */
	public static byte[] intArraytoByteArray(final int[] iaNums) {
		if (iaNums == null) {
			return null;
		}
		final byte[] baData = new byte[iaNums.length * 4];
		for (int i = 0; i < iaNums.length; i++) {
			baData[i * 4 + 0] = (byte) (iaNums[i] & 0xFF);
			baData[i * 4 + 1] = (byte) ((iaNums[i] & 0xFF00) / 0x100);
			baData[i * 4 + 2] = (byte) ((iaNums[i] & 0xFF0000) / 0x10000);
			baData[i * 4 + 3] = (byte) ((iaNums[i] & 0xFF000000) / 0x1000000);
		}
		return baData;
	}
	
	/**
	 * @param baData
	 *            An array of bytes, arranged in a big-endian manner, with a
	 *            length that is a multiple of 4.
	 * @return Returns an array of integers.
	 */
	public static int[] byteArrayToIntArray(final byte[] baData) {
		final int[] iaNums = new int[baData.length / 4];
		for (int i = 0; i < iaNums.length; i++) {
			iaNums[i] = fourByteArrayToInt(baData, i * 4);
		}
		return iaNums;
	}
	
	/**
	 * @param intList
	 *            An List<Integer> object
	 * @return Returns an array of integers
	 */
	public static int[] listIntToIntArray(final List<Integer> intList) {
		final int[] ret = new int[intList.size()];
		final Iterator<Integer> iterator = intList.iterator();
		for (int i = 0; i < ret.length; i++) {
			ret[i] = iterator.next().intValue();
		}
		return ret;
	}
	
}

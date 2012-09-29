import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PrimeClient extends Primes {
	static final public int			PACKET_SIZE		= 8192;
	static final public int			DEFAULT_PORT	= 65521;
	static final public InetAddress	DEFAULT_HOST	= InetAddress.getLoopbackAddress();
	
	public PrimeClient(final InetAddress iaAddress, final int iPort, final int iLimit) throws Exception {
		transferPrimes(iaAddress, iPort, iLimit);
	}
	
	public PrimeClient(final InetAddress iaAddress, final int iPort) throws Exception {
		transferPrimes(iaAddress, iPort, DEFAULT_LIMIT);
	}
	
	public PrimeClient(final InetAddress iaAddress) throws Exception {
		transferPrimes(iaAddress, DEFAULT_PORT, DEFAULT_LIMIT);
	}
	
	public PrimeClient(final String hostname, final int iLimit) throws Exception {
		transferPrimes(InetAddress.getByName(hostname), DEFAULT_PORT, iLimit);
	}
	
	public PrimeClient(final String hostname) throws Exception {
		transferPrimes(InetAddress.getByName(hostname), DEFAULT_PORT, DEFAULT_LIMIT);
	}
	
	public PrimeClient(final int iLimit) throws Exception {
		transferPrimes(DEFAULT_HOST, DEFAULT_PORT, iLimit);
	}
	
	public PrimeClient() throws Exception {
		transferPrimes(DEFAULT_HOST, DEFAULT_PORT, DEFAULT_LIMIT);
	}
	
	private void transferPrimes(final InetAddress iaAddress, final int iPort, final int iLimit) throws Exception {
		final DatagramSocket dsSocket = new DatagramSocket();
		final byte[] baOut = new byte[1];
		byte[] baIn;
		DatagramPacket packet;
		int[] iaNums;
		int i;
		final List<Integer> liPrimes = new ArrayList<Integer>();
		
		baOut[0] = 2;
		try {
			packet = new DatagramPacket(baOut, baOut.length, iaAddress, iPort);
			dsSocket.send(packet);
			
			baIn = new byte[PACKET_SIZE * 4];
			packet = new DatagramPacket(baIn, baIn.length);
			dsSocket.receive(packet);
			
			if (packet.getLength() == 1) {
				throw new Exception("Server did not generate enough primes to handle this request");
			}
			iaNums = ToInts(packet.getData());
			for (i = 0; i < iaNums.length && iaNums[i] <= iLimit; i++) {
				liPrimes.add(iaNums[i]);
			}
			baOut[0] = 3;
			// Keep looping until above the limit.
			while (i == PACKET_SIZE) {
				packet = new DatagramPacket(baOut, baOut.length, iaAddress, iPort);
				dsSocket.send(packet);
				
				baIn = new byte[PACKET_SIZE * 4];
				packet = new DatagramPacket(baIn, baIn.length);
				dsSocket.receive(packet);
				
				if (packet.getLength() == 1) {
					throw new Exception("Server did not generate enough primes to handle this request");
				}
				
				iaNums = ToInts(packet.getData());
				
				for (i = 0; i < PACKET_SIZE && iaNums[i] <= iLimit; i++) {
					liPrimes.add(iaNums[i]);
				}
			}
			
			_iaPrimes = convertIntegers(liPrimes);
			
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			dsSocket.close();
		}
	}
	
	private static int[] ToInts(final byte[] baData) {
		final int[] iaNums = new int[baData.length / 4];
		for (int i = 0; i < iaNums.length; i++) {
			iaNums[i] = byteArrayToInt(baData, i * 4);
		}
		return iaNums;
	}
	
	public static int byteArrayToInt(final byte[] b, final int offset) {
		int value = (b[3 + offset] & 0xFF) << 24;
		value += (b[2 + offset] & 0xFF) << 16;
		value += (b[1 + offset] & 0xFF) << 8;
		value += b[0 + offset] & 0xFF;
		return value;
	}
	
	public static byte[] IntToByteArray(final int value) {
		final byte[] baRet = new byte[4];
		baRet[0] = (byte) (value & 0xFF);
		baRet[1] = (byte) (value >> 8 & 0xFF);
		baRet[2] = (byte) (value >> 16 & 0xFF);
		baRet[3] = (byte) (value >> 24 & 0xFF);
		
		return baRet;
	}
	
	public static int[] convertIntegers(final List<Integer> integers) {
		final int[] ret = new int[integers.size()];
		final Iterator<Integer> iterator = integers.iterator();
		for (int i = 0; i < ret.length; i++) {
			ret[i] = iterator.next().intValue();
		}
		return ret;
	}
}

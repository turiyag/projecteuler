import java.io.*;
import java.net.*;

public class PrimeClient {
	static final public int	PACKET_SIZE		= 8192;
	int[]					iaPrimes;
	
	static final public int	PRIME_1SEC		= 197438;
	static final public int	PRIME_3SEC		= 499349;
	static final public int	PRIME_5SEC		= 749724;
	static final public int	PRIME_10SEC		= 1248507;
	static final public int	PRIME_20SEC		= 1986321;
	static final public int	PRIME_30SEC		= 2628901;
	static final public int	PRIME_50SEC		= 3776991;
	static final public int	PRIME_100SEC	= 6204466;
	static final public int	PRIME_200SEC	= 10192081;
	static final public int	PRIME_400SEC	= 16742539;
	static final public int	PRIME_800SEC	= 27502981;
	static final public int	PRIME_1600SEC	= 45179167;
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public PrimeClient(final int iCount) throws Exception {
		iaPrimes = new int[iCount];
		XfrPrimes();
	}
	
	public int GetPrime(final int i) {
		return iaPrimes[i];
	}
	
	public int GetCount() {
		return iaPrimes.length;
	}
	
	public void XfrPrimes() throws Exception {
		int iCount = 0;
		final DatagramSocket socket = new DatagramSocket();
		try {
			
			// send request
			final byte[] baOut = new byte[1];
			final InetAddress address = InetAddress.getByName("localhost");
			baOut[0] = 2;
			byte[] baIn;
			DatagramPacket packet;
			// get response
			int[] iaNums;
			
			packet = new DatagramPacket(baOut, baOut.length, address, 65521);
			socket.send(packet);
			if (PACKET_SIZE > iaPrimes.length) {
				baIn = new byte[PACKET_SIZE * 4];
				packet = new DatagramPacket(baIn, baIn.length);
				socket.receive(packet);
				if (packet.getLength() == 1) {
					throw new Exception("Server did not generate enough primes");
				}
				iaNums = ToInts(packet.getData());
				
				for (int i = 0; i < iaPrimes.length; i++) {
					iaPrimes[i] = iaNums[i];
				}
			} else {
				baOut[0] = 3;
				while (iCount < iaPrimes.length - PACKET_SIZE) {
					baIn = new byte[PACKET_SIZE * 4];
					packet = new DatagramPacket(baIn, baIn.length);
					socket.receive(packet);
					if (packet.getLength() == 1) {
						throw new Exception("Server did not generate enough primes");
					}
					iaNums = ToInts(packet.getData());
					
					for (int i = 0; i < PACKET_SIZE; i++) {
						iaPrimes[i + iCount] = iaNums[i];
					}
					
					packet = new DatagramPacket(baOut, baOut.length, address, 65521);
					socket.send(packet);
					
					iCount += PACKET_SIZE;
				}
				
				baIn = new byte[PACKET_SIZE * 4];
				packet = new DatagramPacket(baIn, baIn.length);
				socket.receive(packet);
				iaNums = ToInts(packet.getData());
				
				for (int i = 0; i + iCount < iaPrimes.length; i++) {
					iaPrimes[i + iCount] = iaNums[i];
				}
			}
			
			// display response
			
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			socket.close();
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
}
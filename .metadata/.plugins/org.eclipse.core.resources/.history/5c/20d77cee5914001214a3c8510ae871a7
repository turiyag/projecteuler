package primenumber;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import extras.ByteOperations;

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
		int iCount = 0;
		byte[] baRequestedOffset;
		byte[] baReceivedPrimes;
		DatagramPacket dpPacket;
		int[] iaNums;
		int i = PACKET_SIZE;
		final List<Integer> liPrimes = new ArrayList<Integer>();
		
		try {
			// Keep looping until above the limit.
			while (i == PACKET_SIZE) {
				baRequestedOffset = ByteOperations.intToFourByteArray(iCount);
				dpPacket = new DatagramPacket(baRequestedOffset, baRequestedOffset.length, iaAddress, iPort);
				dsSocket.send(dpPacket);
				
				baReceivedPrimes = new byte[PACKET_SIZE * 4];
				dpPacket = new DatagramPacket(baReceivedPrimes, baReceivedPrimes.length);
				dsSocket.receive(dpPacket);
				
				if (dpPacket.getLength() == 1) {
					throw new Exception("Server did not generate enough primes to handle this request");
				}
				
				iaNums = ByteOperations.byteArrayToIntArray(dpPacket.getData());
				
				for (i = 0; i < PACKET_SIZE && iaNums[i] <= iLimit; i++) {
					liPrimes.add(iaNums[i]);
				}
				iCount += PACKET_SIZE;
			}
			
			_iaPrimes = ByteOperations.listIntToIntArray(liPrimes);
			
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			dsSocket.close();
		}
	}
}

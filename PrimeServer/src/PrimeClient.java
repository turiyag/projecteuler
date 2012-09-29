import java.io.*;
import java.net.*;
import java.util.ArrayList;
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
			iaNums = ByteOperations.byteArrayToIntArray(packet.getData());
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
				
				iaNums = ByteOperations.byteArrayToIntArray(packet.getData());
				
				for (i = 0; i < PACKET_SIZE && iaNums[i] <= iLimit; i++) {
					liPrimes.add(iaNums[i]);
				}
			}
			
			_iaPrimes = ByteOperations.listIntToIntArray(liPrimes);
			
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			dsSocket.close();
		}
	}
}

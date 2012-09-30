import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class PrimeServer extends Primes {
	static final public int	PACKET_SIZE		= 8192;
	static final public int	DEFAULT_PORT	= 65521;
	static final int		DEFAULT_LIMIT	= 2000000;
	
	private DatagramSocket	_socket			= null;
	
	public PrimeServer() throws IOException {
		this(DEFAULT_LIMIT);
	}
	
	public PrimeServer(final int iLimit) throws IOException {
		super(iLimit);
		System.out.println("Primes up to " + getCount() + " have been generated.");
		System.out.println("Listening on " + DEFAULT_PORT);
		startListening(DEFAULT_PORT);
	}
	
	private void startListening(int port) throws SocketException {
		InetAddress address;
		DatagramPacket packet;
		final byte[] baRequestedOffset = new byte[4];
		int iRequestedOffset;
		int[] iaPrimes;
		byte[] baPrimes;
		_socket = new DatagramSocket(port);
		
		while (true) {
			try {
				packet = new DatagramPacket(baRequestedOffset, baRequestedOffset.length);
				_socket.receive(packet);
				iRequestedOffset = ByteOperations.fourByteArrayToInt(baRequestedOffset);
				try {
					iaPrimes = getPrimes(iRequestedOffset, iRequestedOffset + PACKET_SIZE);
					baPrimes = ByteOperations.intArraytoByteArray(iaPrimes);
				} catch (final Exception e) {
					baPrimes = new byte[1];
					baPrimes[0] = 0;
				}
				// send the response to the client at "address" and "port"
				address = packet.getAddress();
				port = packet.getPort();
				packet = new DatagramPacket(baPrimes, baPrimes.length, address, port);
				_socket.send(packet);
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}
}

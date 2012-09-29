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
		
		int iCount = 0;
		InetAddress address;
		DatagramPacket packet;
		byte[] buf;
		_socket = new DatagramSocket(port);
		
		while (true) {
			try {
				buf = new byte[1];
				packet = new DatagramPacket(buf, buf.length);
				_socket.receive(packet);
				if (buf[0] == 2) {
					buf = ByteOperations.intArraytoByteArray(getPrimes(0, PACKET_SIZE));
					iCount = PACKET_SIZE;
				} else {
					buf = ByteOperations.intArraytoByteArray(getPrimes(iCount, iCount + PACKET_SIZE));
					iCount += PACKET_SIZE;
				}
				
				// send the response to the client at "address" and "port"
				address = packet.getAddress();
				port = packet.getPort();
				if (buf == null) {
					buf = new byte[1];
					buf[0] = 0;
				}
				packet = new DatagramPacket(buf, buf.length, address, port);
				_socket.send(packet);
				// socket.close();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}
}

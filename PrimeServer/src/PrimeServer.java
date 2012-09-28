import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class PrimeServer extends Primes {
	static final public int	PACKET_SIZE		= 8192;
	static final public int	DEFAULT_LIMIT	= 19629383;
	static final public int	DEFAULT_PORT	= 65521;
	
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
					buf = ToBytes(getPrimes(0, PACKET_SIZE));
					iCount = PACKET_SIZE;
				} else {
					buf = ToBytes(getPrimes(iCount, iCount + PACKET_SIZE));
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
	
	private static byte[] ToBytes(final int[] iaNums) {
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
	
	public static int byteArrayToInt(final byte[] b, final int offset) {
		int value = (b[3 + offset] & 0xFF) << 24;
		value += (b[2 + offset] & 0xFF) << 16;
		value += (b[1 + offset] & 0xFF) << 8;
		value += b[0 + offset] & 0xFF;
		return value;
	}
}

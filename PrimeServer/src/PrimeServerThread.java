import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class PrimeServerThread extends Thread {
	static final public int		PACKET_SIZE	= 8192;
	protected DatagramSocket	socket		= null;
	protected BufferedReader	in			= null;
	
	public PrimeServerThread() throws IOException {
		this("PrimeServerThread");
	}
	
	public PrimeServerThread(final String name) throws IOException {
		super(name);
		socket = new DatagramSocket(65521);
	}
	
	@Override
	public void run() {
		final Primes p = new Primes(Primes.PRIME_10SEC);
		int iCount = 0;
		InetAddress address;
		int port;
		DatagramPacket packet;
		byte[] buf;
		
		System.out.println("Primes up to " + p.GetCount() + " have been generated.");
		while (true) {
			try {
				buf = new byte[1];
				packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				if (buf[0] == 2) {
					buf = ToBytes(p.GetPrimes(0, PACKET_SIZE));
					iCount = PACKET_SIZE;
				} else {
					buf = ToBytes(p.GetPrimes(iCount, iCount + PACKET_SIZE));
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
				socket.send(packet);
				// socket.close();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private byte[] ToBytes(final int[] iaNums) {
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
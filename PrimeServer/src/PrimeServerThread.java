import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class PrimeServerThread extends Thread {
	protected DatagramSocket socket = null;
	protected BufferedReader in = null;

	public PrimeServerThread() throws IOException {
		this("PrimeServerThread");
	}

	public PrimeServerThread(String name) throws IOException {
		super(name);
		socket = new DatagramSocket(65521);
	}

	public void run() {
		// boolean bKeepGoing=true;
		Primes p = new Primes(Primes.PRIME_1600SEC);
		int iSize = 8192;
		int[] iaPrimes;
		int iCount = 0;

		System.out.println("Done!");
		while (true) {
			try {
				byte[] buf = new byte[1];
				// receive request
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				if (buf[0] == 2) {
					iaPrimes = new int[iSize];
					for (int i = 0; i < iaPrimes.length; i++) {
						iaPrimes[i] = p.GetPrime(i);
					}
					iCount = iSize;
					buf = ToBytes(iaPrimes);
				} else {
					iaPrimes = new int[iSize];
					for (int i = 0; i < iaPrimes.length; i++) {
						iaPrimes[i] = p.GetPrime(i + iCount);
					}
					iCount += iSize;
					buf = ToBytes(iaPrimes);
				}

				// send the response to the client at "address" and "port"
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				packet = new DatagramPacket(buf, buf.length, address, port);
				socket.send(packet);
				// socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private byte[] ToBytes(int[] iaNums) {
		byte[] baData = new byte[iaNums.length * 4];
		for (int i = 0; i < iaNums.length; i++) {
			baData[(i * 4) + 0] = (byte) (iaNums[i] & 0xFF);
			baData[(i * 4) + 1] = (byte) ((iaNums[i] & 0xFF00) / 0x100);
			baData[(i * 4) + 2] = (byte) ((iaNums[i] & 0xFF0000) / 0x10000);
			baData[(i * 4) + 3] = (byte) ((iaNums[i] & 0xFF000000) / 0x1000000);
		}
		return baData;
	}

	public static int byteArrayToInt(byte[] b, int offset) {
		int value = (b[3 + offset] & 0xFF) << 24;
		value += (b[2 + offset] & 0xFF) << 16;
		value += (b[1 + offset] & 0xFF) << 8;
		value += (b[0 + offset] & 0xFF);
		return value;
	}
}

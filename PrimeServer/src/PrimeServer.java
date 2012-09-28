import java.io.*;

public class PrimeServer {
	public static void main(String[] args) throws IOException {
		new PrimeServerThread().start();
	}
}

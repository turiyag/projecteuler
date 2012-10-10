import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Files {
	public Files() {

	}

	// Loads an file into an array of bytes
	public static byte[] LoadBytes(String sPath) {
		File f = new File(sPath);
		byte[] b = new byte[(int) f.length()];
		FileInputStream in = null;
		try {
			in = new FileInputStream(sPath);
			in.read(b);
		} catch (IOException e) {
		} finally {
			try {
				in.close();
			} catch (IOException e) {
			}
		}
		return b;
	}

	// Saves an array of bytes into a file
	public static void SaveBytes(String sPath, byte[] b) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(sPath);
			out.write(b);
		} catch (IOException e) {
		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
	}

	// Read a single line of input from STDIO
	public static String ReadInput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String sIn = null;

		try {
			sIn = br.readLine();
		} catch (IOException ioe) {
			System.out.println("I/O error");
			System.exit(1);
		}

		return sIn;
	}

	// Simple function to read a file from STDIN into a LinkList
	public static LinkList ReadStdIn() {
		String sTemp = "";
		LinkList llAll = new LinkList();
		try {
			// Try opening a STDIN reader
			BufferedReader brStdIn = new BufferedReader(new InputStreamReader(System.in));
			// Then read lines until the end of the file
			do {
				sTemp = brStdIn.readLine();
				llAll.add(sTemp);
			} while (sTemp != null);
			// Remove the last, null element
			llAll.remove();
			// Then return the file as a single string
			return llAll;
		} catch (IOException e) {
			return null;
		}
	}

	// Simple function to read a file into a LinkList
	public static LinkList ReadFile(String sPath) {
		String sTemp = "";
		LinkList llAll = new LinkList();
		BufferedReader brStdIn;
		try {
			// Try opening a file reader
			brStdIn = new BufferedReader(new FileReader(new File(sPath)));
			// Then read lines until the end of the file
			do {
				sTemp = brStdIn.readLine();
				llAll.add(sTemp);
			} while (sTemp != null);
			// Remove the last, null element
			llAll.remove();
			// Then return the file as the LinkList
			brStdIn.close();
			return llAll;
		} catch (IOException e) {
			return null;

		} finally {

		}
	}

	public static LinkList SplitString(String sSrc, char cDelim) {
		LinkList llSplit = new LinkList();
		int iLast = 0;
		int i = 0;
		for (; i < sSrc.length(); i++) {
			if (sSrc.charAt(i) == cDelim) {
				llSplit.add(sSrc.substring(iLast, i));
				iLast = i + 1;
			}
		}
		llSplit.add(sSrc.substring(iLast));

		return llSplit;
	}
}

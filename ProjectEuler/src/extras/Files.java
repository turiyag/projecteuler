package extras;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

import datastructures.LinkList;

public class Files {
	public Files() {
		
	}
	
	// Loads an file into an array of bytes
	public static byte[] LoadBytes(final String sPath) {
		final File f = new File(sPath);
		final byte[] b = new byte[(int) f.length()];
		FileInputStream in = null;
		try {
			in = new FileInputStream(sPath);
			in.read(b);
		} catch (final IOException e) {
		} finally {
			try {
				in.close();
			} catch (final IOException e) {
			}
		}
		return b;
	}
	
	public static String readFileAsString(String filePath) throws IOException {
		StringBuffer fileData = new StringBuffer();
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
		}
		reader.close();
		return fileData.toString();
	}
	
	// Saves an array of bytes into a file
	public static void SaveBytes(final String sPath, final byte[] b) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(sPath);
			out.write(b);
		} catch (final IOException e) {
		} finally {
			try {
				out.close();
			} catch (final IOException e) {
			}
		}
	}
	
	// Read a single line of input from STDIO
	public static String ReadInput() {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String sIn = null;
		
		try {
			sIn = br.readLine();
		} catch (final IOException ioe) {
			System.out.println("I/O error");
			System.exit(1);
		}
		
		return sIn;
	}
	
	// Simple function to read a file from STDIN into a LinkList
	public static LinkList ReadStdIn() {
		String sTemp = "";
		final LinkList llAll = new LinkList();
		try {
			// Try opening a STDIN reader
			final BufferedReader brStdIn = new BufferedReader(new InputStreamReader(System.in));
			// Then read lines until the end of the file
			do {
				sTemp = brStdIn.readLine();
				llAll.add(sTemp);
			} while (sTemp != null);
			// Remove the last, null element
			llAll.remove();
			// Then return the file as a single string
			return llAll;
		} catch (final IOException e) {
			return null;
		}
	}
	
	// Simple function to read a file into a LinkList
	public static LinkList ReadFile(final String sPath) {
		String sTemp = "";
		BufferedReader brStdIn;
		final LinkList llAll = new LinkList();
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
			brStdIn.close();
			// Then return the file as the LinkList
			return llAll;
		} catch (final IOException e) {
			return null;
		} finally {
		}
	}
	
	public static LinkList SplitString(final String sSrc, final char cDelim) {
		final LinkList llSplit = new LinkList();
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
	
	public static LinkedList<LinkedList<String>> SplitLinesAndDelim(final String sSrc, final char cElemDelim, final String sLineDelim) {
		LinkedList<LinkedList<String>> llsLines = new LinkedList<LinkedList<String>>();
		LinkedList<String> lsLine = new LinkedList<String>();
		
		int iLast = 0;
		int i = 0;
		for (; i < sSrc.length() - sLineDelim.length(); i++) {
			if (sSrc.charAt(i) == cElemDelim) {
				lsLine.add(sSrc.substring(iLast, i));
				iLast = i + 1;
			} else if (sSrc.substring(i, i + sLineDelim.length()).equals(sLineDelim)) {
				lsLine.add(sSrc.substring(iLast, i));
				iLast = i + sLineDelim.length();
				llsLines.add(lsLine);
				lsLine = new LinkedList<String>();
			}
		}
		lsLine.add(sSrc.substring(iLast));
		llsLines.add(lsLine);
		return llsLines;
	}
	
	public static int[][] MakeArray(final LinkedList<LinkedList<String>> llsLines) {
		int iaaLines[][];
		int iLineIndex;
		int iElemIndex;
		LinkedList<String> lsLine;
		Iterator<LinkedList<String>> itLines;
		Iterator<String> itLine;
		iaaLines = new int[llsLines.size()][llsLines.element().size()];
		itLines = llsLines.iterator();
		iLineIndex = 0;
		while (itLines.hasNext()) {
			lsLine = itLines.next();
			itLine = lsLine.iterator();
			iElemIndex = 0;
			while (itLine.hasNext()) {
				iaaLines[iLineIndex][iElemIndex] = Integer.valueOf(itLine.next());
				iElemIndex++;
			}
			iLineIndex++;
		}
		return iaaLines;
	}
}

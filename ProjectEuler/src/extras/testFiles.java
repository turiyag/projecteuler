package extras;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class testFiles {
	
	@Test
	public void test() {
		LinkedList<LinkedList<String>> llsLines = new LinkedList<LinkedList<String>>();
		LinkedList<String> lsLine = new LinkedList<String>();
		int[][] iaaMatrix;
		String sFile;
		
		lsLine.add("1");
		lsLine.add("2");
		lsLine.add("3");
		lsLine.add("3");
		lsLine.add("3");
		lsLine.add("3");
		llsLines.add(lsLine);
		lsLine = new LinkedList<String>();
		lsLine.add("4");
		lsLine.add("5");
		lsLine.add("6");
		lsLine.add("3");
		lsLine.add("3");
		lsLine.add("3");
		llsLines.add(lsLine);
		lsLine = new LinkedList<String>();
		lsLine.add("7");
		lsLine.add("8");
		lsLine.add("9");
		lsLine.add("3");
		lsLine.add("3");
		lsLine.add("3");
		llsLines.add(lsLine);
		lsLine = new LinkedList<String>();
		lsLine.add("7");
		lsLine.add("8");
		lsLine.add("9");
		lsLine.add("3");
		lsLine.add("3");
		lsLine.add("3");
		llsLines.add(lsLine);
		iaaMatrix = Files.MakeArray(llsLines);
		assertEquals(1, iaaMatrix[0][0]);
		assertEquals(2, iaaMatrix[0][1]);
		assertEquals(3, iaaMatrix[0][2]);
		assertEquals(9, iaaMatrix[3][2]);
		
		try {
			sFile = Files.readFileAsString("matrix.txt");
			llsLines = Files.SplitLinesAndDelim(sFile, ',', "\r\n");
			iaaMatrix = Files.MakeArray(llsLines);
			assertEquals(4445, iaaMatrix[0][0]);
			assertEquals(2697, iaaMatrix[0][1]);
			assertEquals(5870, iaaMatrix[0][79]);
			assertEquals(1096, iaaMatrix[1][0]);
			assertEquals(7981, iaaMatrix[79][79]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

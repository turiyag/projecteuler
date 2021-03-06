package problem;

import java.io.IOException;
import java.util.LinkedList;

import extras.Files;

public class Problem81 {
	static int[][]	iaaMin;
	static int[][]	iaaMatrix;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sFile;
		int iMin;
		try {
			sFile = Files.readFileAsString("matrix.txt");
			LinkedList<LinkedList<String>> llsLines = Files.SplitLinesAndDelim(sFile, ',', "\r\n");
			iaaMatrix = Files.MakeArray(llsLines);
			iaaMin = new int[80][80];
			for (int i = 0; i < 80; i++) {
				iaaMin[i][0] = iaaMatrix[i][0];
			}
			for (int iX = 1; iX < 80; iX++) {
				for (int iY = 0; iY < 80; iY++) {
					iaaMin[iY][iX] = Math.min(minGoingDown(iX, iY), minGoingUp(iX, iY));
				}
			}
			iMin = iaaMin[0][79];
			for (int iY = 1; iY < 80; iY++) {
				if (iaaMin[iY][79] < iMin) {
					iMin = iaaMin[iY][79];
				}
			}
			System.out.println("It's " + iMin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static int minGoingUp(int iX, int iY) {
		if (iY <= 0) {
			return iaaMin[iY][iX - 1] + iaaMatrix[iY][iX];
		} else {
			return Math.min(iaaMin[iY][iX - 1] + iaaMatrix[iY][iX], minGoingUp(iX, iY - 1) + iaaMatrix[iY][iX]);
		}
	}
	
	private static int minGoingDown(int iX, int iY) {
		if (iY >= 79) {
			return iaaMin[iY][iX - 1] + iaaMatrix[iY][iX];
		} else {
			return Math.min(iaaMin[iY][iX - 1] + iaaMatrix[iY][iX], minGoingDown(iX, iY + 1) + iaaMatrix[iY][iX]);
		}
	}
}

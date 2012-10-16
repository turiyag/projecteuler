package problem;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

import specialnumbers.IntListing;

import datastructures.LinkList;

import extras.Files;

public class Problem82 {
	
	static int[][]					_iaaMin;
	static int[][]					_iaaMatrix;
	static int[][]					_iaaVisited;
	static LinkedList<IntListing>	_illLower;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sFile;
		int iMin;
		int iX;
		int iY;
		IntListing il;
		try {
			sFile = Files.readFileAsString("matrix.txt");
			LinkedList<LinkedList<String>> llsLines = Files.SplitLinesAndDelim(sFile, ',', "\r\n");
			_iaaMatrix = Files.MakeArray(llsLines);
			_iaaMin = new int[80][80];
			_iaaVisited = new int[80][80];
			_iaaMin[0][0] = _iaaMatrix[0][0];
			_illLower = new LinkedList<IntListing>();
			queueUp(_iaaMatrix[0][0], 1, 0);
			queueUp(_iaaMatrix[0][0], 0, 1);
			while (!_illLower.isEmpty()) {
				il = getNext();
				iX = il.numberOf(2);
				iY = il.numberOf(1);
				iMin = il.numberOf(3) + _iaaMatrix[iX][iY];
				if (_iaaMin[iX][iY] == 0) {
					_iaaMin[iX][iY] = iMin;
				}
				queueUp(iMin, iY + 1, iX);
				queueUp(iMin, iY - 1, iX);
				queueUp(iMin, iY, iX + 1);
				queueUp(iMin, iY, iX - 1);
			}
			for (iY = 0; iY < 80; iY++) {
				for (iX = 0; iX < 80; iX++) {
					System.out.print(_iaaMin[iY][iX] + ",");
				}
				System.out.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void queueUp(int iLower, int iY, int iX) {
		if (iY < 0 || iY > 79 || iX < 0 || iX > 79) {
			return;
		} else if (iX == 79 && iY == 79) {
			System.out.println(iLower);
		} else if (_iaaMin[iX][iY] != 0) {
			return;
		} else {
			IntListing il = new IntListing(3);
			il.add(3, iLower);
			il.add(2, iX);
			il.add(1, iY);
			_illLower.add(il);
		}
	}
	
	private static IntListing getNext() {
		Collections.sort(_illLower);
		return _illLower.pop();
	}
}

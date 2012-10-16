package problem;

import java.util.TreeMap;

import specialnumbers.IntListing;
import games.Monopoly;

public class Problem84 {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Monopoly m = new Monopoly();
		IntListing il = new IntListing(40);
		TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();
		int iSquare;
		for (int i = 0; i < Integer.MAX_VALUE / 100000; i++) {
			m.runTurn();
			il.add(m.getPlayerPosition());
		}
		for (int i = 0; i < 40; i++) {
			tm.put(il.numberOf(i), i);
		}
		System.out.println("It's ");
		for (int i = 0; i < 3; i++) {
			iSquare = tm.lastEntry().getValue();
			System.out.print(iSquare);
			tm.remove(tm.lastKey());
		}
		
	}
}

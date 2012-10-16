package extras;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import specialnumbers.IntListing;

public class testSums {
	
	@Test
	public void test() {
		Sums s = new Sums(150);
		LinkedList<IntListing> lil;
		lil = s.allSums(1);
		assertEquals("[1]", lil.toString());
		lil = s.allSums(2);
		assertEquals("[1+1, 2]", lil.toString());
		lil = s.allSums(3);
		assertEquals("[1+1+1, 2+1, 3]", lil.toString());
		lil = s.allSums(4);
		assertEquals("[1+1+1+1, 2+1+1, 2+2, 3+1, 4]", lil.toString());
		
		assertEquals(1, s.allSumsUnder(1, 1));
		assertEquals(2, s.allSumsUnder(2, 2));
		assertEquals(3, s.allSumsUnder(3, 3));
		assertEquals(5, s.allSumsUnder(4, 4));
		assertEquals(7, s.allSumsUnder(5, 5));
		
		assertEquals(0, s.allPrimeSumsUnder(1, 1));
		assertEquals(1, s.allPrimeSumsUnder(2, 2));
		assertEquals(1, s.allPrimeSumsUnder(3, 3));
		assertEquals(1, s.allPrimeSumsUnder(4, 4));
		assertEquals(2, s.allPrimeSumsUnder(5, 5));
		assertEquals(2, s.allPrimeSumsUnder(6, 6));
		assertEquals(3, s.allPrimeSumsUnder(7, 7));
		assertEquals(3, s.allPrimeSumsUnder(8, 8));
		
	}
	
}

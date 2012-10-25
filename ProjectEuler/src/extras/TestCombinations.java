package extras;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class TestCombinations {

	@Test
	public void test() {
		LinkedList<LinkedList<Integer>> ill = new LinkedList<LinkedList<Integer>>();
		LinkedList<Integer> il = new LinkedList<Integer>();
		il.add(2);
		il.add(3);
		il.add(5);
		il.add(7);
		ill.add(il);
		il = new LinkedList<Integer>();
		il.add(2);
		il.add(3);
		il.add(5);
		il.add(7);
		System.out.println(ill.contains(il));
		//System.out.println( Combinations.productCombinations(il).toString());
	}

}

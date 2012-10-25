package extras;

import java.util.Iterator;
import java.util.LinkedList;

import specialnumbers.ByteOperations;

public class Combinations {
	public static LinkedList<LinkedList<Integer>> productCombinations(LinkedList<Integer> il){
		return productCombinations(il,0);
	}
	
	private static LinkedList<LinkedList<Integer>> productCombinations(LinkedList<Integer> il, int iSkipFirst){
		LinkedList<LinkedList<Integer>> illRet = new LinkedList<LinkedList<Integer>>();
		int[] ia = ByteOperations.listIntToIntArray(il);
		LinkedList<Integer> ilTemp;
		LinkedList<Integer> ilUniqueness;
		LinkedList<LinkedList<Integer>> illSubComb;
		int iSkip = iSkipFirst;
		
		illRet.add(il);
		if (ia.length == 1) {
			return illRet;
		} else {
			ilUniqueness = new LinkedList<Integer>();
			for (int i1 = 0; i1 < ia.length-1; i1++){
				for (int i2=i1+1; i2 < ia.length; i2++){
					if (iSkip > 0) {
						iSkip--;
					} else {
						if (!ilUniqueness.contains(ia[i1] * ia[i2])) {
							ilUniqueness.add(ia[i1] * ia[i2]);
							ilTemp = new LinkedList<Integer>();
							ilTemp.add(ia[i1]*ia[i2]);
							for(int iX = 0; iX < ia.length; iX++){
								if (iX != i1 && iX != i2){
									ilTemp.add(ia[iX]);
								}
							}
							illSubComb = productCombinations(ilTemp,iSkipFirst + (i1 * ia.length) + i2 -1);
							illRet.addAll(illSubComb);
						}
						
					}
				}
			}	
			return illRet;
		}
	}
}

package problem;

import specialnumbers.Sums;

public class Problem85 {
	private static int[] _iaSums;

	public static void main(String[] args) {
		int iClosestN = 0;
		int iClosestM = 0;
		int iMinDiff = Integer.MAX_VALUE;
		int iCurr;
		_iaSums = Sums.sumToEachN(2000);
		for (int n = 1; n<2000; n++){
			for(int m = 1; m < 2000; m++) {
				iCurr = _iaSums[n] * _iaSums[m];
				if (Math.abs(iCurr - 2000000) < iMinDiff){
					iMinDiff = Math.abs(iCurr - 2000000);
					iClosestN = n;
					iClosestM = m;
				}
			}
		}
		System.out.println("n: " + iClosestN + " m: "+ iClosestM + " t: "+ _iaSums[iClosestN] * _iaSums[iClosestM]);
	}
}

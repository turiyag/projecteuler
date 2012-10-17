package problem;

public class Problem86 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int iCount = 0;
		int iHyp;
		int iSqRt;
		int iX;
		for (iX = 1; iCount < 1000000; iX++){
			for (int iY = 1; iY <= iX; iY++){
				for (int iZ = 1; iZ <= iY; iZ++){
					iHyp = (iY+iZ)*(iY+iZ)+iX*iX;
					iSqRt = (int) Math.sqrt(iHyp);
					if (iHyp == iSqRt * iSqRt){
						iCount++;
					}
				}
			}
		}
		System.out.println("It's " + (iX-1));
	}
}

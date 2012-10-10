
public class OldCode {
/*

	public static void AddToTree(String sSource,int iNth,AVLTree tPrimes){
		String sTemp;
		for(int iPos1=0;iPos1<sSource.length()-1;iPos1++){
			sTemp=ReplaceX(sSource, iPos1, "*");
			tPrimes.AddWordPos(sTemp,iNth);
			AddToTree(sTemp,sSource.charAt(iPos1),iPos1+1,iNth,tPrimes);
		}
		for(int iPos1=0;iPos1<sSource.length()-1;iPos1++){
		}
	}
	public static void AddToTree(String sSource,char cMatch,int iStart,int iNth,AVLTree tPrimes){
		String sTemp;
		for(int iPos1=iStart;iPos1<sSource.length()-1;iPos1++){
			if(sSource.charAt(iPos1)==cMatch){
				sTemp=ReplaceX(sSource, iPos1, "*");
				tPrimes.AddWordPos(sTemp, iNth);
				AddToTree(sTemp, cMatch, iPos1+1,iNth,tPrimes);
			}
		}
	}
	
	public static String ReplaceX(String sSource,int iPos,String sReplace) {
		return sSource.substring(0, iPos) + sReplace + sSource.substring(iPos+1);
		
	}
	public static boolean CheckFive(int[][] iaPairs,int i1,int i2,int i3,int i4,int i5){
		if (FindMatch(iaPairs,i1,i2)&&FindMatch(iaPairs,i1,i3)&&FindMatch(iaPairs,i1,i4)&&FindMatch(iaPairs,i1,i5)){
			if (FindMatch(iaPairs,i2,i3)&&FindMatch(iaPairs,i2,i4)&&FindMatch(iaPairs,i2,i5)){
				if (FindMatch(iaPairs,i3,i4)&&FindMatch(iaPairs,i3,i5)){
					if (FindMatch(iaPairs,i4,i5)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean IsUnPrintable(char c){
		if ((int)(c)<=31){
			if ((int)(c)!=9) return true;
		}
		return false;
	}
	
	public static boolean FindMatch(int i1,int i2){
		if (Integer.toString(p.GetPrime(i1)).length()+Integer.toString(p.GetPrime(i2)).length()>9) return false;
		if (PrimeTestMillerRabin.GoodTest(Integer.parseInt(p.GetPrime(i1)+""+p.GetPrime(i2)))){
		if (PrimeTestMillerRabin.GoodTest(Integer.parseInt(p.GetPrime(i2)+""+p.GetPrime(i1)))){
			return true;
		}
		}
		return false;
	}

	public static boolean FindMatch(int[][] iaPairs,int iPrime, int iMatch){
		int i=1;
		for (;(iaPairs[iPrime][i]<iMatch)&&(iaPairs[iPrime][i]!=0);i++);
		if (iaPairs[iPrime][i]==iMatch) return true;
		return false;
	}
		
	static public String Format5GonRing(int i0, int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9){
		int iMin=Math.min(i1, Math.min(i3, Math.min(i5, Math.min(i7, i9))));
		String sRet;
		if (i1==iMin){
			sRet=""+i1+i0+i2+i3+i2+i4+i5+i4+i6+i7+i6+i8+i9+i8+i0;
		} else if (i3==iMin) {
			sRet=""+i3+i2+i4+i5+i4+i6+i7+i6+i8+i9+i8+i0+i1+i0+i2;
		} else if (i5==iMin) {
			sRet=""+i5+i4+i6+i7+i6+i8+i9+i8+i0+i1+i0+i2+i3+i2+i4;
		} else if (i7==iMin) {
			sRet=""+i7+i6+i8+i9+i8+i0+i1+i0+i2+i3+i2+i4+i5+i4+i6;
		} else {
			sRet=""+i9+i8+i0+i1+i0+i2+i3+i2+i4+i5+i4+i6+i7+i6+i8;
		}
		return sRet;
	}


*/
}

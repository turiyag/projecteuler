import java.math.BigInteger;


public class NumHelp {
	static BigInteger[] _laFactorial;

	public NumHelp(){
		
	}
	public static BigInteger itob(int i){
		return BigInteger.valueOf((long)(i));
	}
	public static BigInteger ltob(long l){
		return BigInteger.valueOf(l);
	}
	
	public static BigInteger choose(int n,int r){
		return factorial(n).divide(factorial(r).multiply(factorial(n-r)));
	}
	
	public static BigInteger factorial(int i){
		if (_laFactorial[i]!=null){
			return _laFactorial[i];
		} else if (i==0) {
				_laFactorial[i]=itob(1);
				return _laFactorial[i];
		} else {
			_laFactorial[i]=factorial(i-1).multiply(itob(i));
			return _laFactorial[i];
		}
	}
	
	public static int DigitSum(String s){
		char[] c=s.toCharArray();
		int iRet=0;
		for (int i=0; i<c.length;i++){
			iRet+=Character.getNumericValue(c[i]);
		}
		return iRet;
	}
	

}

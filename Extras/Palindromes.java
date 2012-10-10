
public class Palindromes {
	public Palindromes(){
		
	}
	
	static public boolean IsPalindrome(int i){
		return IsPalindrome(Integer.toString(i));
	}
	static public boolean IsPalindrome(String s){
		return IsPalindrome(s.toCharArray());
	}
	static public boolean IsPalindrome(char[] c){
		for(int i=0;i<c.length/2;i++){
			if(c[i]!=c[c.length-i-1]){
				return false;
			}
		}
		return true;
	}
	
	static public int Reverse(int iRev){
		char[] cSrc=Integer.toString(iRev).toCharArray();
		char[] cRet=new char[cSrc.length];
		for(int i=0;i<cSrc.length;i++){
			cRet[i]=cSrc[cSrc.length-i-1];
		}
		return Integer.parseInt(new String(cRet));
	}
	static public String Reverse(String s){
		char[] cSrc=s.toCharArray();
		char[] cRet=new char[cSrc.length];
		for(int i=0;i<cSrc.length;i++){
			cRet[i]=cSrc[cSrc.length-i-1];
		}
		return new String(cRet);
	}
}

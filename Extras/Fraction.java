
public class Fraction {
	int _iNum;
	int _iDen;
	
	public Fraction(){
		
	}
	public Fraction(int iNum,int iDen){
		_iNum=iNum;
		_iDen=iDen;
	}
	public Fraction(Fraction f){
		_iNum=f._iNum;
		_iDen=f._iDen;
	}
	
	public void Add(int i){
		_iNum+=i*_iDen;
	}
	
	public static Fraction Add(Fraction f, int i){
		Fraction fRet=new Fraction(f);
		fRet._iNum+=i*fRet._iDen;
		return fRet;
	}
	
	public void Invert(){
		_iNum=_iNum^_iDen;
		_iDen=_iNum^_iDen;
		_iNum=_iNum^_iDen;
	}

	public static void Invert(Fraction f){
		f._iNum=f._iNum^f._iDen;
		f._iDen=f._iNum^f._iDen;
		f._iNum=f._iNum^f._iDen;
	}

	public void ReduceFrac(Primes p){
		for(int i=0;p.GetPrime(i)<=_iNum;i++){
			if((_iNum%p.GetPrime(i))==0){
			if((_iDen%p.GetPrime(i))==0){
				_iNum/=p.GetPrime(i);
				_iDen/=p.GetPrime(i);
				i--;
			}
			}
		}
	}
	
	public static Fraction ReduceFrac(Fraction f,Primes p){
		Fraction fFrac=new Fraction(f);
		for(int i=0;p.GetPrime(i)<=fFrac._iNum;i++){
			if((fFrac._iNum%p.GetPrime(i))==0){
			if((fFrac._iDen%p.GetPrime(i))==0){
				fFrac._iNum/=p.GetPrime(i);
				fFrac._iDen/=p.GetPrime(i);
				i--;
			}
			}
		}
		return fFrac;
	}

	public void print(){
		System.out.print(_iNum+"/"+_iDen);
	}

	public void println(){
		System.out.println(_iNum+"/"+_iDen);
	}
	
	static public Fraction CalcConvFrac(int iConv,int[] iaSeq){
		Fraction flRet=new Fraction(iaSeq[iConv],1);
		for (int i=iConv-1;i>=0;i--){
			flRet.Invert();
			flRet.Add(iaSeq[i]);
		}
		return flRet;
	}
}

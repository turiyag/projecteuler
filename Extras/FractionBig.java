import java.math.BigInteger;


public class FractionBig {

	BigInteger _bNum;
	BigInteger _bDen;
	
	public FractionBig(){
		
	}
	public FractionBig(int iNum,int iDen){
		_bNum=NumHelp.itob(iNum);
		_bDen=NumHelp.itob(iDen);
	}
	public FractionBig(long iNum,long iDen){
		_bNum=BigInteger.valueOf(iNum);
		_bDen=BigInteger.valueOf(iDen);
	}
	public FractionBig(FractionBig f){
		_bNum=f._bNum.subtract(BigInteger.ZERO);
		_bDen=f._bDen.subtract(BigInteger.ZERO);
	}
	
	public void Add(int i){
		_bNum=_bNum.add(_bDen.multiply(NumHelp.itob(i)));
	}
	
	public void Add(long l){
		_bNum=_bNum.add(_bDen.multiply(BigInteger.valueOf(l)));
	}
	
	public static FractionBig Add(FractionBig f, int i){
		FractionBig fRet=new FractionBig(f);
		fRet._bNum=fRet._bNum.add(fRet._bDen.multiply(NumHelp.itob(i)));
		return fRet;
	}
	
	public void Invert(){
		_bNum=_bNum.xor(_bDen);
		_bDen=_bNum.xor(_bDen);
		_bNum=_bNum.xor(_bDen);
	}

	public static void Invert(FractionBig f){
		f._bNum=f._bNum.xor(f._bDen);
		f._bDen=f._bNum.xor(f._bDen);
		f._bNum=f._bNum.xor(f._bDen);
	}

	public void print(){
		System.out.print(_bNum.toString()+"/"+_bDen.toString());
	}

	public void println(){
		System.out.println(_bNum.toString()+"/"+_bDen.toString());
	}
	
	static public FractionBig CalcConvFrac(int iConv,int[] iaSeq){
		FractionBig flRet=new FractionBig(iaSeq[iConv],1);
		for (int i=iConv-1;i>=0;i--){
			flRet.Invert();
			flRet.Add(iaSeq[i]);
		}
		return flRet;
	}
}

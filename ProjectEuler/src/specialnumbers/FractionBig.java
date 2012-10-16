package specialnumbers;

import java.math.BigInteger;

public class FractionBig {
	
	BigInteger	_bNum;
	BigInteger	_bDen;
	
	public FractionBig() {
		
	}
	
	public FractionBig(final int iNum, final int iDen) {
		_bNum = NumHelp.itob(iNum);
		_bDen = NumHelp.itob(iDen);
	}
	
	public FractionBig(final long iNum, final long iDen) {
		_bNum = BigInteger.valueOf(iNum);
		_bDen = BigInteger.valueOf(iDen);
	}
	
	public FractionBig(final FractionBig f) {
		_bNum = f._bNum.subtract(BigInteger.ZERO);
		_bDen = f._bDen.subtract(BigInteger.ZERO);
	}
	
	public void Add(final int i) {
		_bNum = _bNum.add(_bDen.multiply(NumHelp.itob(i)));
	}
	
	public void Add(final long l) {
		_bNum = _bNum.add(_bDen.multiply(BigInteger.valueOf(l)));
	}
	
	public static FractionBig Add(final FractionBig f, final int i) {
		final FractionBig fRet = new FractionBig(f);
		fRet._bNum = fRet._bNum.add(fRet._bDen.multiply(NumHelp.itob(i)));
		return fRet;
	}
	
	public void Invert() {
		_bNum = _bNum.xor(_bDen);
		_bDen = _bNum.xor(_bDen);
		_bNum = _bNum.xor(_bDen);
	}
	
	public static void Invert(final FractionBig f) {
		f._bNum = f._bNum.xor(f._bDen);
		f._bDen = f._bNum.xor(f._bDen);
		f._bNum = f._bNum.xor(f._bDen);
	}
	
	public void print() {
		System.out.print(_bNum.toString() + "/" + _bDen.toString());
	}
	
	public void println() {
		System.out.println(_bNum.toString() + "/" + _bDen.toString());
	}
	
	static public FractionBig CalcConvFrac(final int iConv, final int[] iaSeq) {
		final FractionBig flRet = new FractionBig(iaSeq[iConv], 1);
		for (int i = iConv - 1; i >= 0; i--) {
			flRet.Invert();
			flRet.Add(iaSeq[i]);
		}
		return flRet;
	}
}

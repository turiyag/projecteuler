package extras;

import primenumber.Primes;

public class FractionLong {
	
	long	_iNum;
	long	_iDen;
	
	public FractionLong() {
		
	}
	
	public FractionLong(final int iNum, final int iDen) {
		_iNum = iNum;
		_iDen = iDen;
	}
	
	public FractionLong(final long iNum, final long iDen) {
		_iNum = iNum;
		_iDen = iDen;
	}
	
	public FractionLong(final FractionLong f) {
		_iNum = f._iNum;
		_iDen = f._iDen;
	}
	
	public void Add(final int i) {
		_iNum += i * _iDen;
	}
	
	public void Add(final long i) {
		_iNum += i * _iDen;
	}
	
	public static Fraction Add(final Fraction f, final int i) {
		final Fraction fRet = new Fraction(f);
		fRet._iNum += i * fRet._iDen;
		return fRet;
	}
	
	public void Invert() {
		_iNum = _iNum ^ _iDen;
		_iDen = _iNum ^ _iDen;
		_iNum = _iNum ^ _iDen;
	}
	
	public static void Invert(final Fraction f) {
		f._iNum = f._iNum ^ f._iDen;
		f._iDen = f._iNum ^ f._iDen;
		f._iNum = f._iNum ^ f._iDen;
	}
	
	public void ReduceFrac(final Primes p) {
		for (int i = 0; p.getPrime(i) <= _iNum; i++) {
			if (_iNum % p.getPrime(i) == 0) {
				if (_iDen % p.getPrime(i) == 0) {
					_iNum /= p.getPrime(i);
					_iDen /= p.getPrime(i);
					i--;
				}
			}
		}
	}
	
	public static FractionLong ReduceFrac(final FractionLong f, final Primes p) {
		final FractionLong fFrac = new FractionLong(f);
		for (int i = 0; p.getPrime(i) <= fFrac._iNum; i++) {
			if (fFrac._iNum % p.getPrime(i) == 0) {
				if (fFrac._iDen % p.getPrime(i) == 0) {
					fFrac._iNum /= p.getPrime(i);
					fFrac._iDen /= p.getPrime(i);
					i--;
				}
			}
		}
		return fFrac;
	}
	
	public void print() {
		System.out.print(_iNum + "/" + _iDen);
	}
	
	public void println() {
		System.out.println(_iNum + "/" + _iDen);
	}
	
	static public FractionLong CalcConvFrac(final int iConv, final long[] iaSeq) {
		final FractionLong flRet = new FractionLong(iaSeq[iConv], 1);
		for (int i = iConv - 1; i >= 0; i--) {
			flRet.Invert();
			flRet.Add(iaSeq[i]);
		}
		return flRet;
	}
}

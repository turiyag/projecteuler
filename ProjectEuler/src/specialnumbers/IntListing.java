package specialnumbers;

import java.util.Arrays;

public class IntListing implements Comparable<IntListing>, Cloneable {
	
	int[]	_iaInts;
	int		_iMax;
	
	public int getMax() {
		return _iMax;
	}
	
	public IntListing(int iMax) {
		super();
		_iaInts = new int[iMax + 1];
	}
	
	public void add(int iNum) {
		add(iNum, 1);
	}
	
	public void add(int iNum, int iCount) {
		if (iNum >= _iaInts.length) {
			throw new ArrayIndexOutOfBoundsException("iNum is larger than maximum allowed");
		} else {
			_iaInts[iNum] += iCount;
			if (iNum > _iMax) {
				_iMax = iNum;
			}
		}
	}
	
	public void add(IntListing ilOther) {
		int[] iaOther;
		if (ilOther.getMax() >= _iaInts.length) {
			throw new ArrayIndexOutOfBoundsException("iNum is larger than maximum allowed");
		} else {
			iaOther = ilOther.getInts();
			for (int i = 1; i <= ilOther.getMax(); i++) {
				_iaInts[i] += iaOther[i];
			}
			if (_iMax < ilOther.getMax())
				_iMax = ilOther.getMax();
		}
	}
	
	public int numberOf(int iNum) {
		if (iNum >= _iaInts.length) {
			throw new ArrayIndexOutOfBoundsException("iNum is larger than maximum allowed");
		} else {
			return _iaInts[iNum];
		}
	}
	
	@Override
	public int compareTo(IntListing ilOther) {
		int iThis = _iMax;
		int iOther = ilOther.getMax();
		if (iThis > iOther)
			return 1;
		if (iThis < iOther)
			return -1;
		if (iThis == 0)
			return 0;
		while (iThis > 0) {
			if (numberOf(iThis) > ilOther.numberOf(iThis))
				return 1;
			if (numberOf(iThis) < ilOther.numberOf(iThis))
				return -1;
			iThis--;
		}
		return 0;
	}
	
	public int[] getInts() {
		return _iaInts;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _iMax;
		result = prime * result + Arrays.hashCode(_iaInts);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntListing other = (IntListing) obj;
		if (_iMax != other._iMax)
			return false;
		if (!Arrays.equals(_iaInts, other._iaInts))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		int iCount;
		String sAll = new String();
		String sOne = new String();
		for (int i = _iMax; i > 0; i--) {
			sOne = "";
			for (iCount = 0; iCount < _iaInts[i]; iCount++) {
				sOne += i + "+";
			}
			if (!sOne.isEmpty()) {
				sAll += sOne;
			}
		}
		if (sAll.isEmpty())
			return sAll;
		return sAll.substring(0, sAll.length() - 1);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		IntListing ilNew = new IntListing(_iMax);
		ilNew.add(this);
		return ilNew;
	}
	
}

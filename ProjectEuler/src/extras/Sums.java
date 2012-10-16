package extras;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.lang.Math;

import primenumber.Primes;

import specialnumbers.IntListing;

public class Sums {
	private ArrayList<LinkedList<IntListing>>	_lilaSums;
	private long[][]							_iaaSumsUnder;
	private long[][]							_iaaPrimeSumsUnder;
	private Primes								_p;
	private int									_iMax;
	
	public Sums(int iMax) {
		super();
		_iMax = iMax + 1;
		_p = new Primes(_iMax);
		_iaaSumsUnder = new long[_iMax][_iMax];
		_iaaPrimeSumsUnder = new long[_iMax][_iMax];
		_lilaSums = new ArrayList<LinkedList<IntListing>>(_iMax);
		LinkedList<IntListing> lil = new LinkedList<IntListing>();
		
		IntListing il = new IntListing(_iMax);
		il.add(1);
		lil.add(il);
		_lilaSums.add(lil);
		
		lil = new LinkedList<IntListing>();
		il = new IntListing(_iMax);
		il.add(1, 2);
		lil.add(il);
		il = new IntListing(_iMax);
		il.add(2);
		lil.add(il);
		_lilaSums.add(lil);
	}
	
	public LinkedList<IntListing> allSums(int iNum) {
		LinkedList<IntListing> lilRet;
		LinkedList<IntListing> lilPrev;
		LinkedList<IntListing> lil2Prev;
		IntListing il;
		IntListing ilDup;
		Iterator<IntListing> iterator;
		
		lilRet = new LinkedList<IntListing>();
		if (iNum < 1) {
			return lilRet;
		} else if (_lilaSums.size() >= iNum) {
			return _lilaSums.get(iNum - 1);
		} else {
			for (int i = 1; i <= iNum / 2; i++) {
				lilPrev = allSums(iNum - i);
				lil2Prev = allSums(i);
				for (IntListing ilPrev : lilPrev) {
					for (IntListing il2Prev : lil2Prev) {
						il = new IntListing(_iMax);
						il.add(il2Prev);
						il.add(ilPrev);
						lilRet.add(il);
					}
				}
			}
			il = new IntListing(_iMax);
			il.add(iNum);
			lilRet.add(il);
			Collections.sort(lilRet);
			ilDup = new IntListing(_iMax);
			iterator = lilRet.iterator();
			while (iterator.hasNext()) {
				il = iterator.next();
				if (il.equals(ilDup)) {
					iterator.remove();
				} else {
					ilDup = il;
				}
			}
			_lilaSums.add(lilRet);
			return lilRet;
		}
	}
	
	public long allSumsUnder(int iNum, int iUnder) {
		long iTotalCount;
		iUnder = Math.min(iNum, iUnder);
		if (iNum == 1 || iUnder == 1) {
			return 1;
		} else if (iNum == 0) {
			return 1;
		} else if (_iaaSumsUnder[iNum][iUnder] != 0) {
			return _iaaSumsUnder[iNum][iUnder];
		} else {
			iTotalCount = 0;
			for (int i = iUnder; i > 0; i--) {
				iTotalCount += allSumsUnder(iNum - i, i);
			}
			_iaaSumsUnder[iNum][iUnder] = iTotalCount;
			return iTotalCount;
		}
	}
	
	public long allPrimeSumsUnder(int iNum, int iUnder) {
		long iTotalCount;
		int iBegin;
		iUnder = Math.min(iNum, iUnder);
		if (iNum == 1 || iUnder == 1) {
			return 0;
		} else if (iNum == 0) {
			return 1;
		} else if (_iaaPrimeSumsUnder[iNum][iUnder] != 0) {
			return _iaaPrimeSumsUnder[iNum][iUnder];
		} else {
			iTotalCount = 0;
			iBegin = _p.findClosestPrime(iUnder);
			if (_p.getPrime(iBegin) > iUnder) {
				iBegin--;
			}
			for (int i = iBegin; i >= 0; i--) {
				iTotalCount += allPrimeSumsUnder(iNum - _p.getPrime(i), _p.getPrime(i));
			}
			_iaaPrimeSumsUnder[iNum][iUnder] = iTotalCount;
			return iTotalCount;
		}
	}
}

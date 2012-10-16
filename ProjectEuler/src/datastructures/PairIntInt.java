package datastructures;

public class PairIntInt {
	public int	_i1;
	public int	_i2;
	
	public PairIntInt() {
		_i1 = 0;
		_i2 = 0;
	}
	
	public PairIntInt(final int i1, final int i2) {
		_i1 = i1;
		_i2 = i2;
	}
	
	public PairIntInt(final PairIntInt pii) {
		_i1 = pii._i1;
		_i2 = pii._i2;
	}
}

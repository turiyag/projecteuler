package extras;

public class Figurate {
	final static int	MIN_TRI	= 45;
	final static int	MAX_TRI	= 141;
	final static int	MIN_SQR	= 32;
	final static int	MAX_SQR	= 100;
	final static int	MIN_PEN	= 26;
	final static int	MAX_PEN	= 82;
	final static int	MIN_HEX	= 23;
	final static int	MAX_HEX	= 71;
	final static int	MIN_HEP	= 21;
	final static int	MAX_HEP	= 64;
	final static int	MIN_OCT	= 19;
	final static int	MAX_OCT	= 59;
	
	static public int Tri(final int n) {
		return n * (n + 1) / 2;
	}
	
	static public int Sqr(final int n) {
		return n * n;
	}
	
	static public int Pen(final int n) {
		return n * (3 * n - 1) / 2;
	}
	
	static public int Hex(final int n) {
		return n * (2 * n - 1);
	}
	
	static public int Hep(final int n) {
		return n * (5 * n - 3) / 2;
	}
	
	static public int Oct(final int n) {
		return n * (3 * n - 2);
	}
	
	// This has an error
	static public boolean IsCyclic(final int[] ia) {
		final String sa[] = new String[ia.length];
		for (int i = 0; i < ia.length; i++) {
			sa[i] = Integer.toString(ia[i]);
		}
		
		// This will not check if the object is linked in a cycle, just if it is
		// linked
		boolean bRet;
		for (int i = 0; i < sa.length; i++) {
			bRet = false;
			for (int j = 0; j < sa.length; j++) {
				if (i != j) {
					if (sa[i].substring(2, 4).equals(sa[j].substring(0, 2))) {
						bRet = true;
						sa[j] = "##" + sa[j].substring(2, 4);
						j = sa.length;
					}
				}
			}
			if (bRet == false) {
				return false;
			}
		}
		return true;
	}
	
	static public boolean HasOneCycle(final int[] ia) {
		final String sa[] = new String[ia.length];
		for (int i = 0; i < ia.length; i++) {
			sa[i] = Integer.toString(ia[i]);
		}
		
		// This will not check if the object is linked in a cycle, just if it is
		// linked
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (i != j) {
					if (sa[i].substring(2, 4).equals(sa[j].substring(0, 2))) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	static public boolean HasFiveCycles(final int[] ia) {
		final String sa[] = new String[ia.length];
		for (int i = 0; i < ia.length; i++) {
			sa[i] = Integer.toString(ia[i]);
		}
		
		int iSum = 0;
		// This will not check if the object is linked in a cycle, just if it is
		// linked
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (i != j) {
					if (sa[i].substring(2, 4).equals(sa[j].substring(0, 2))) {
						iSum++;
						sa[j] = "##" + sa[j].substring(2, 4);
						sa[i] = sa[i].substring(0, 2) + "$$";
						j = 5;
					}
				}
			}
		}
		
		return iSum == 4;
	}
}

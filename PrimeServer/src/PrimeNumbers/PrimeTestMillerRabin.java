package PrimeNumbers;

import java.math.BigInteger;

import Extras.NumHelp;

public class PrimeTestMillerRabin {
	private static final BigInteger	biMRLimit1	= new BigInteger("1373653");
	private static final BigInteger	biMRLimit2	= new BigInteger("9080191");
	private static final BigInteger	biMRLimit3	= new BigInteger("4759123141");
	private static final BigInteger	biMRLimit4	= new BigInteger("2152302898747");
	private static final BigInteger	biMRLimit5	= new BigInteger("3474749660383");
	private static final BigInteger	biMRLimit6	= new BigInteger("341550071728321");
	
	// public static boolean MRTest(long )
	private static int modular_exponent_32(final int base, final int power, final int modulus) {
		long result = 1;
		for (int i = 31; i >= 0; i--) {
			result = result * result % modulus;
			if ((power & 1 << i) != 0) {
				result = result * base % modulus;
			}
		}
		return (int) result; // Will not truncate since modulus is an int
	}
	
	private static boolean miller_rabin_pass_32(final int a, final int n) {
		int d = n - 1;
		final int s = Integer.numberOfTrailingZeros(d);
		d >>= s;
		int a_to_power = modular_exponent_32(a, d, n);
		if (a_to_power == 1) {
			return true;
		}
		for (int i = 0; i < s - 1; i++) {
			if (a_to_power == n - 1) {
				return true;
			}
			a_to_power = modular_exponent_32(a_to_power, 2, n);
		}
		if (a_to_power == n - 1) {
			return true;
		}
		return false;
	}
	
	public static boolean MRIsLikelyPrime(final int n) {
		if (miller_rabin_pass_32(2, n) && (n <= 7 || miller_rabin_pass_32(7, n)) && (n <= 61 || miller_rabin_pass_32(61, n))) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean GoodTest(final int n) {
		if (n > 61) {
			if (n % 2 == 0) {
				return false;
			}
			if (n % 3 == 0) {
				return false;
			}
			if (n % 5 == 0) {
				return false;
			}
			if (n % 7 == 0) {
				return false;
			}
			if (n % 11 == 0) {
				return false;
			}
			if (n % 13 == 0) {
				return false;
			}
			if (n % 17 == 0) {
				return false;
			}
			if (n % 19 == 0) {
				return false;
			}
			if (n % 23 == 0) {
				return false;
			}
			if (n % 29 == 0) {
				return false;
			}
			if (n % 31 == 0) {
				return false;
			}
			if (n % 37 == 0) {
				return false;
			}
			if (n % 41 == 0) {
				return false;
			}
			if (n % 43 == 0) {
				return false;
			}
			if (n % 47 == 0) {
				return false;
			}
			if (n % 53 == 0) {
				return false;
			}
			if (n % 59 == 0) {
				return false;
			}
			if (n % 61 == 0) {
				return false;
			}
			if (miller_rabin_pass_32(2, n)) {
				if (miller_rabin_pass_32(7, n)) {
					if (miller_rabin_pass_32(61, n)) {
						return true;
					}
					return false;
				}
				return false;
			}
			return false;
		}
		if (n == 2) {
			return true;
		}
		if (n == 3) {
			return true;
		}
		if (n == 5) {
			return true;
		}
		if (n == 7) {
			return true;
		}
		if (n == 11) {
			return true;
		}
		if (n == 13) {
			return true;
		}
		if (n == 17) {
			return true;
		}
		if (n == 19) {
			return true;
		}
		if (n == 23) {
			return true;
		}
		if (n == 29) {
			return true;
		}
		if (n == 31) {
			return true;
		}
		if (n == 37) {
			return true;
		}
		if (n == 41) {
			return true;
		}
		if (n == 43) {
			return true;
		}
		if (n == 47) {
			return true;
		}
		if (n == 53) {
			return true;
		}
		if (n == 59) {
			return true;
		}
		if (n == 61) {
			return true;
		}
		if (n <= 1) {
			return false;
		}
		
		return false;
	}
	
	private static boolean miller_rabin_pass_32(final BigInteger a, final BigInteger n) {
		final BigInteger d = n.subtract(BigInteger.ONE);
		final String sD = d.toString();
		int s = 0;
		for (s = 0; sD.charAt(sD.length() - s) != '0'; s++) {
			;
		}
		d.shiftRight(s);
		BigInteger a_to_power = a.modPow(d, n);
		if (a_to_power.equals(BigInteger.ONE)) {
			return true;
		}
		for (int i = 0; i < s - 1; i++) {
			if (a_to_power.equals(n.subtract(BigInteger.ONE))) {
				return true;
			}
			a_to_power = a.modPow(NumHelp.itob(2), n);
		}
		if (a_to_power.equals(n.subtract(BigInteger.ONE))) {
			return true;
		}
		return false;
	}
	
	public static boolean MRIsLikelyPrime(final BigInteger n) {
		if (n.compareTo(biMRLimit1) <= 0) {
			return miller_rabin_pass_32(NumHelp.itob(2), n) && miller_rabin_pass_32(NumHelp.itob(3), n);
		} else if (n.compareTo(biMRLimit2) <= 0) {
			return miller_rabin_pass_32(NumHelp.itob(31), n) && miller_rabin_pass_32(NumHelp.itob(73), n);
		} else if (n.compareTo(biMRLimit3) <= 0) {
			return miller_rabin_pass_32(NumHelp.itob(2), n) && miller_rabin_pass_32(NumHelp.itob(7), n) && miller_rabin_pass_32(NumHelp.itob(61), n);
		} else if (n.compareTo(biMRLimit4) <= 0) {
			return miller_rabin_pass_32(NumHelp.itob(2), n) && miller_rabin_pass_32(NumHelp.itob(3), n) && miller_rabin_pass_32(NumHelp.itob(5), n) && miller_rabin_pass_32(NumHelp.itob(7), n) && miller_rabin_pass_32(NumHelp.itob(11), n);
		} else if (n.compareTo(biMRLimit5) <= 0) {
			return miller_rabin_pass_32(NumHelp.itob(2), n) && miller_rabin_pass_32(NumHelp.itob(3), n) && miller_rabin_pass_32(NumHelp.itob(5), n) && miller_rabin_pass_32(NumHelp.itob(7), n) && miller_rabin_pass_32(NumHelp.itob(11), n) && miller_rabin_pass_32(NumHelp.itob(13), n);
		} else if (n.compareTo(biMRLimit6) <= 0) {
			return miller_rabin_pass_32(NumHelp.itob(2), n) && miller_rabin_pass_32(NumHelp.itob(3), n) && miller_rabin_pass_32(NumHelp.itob(5), n) && miller_rabin_pass_32(NumHelp.itob(7), n) && miller_rabin_pass_32(NumHelp.itob(11), n) && miller_rabin_pass_32(NumHelp.itob(13), n) && miller_rabin_pass_32(NumHelp.itob(17), n);
		} else {
			// This is non-deterministic, but I hope never to reach this limit.
			return miller_rabin_pass_32(NumHelp.itob(2), n) && miller_rabin_pass_32(NumHelp.itob(3), n) && miller_rabin_pass_32(NumHelp.itob(5), n) && miller_rabin_pass_32(NumHelp.itob(7), n) && miller_rabin_pass_32(NumHelp.itob(11), n) && miller_rabin_pass_32(NumHelp.itob(13), n) && miller_rabin_pass_32(NumHelp.itob(17), n);
		}
	}
}

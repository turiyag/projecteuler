package extras;

/**
 * Mitchell Ludwig #10015370
 */

// Basic class to represent a pair of strings
public class StrPair {
	private String	_s1;	// First string
	private String	_s2;	// Second string
							
	// Constructor
	// Initializes both strings to be empty strings
	public StrPair() {
		_s1 = "";
		_s2 = "";
	}
	
	// Constructor
	// Initializes strings to specified values
	public StrPair(final String s1, final String s2) {
		_s1 = s1;
		_s2 = s2;
	}
	
	// Constructor
	// Copies the values from the specified StrPair
	public StrPair(final StrPair spSrc) {
		_s1 = spSrc.Gets1();
		_s2 = spSrc.Gets2();
	}
	
	// Setters
	public void Sets1(final String s1) {
		_s1 = s1;
	}
	
	public void Sets2(final String s2) {
		_s2 = s2;
	}
	
	// Getters
	public String Gets1() {
		return _s1;
	}
	
	public String Gets2() {
		return _s2;
	}
	
	// Print the values in the pair
	public void print() {
		System.out.print(Gets1() + " " + Gets2());
	}
	
	// Print the values in the pair, followed by a newline
	public void println() {
		System.out.println(Gets1() + " " + Gets2());
	}
	
	// Compare the first element of the current StrPair with the
	// first element of the specified StrPair
	public int compare(final StrPair spComp) {
		return spComp.Gets1().compareTo(Gets1());
	}
	
	// Compare the second element of the current StrPair with the
	// second element of the specified StrPair
	public int compare2(final StrPair spComp) {
		return spComp.Gets2().compareTo(Gets2());
	}
}

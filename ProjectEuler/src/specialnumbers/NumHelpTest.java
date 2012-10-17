package specialnumbers;

import java.math.BigDecimal;

import org.junit.Test;

public class NumHelpTest {
	
	private static final int	SCALE	= 120;
	
	@Test
	public void test() {
		System.out.println("sqrt 2 = " + Math.sqrt(2));
		System.out.println("       = " + NumHelp.sqrt(BigDecimal.valueOf(2), SCALE));
		System.out.println("sqrt 26 = " + Math.sqrt(26));
		System.out.println("       = " + NumHelp.sqrt(BigDecimal.valueOf(26), SCALE));
	}
	
}

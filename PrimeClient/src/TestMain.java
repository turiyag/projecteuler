
public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrimeClient pc=new PrimeClient(1986321-8192);
		for(int i=1986321-9000;i<pc.GetCount();i++){
			System.out.println(pc.GetPrime(i));
		}
	}

}

import java.io.*;
import java.net.*;
public class PrimeClient {
	static final int iDataSize=8192;
	int[] iaPrimes;
	/**
	 * @param args
	 */
	public PrimeClient(int iCount){
		iaPrimes=new int[iCount];
		XfrPrimes();
	}
	
	public int GetPrime(int i){
		return iaPrimes[i];
	}
	
	public int GetCount(){
		return iaPrimes.length;
	}
	
	public void XfrPrimes(){
		int iCount=0;

		try{
			// get a datagram socket
			DatagramSocket socket = new DatagramSocket();
	
			// send request
			byte[] baOut = new byte[5];
			InetAddress address = InetAddress.getByName("localhost");
			baOut[0]=2;
			byte[] baIn;
			DatagramPacket packet;
			// get response
			int[] iaNums;
			
			packet = new DatagramPacket(baOut, baOut.length, address, 65521);
			socket.send(packet);
			if(iDataSize>iaPrimes.length){
				baIn = new byte[iDataSize*4];
				packet = new DatagramPacket(baIn, baIn.length);
				socket.receive(packet);
				iaNums=ToInts(packet.getData());

				for(int i=0;i<iaPrimes.length;i++){
					iaPrimes[i]=iaNums[i];
				}
			} else {
				baOut[0]=3;
				while(iCount<iaPrimes.length-iDataSize){
					baIn = new byte[iDataSize*4];
					packet = new DatagramPacket(baIn, baIn.length);
					socket.receive(packet);
					iaNums=ToInts(packet.getData());
					
					for(int i=0;i<iDataSize;i++){
						iaPrimes[i+iCount]=iaNums[i];
					}
					
					packet = new DatagramPacket(baOut, baOut.length, address, 65521);
					socket.send(packet);
					
					iCount+=iDataSize;
				}
				
				baIn = new byte[iDataSize*4];
				packet = new DatagramPacket(baIn, baIn.length);
				socket.receive(packet);
				iaNums=ToInts(packet.getData());

				for(int i=0;i+iCount<iaPrimes.length;i++){
					iaPrimes[i+iCount]=iaNums[i];
				}
			}

	
			// display response

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static int[] ToInts(byte[] baData){
		int[] iaNums=new int[baData.length/4];
		for (int i=0;i<iaNums.length;i++){
			iaNums[i]=byteArrayToInt(baData,i*4);//((int)(baData[(i*4)+3])*0x1000000)+((int)(baData[(i*4)+2])*0x10000)+((int)(baData[(i*4)+1])*0x100)+((int)(baData[(i*4)+0]));
		}
		return iaNums;
	}
	public static int byteArrayToInt(byte[] b, int offset) {
		int value=(b[3+offset]&0xFF)<<24;
		value += (b[2+offset]&0xFF)<<16;
		value += (b[1+offset]&0xFF)<<8;
		value += (b[0+offset]&0xFF);
		return value;
	}

	public static byte[] IntToByteArray(int value) {
		byte[] baRet=new byte[4];
		baRet[0]=(byte)(value&0xFF);
		baRet[1]=(byte)((value>>8)&0xFF);
		baRet[2]=(byte)((value>>16)&0xFF);
		baRet[3]=(byte)((value>>24)&0xFF);
		
		return baRet;
	}
}

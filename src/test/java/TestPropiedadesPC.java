import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestPropiedadesPC {
	
	public static void datos_pc(){ 		
		try {
			//sacar ip 
			String hostName= java.net.InetAddress.getLocalHost().getHostAddress(); 
			System.out.println("IP: " + hostName); 
			//nombre 
			InetAddress addr;
			addr = InetAddress.getByName(hostName);
			String hostname = addr.getHostName(); 
			System.out.println("hostname: " + hostname);
			//Sistema OPerativo 
			String so = System.getProperty("os.name"); 
			System.out.println("SISTEMA OPERATIVO: " + so);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
		} 


	public static void main(String[] args) {
		datos_pc();
	}

}

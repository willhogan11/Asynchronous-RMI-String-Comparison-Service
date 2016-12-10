package ie.gmit.sw;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class StringServiceServant {

	public static void main(String[] args) throws Exception {
		
		StringService strService = new StringServiceImpl();
		
		LocateRegistry.createRegistry(1099);
		
		Naming.rebind("StringCompareService", strService);
		
		System.out.println("Server Ready....");
	}
}
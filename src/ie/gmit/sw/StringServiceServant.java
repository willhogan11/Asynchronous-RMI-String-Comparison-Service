package ie.gmit.sw;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * @author Will Hogan
 * @category Distributed Systems
 * Create an instance of the class StringService.
 * Start the RMI registry on port 1099
 * Bind our remote object to the registry with the human-readable name "StringCompareService"
 * Display the status of the server on console
 */
public class StringServiceServant {

	public static void main(String[] args) throws Exception {
		
		//Create an instance of the class StringService.
		StringService strService = new StringServiceImpl();
		
		//Start the RMI registry on port 1099
		LocateRegistry.createRegistry(1099);
		
		//Bind our remote object to the registry with the human-readable name "StringCompareService"
		Naming.rebind("StringCompareService", strService);
		
		// Display the status of the server on console
		System.out.println("Server Ready....");
	}
}
package ie.gmit.sw;

import java.rmi.Remote;
import java.rmi.RemoteException;

/** 
 * @author Will Hogan
 * An interface called StringService should expose a remote method with the following signature:
 * where s and t are the two strings to compare, algo is the string matching algorithm to
 * use and Resultator is a remote object reference that allows the RMI service provider
 * to push an asynchronous response to the RMI requestor (a pass by reference from the 
 * service provider to the service requestor).
 */
public interface StringService extends Remote {

	// This method is remotely invocable
	public Resultator compare(String s, String t, String algo) throws RemoteException, InterruptedException;
}
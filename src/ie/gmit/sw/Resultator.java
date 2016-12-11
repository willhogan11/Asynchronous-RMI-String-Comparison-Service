package ie.gmit.sw;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Will Hogan
 * This interface called Resultator exposes the following remote methods, 
 * which are used to get and set the String Comparison result  
 */
public interface Resultator extends Remote {

	public String getResult() throws RemoteException;
	public void setResult(String result) throws RemoteException;
	public boolean isProcessed() throws RemoteException;
	public void setProcessed() throws RemoteException;
}
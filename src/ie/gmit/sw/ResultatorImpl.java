package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Will Hogan
 * ResultatorImpl is an example of a Remote Object. As this class implements the remote Interface StringService
 * ResultatorImpl also is a subclass of UnicastRemoteObject. This superclass provides the remote
 * object with all the functionality it needs to communicate with the server-side skeleton
 */
public class ResultatorImpl extends UnicastRemoteObject implements Resultator {
	
	private String result;
	private boolean isProcessed = false;

	private static final long serialVersionUID = 1L;

	public ResultatorImpl() throws RemoteException {
		super();
	}

	public String getResult() throws RemoteException {
		return this.result;
	}

	public void setResult(String result) throws RemoteException {
		this.result = result;
	}

	public boolean isProcessed() throws RemoteException {
		return isProcessed;
	}

	public void setProcessed() throws RemoteException {
		this.isProcessed = true;
	}
}
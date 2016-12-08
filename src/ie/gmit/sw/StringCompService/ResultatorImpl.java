package ie.gmit.sw.StringCompService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ResultatorImpl extends UnicastRemoteObject implements Resultator {
	
	private String result;
	private boolean isProcessed;

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
		this.isProcessed = false;
	}
}
package ie.gmit.sw.StringCompService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class StringServiceImpl extends UnicastRemoteObject implements StringService {

	protected StringServiceImpl() throws RemoteException {
		super();
	}

	public Resultator compare(String s, String t, String algo) throws RemoteException {
		
		Resultator res = new ResultatorImpl();
		
		StringComp sc = new StringComp(algo, s, t, res);
		
		return res;
	}	
}
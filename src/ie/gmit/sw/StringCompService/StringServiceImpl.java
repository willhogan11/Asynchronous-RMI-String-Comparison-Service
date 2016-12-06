package ie.gmit.sw.StringCompService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ThreadPoolExecutor;

public class StringServiceImpl extends UnicastRemoteObject implements StringService {

	private static final long serialVersionUID = 1L;
	
	public StringServiceImpl() throws RemoteException {
	}
	
	public Resultator compare(String s, String t, String algo) throws RemoteException {
		
		Resultator res = new ResultatorImpl();
	
		StringComp sc = new StringComp(algo, s, t, res);
		
		Thread thread = new Thread(sc);
		thread.start();
		
		System.out.println(thread);
		
		return res;
	}	
}
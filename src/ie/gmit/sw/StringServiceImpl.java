package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class StringServiceImpl extends UnicastRemoteObject implements StringService {

	private static final long serialVersionUID = 1L;
	private static final int NUM_OF_THREADS = 100;
    private ExecutorService executorService;
    
    private Resultator res;	
	
	public StringServiceImpl() throws RemoteException {
		// executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);
	}
	
	
	public Resultator compare(String s, String t, String algo) throws RemoteException, InterruptedException {
		
		//Create an Resultator object to hold the returned string coomparison result
		res = new ResultatorImpl();
		
		StringComp sc = new StringComp(algo, s, t, res);
				
		System.out.println("\nResult for Compare method : " + res.getResult());	
		
		return res;
	}	
}
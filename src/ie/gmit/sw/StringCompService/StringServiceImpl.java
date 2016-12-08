package ie.gmit.sw.StringCompService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class StringServiceImpl extends UnicastRemoteObject implements StringService {

	private static final long serialVersionUID = 1L;
	private static final int NUM_OF_THREADS = 100;
    private ExecutorService executorService;
    

//	public static void main(String[] args) throws RemoteException, InterruptedException {
//		
//		StringServiceImpl ssi = new StringServiceImpl();
//		
//		Resultator res;  //= new ResultatorImpl();
//		
//		res = ssi.compare("will", "will", "HAMMING_DISTANCE");
//		
//		System.out.println("Getting Result : " + res.getResult());
//	}
	
	
	public StringServiceImpl() throws RemoteException {
		// executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);
	}
	
	
	public Resultator compare(String s, String t, String algo) throws RemoteException, InterruptedException {
		
		Resultator res = new ResultatorImpl();
		
		
		StringComp sc = new StringComp(algo, s, t, res);
		System.out.println(sc);
		
		System.out.println("\nResult **BEFORE** the Compare method : " + res.getResult());	
		
		res = sc.compResult();
				
		System.out.println("\nResult **AFTER** the Compare method : " + res.getResult());	
		System.out.println("Resulatator Object should have the same Object Id: " + res);
		
		return res;
	}	
}
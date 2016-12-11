package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StringServiceImpl extends UnicastRemoteObject implements StringService {

	private static final long serialVersionUID = 1L;
	private static final int NUM_OF_THREADS = 100;
    private ExecutorService executorService;
    private Resultator res;
    
	public StringServiceImpl() throws RemoteException {
		executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);
	}
	
	public Resultator compare(String s, String t, String algo) throws RemoteException, InterruptedException {

		res = new ResultatorImpl();
		
		Thread.sleep(5000);
		
		executorService.submit(new Runnable() {
			
			public void run() {
				try {
					
					StringComp sc = new StringComp(algo, s, t, res);
					System.out.println("\nResult for Compare method : " + res.getResult());
					
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
		});
			
		return res;
	}	
}
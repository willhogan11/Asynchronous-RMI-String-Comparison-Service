package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Will Hogan
 * This class is responsible for implementing the StringService Interface and must implement 
 * the Resultator compare method. This allows for my own implementation, which essentially takes in the 3 parameters
 * that we need to compare, the two strings and the algorithm selected. For this, i create an instance of the StringComp class. 
 * 
 * For the below, i have created a Thread pool executor service, that allows the String comparison task to
 * continue on it's own thread, without clogging up the system. With this, the resultator object can still be returned as null until the 
 * String comparison is completed, it's then at this point that the updated Resultator object will be returned. 
 * 
 * As outlined in the Assignment briefing, i have put the main thread to sleep for 5 seconds, 
 * before the thread executor kicks in, to try and emulate a real time scenario. 
 * 
 * With the index.jsp page refreshing every 10 seconds, this allows for a delay and so the completed result shoudn't be seen for 20 seconds. 
 */
public class StringServiceImpl extends UnicastRemoteObject implements StringService {

	private static final long serialVersionUID = 1L;
	private static final int NUM_OF_THREADS = 100;
    private ExecutorService executorService;
    private Resultator res;
    
	public StringServiceImpl() throws RemoteException {
		executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);
	}
	
	public Resultator compare(String s, String t, String algo) throws RemoteException, InterruptedException {

		// Create a new Resultator object
		res = new ResultatorImpl();
		
		// Put main thread to sleep for 9 seconds. 
		Thread.sleep(9000);
		
		// Execute the thread pool service
		executorService.submit(new Runnable() {
			
			public void run() {
				try {
					
					// Create an instance of the StringComp class, that takes in 4 parameters. 
					StringComp sc = new StringComp(algo, s, t, res);
					
					// Just for testing, to demo what is being returned, ie null if nothing, or the updated Resultator object if completed. 
					System.out.println("\nResult for Compare method : " + res.getResult());
					
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
		});
			
		// Return the Resultator Object. 
		return res;
	}	
}
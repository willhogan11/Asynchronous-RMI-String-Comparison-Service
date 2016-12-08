package ie.gmit.sw.StringCompService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.sun.security.jgss.InquireSecContextPermission;

public class RequestJobProcess {

	private static final int NUMBER_OF_THREADS = 1;
	private static Queue<RequestJob> inQueue = new LinkedBlockingQueue<RequestJob>();
	private static Map<String, Resultator> outQueue = new ConcurrentHashMap<String, Resultator>();
	private ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    
    public static void main(String[] args) {
    	
    	RequestJobProcess rjp = new RequestJobProcess();
		RequestJob request;
	    Resultator res = null;

		request = new RequestJob();
		request.setAlgorithm("HAMMING");
		request.setStr1("will");
		request.setStr2("will");
		request.setTaskNumber("T0");
		
		rjp.addRequest(request);
		
		
		// Get a handle on the Registry....
		
		int counter = 0;
		
		// while(counter < 1) {
			
			try {
				
				System.out.println("Queue : " + inQueue);
				
				System.out.println("Request Details: " + request);
				
				// request = inQueue.poll();
				
				System.out.println("Details of empty resultator object : " + res);
				
				// StringComp sc = new StringComp(algo, s, t, res);
				StringComp sc = new StringComp(request.getAlgorithm(), request.getStr1(), request.getStr2(), res);
				System.out.println("StringComp Details : " + sc);
				
				StringService strServ = (StringService) Naming.lookup("rmi://localhost:1099/StringCompareService");
				System.out.println(strServ);
				
//				System.out.println("Result Details : " + res.getResult());
//				System.out.println(res.isProcessed());
				
				res = strServ.compare(request.getStr1(), request.getStr2(), request.getAlgorithm());
				
				System.out.println(res);
				System.out.println("Result on Clients side : " + res.getResult());
				
				outQueue.put(request.getTaskNumber(), res);
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// counter++; 
		// }
		
	}
    

	// Constructor
	public RequestJobProcess()  {
		

		
		
		// Start Threading 
//		executorService.submit(new Runnable() {
//			
//			public void run() {
//				
//				int counter = 0;
//				
//				while(counter < 3) {
//					
//					try {
//						
//						System.out.println("Queue : " + inQueue);
//						
//						System.out.println("Request Details: " + request);
//						
//						request = inQueue.poll();
//						
//						System.out.println("Details of empty resultator object : " + res);
//						
//						res = strServ.compare(request.getStr1(), request.getStr2(), request.getAlgorithm());
//						
//						if(res == null)
//							System.out.println("Result object is Null...");
//						
//						outQueue.put(request.getTaskNumber(), res);
//						
//						Thread.sleep(2000);
//						
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					
//					counter++; 
//				}
//			}
//		});
	}
	
	
    public void addRequest(RequestJob request){
        inQueue.add(request);
        System.out.println("Successfully Added Request to inQueue...." + inQueue);
    } 
	
}
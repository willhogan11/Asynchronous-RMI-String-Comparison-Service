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

public class RequestJobProcess {

	private static final int NUMBER_OF_THREADS = 1;
	private Queue<RequestJob> inQueue = new LinkedBlockingQueue<RequestJob>();
	private Map<String, Resultator> outQueue = new ConcurrentHashMap<String, Resultator>();
	private ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
	private StringService strServ;
	private static RequestJob request;
    private Resultator res;
    
    public static void main(String[] args) {
    	
		RequestJobProcess rjp = new RequestJobProcess();
		request = new RequestJob();
		request.setAlgorithm("HAMMING");
		request.setStr1("will");
		request.setStr2("will");
		request.setTaskNumber("T0");
		
		rjp.addRequest(request);
	}

	// Constructor
	public RequestJobProcess()  {
		
		// Get a handle on the Registry....
		try {
			
			StringService strServ = (StringService) Naming.lookup("rmi://localhost:1099/StringCompareService");
			System.out.println(strServ);
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		
		try {
			res = new ResultatorImpl();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		// Start Threading 
		executorService.submit(new Runnable() {
			
			public void run() {
				
				int counter = 0;
				
				while(true) {
					
					try {
						
						System.out.println("Queue : " + inQueue);
						
						System.out.println("Request Details: " + request);
						
						request = inQueue.poll();
						
						System.out.println("Details of empty resultator object : " + res);
						
						res = strServ.compare(request.getStr1(), request.getStr2(), request.getAlgorithm());
						
						if(res == null)
							System.out.println("Result object is Null...");
						
						outQueue.put(request.getTaskNumber(), res);
						
						Thread.sleep(2000);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					counter++; 
				}
			}
		});
	}
	
	
    public void addRequest(RequestJob request){
        inQueue.add(request);
        System.out.println("Successfully Added Request to inQueue....");
    } 
	
}
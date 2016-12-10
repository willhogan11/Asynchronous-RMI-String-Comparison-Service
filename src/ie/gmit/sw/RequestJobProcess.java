package ie.gmit.sw;

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
	private ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
	private Resultator res;
	
	
	// Constructor
	public RequestJobProcess() throws MalformedURLException, NotBoundException, RemoteException  {
	
		
		StringService strServ = (StringService) Naming.lookup("rmi://localhost:1099/StringCompareService");
		System.out.println(strServ); 
		
		try {
			
			System.out.println("Queue : " + inQueue);
			
			RequestJob request = inQueue.poll();
			
			Resultator res = strServ.compare(request.getStr1(), request.getStr2(), request.getAlgorithm());
			
			// outQueue.put(request.getTaskNumber(), res);
			
			// System.out.println("OutQueue details : " + outQueue);
			
			System.out.println("Details of empty resultator object : " + res);
			
			System.out.println("Request Details: " + request);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
    public void addRequest(RequestJob request){
        inQueue.add(request);
        System.out.println("Successfully Added Request to inQueue...." + inQueue);
    } 	
}

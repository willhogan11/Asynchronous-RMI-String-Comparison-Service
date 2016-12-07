package ie.gmit.sw.StringCompService;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientRunner {
	
	private static long jobNumber = 0;
	private static Queue<RequestJob> inQueue = new LinkedBlockingQueue<RequestJob>();
	private static Map<String, Resultator> outQueue = new HashMap<String, Resultator>();

	public static void main(String[] args) throws RemoteException {
	
		String algo = "LEVENSHTEIN_DISTANCE";
		String s = "will";
		String t = "will";
		String taskNumber = "";
		

		Resultator res = new ResultatorImpl();// Create a new instance of Resultator that will be added to the outQueue. 
		
		// Check out-queue for finished job
		// How to work this?
		
		try {
			// Get a handle on the Registry....
			StringService strServ = (StringService) Naming.lookup("rmi://localhost:1099/StringCompareService");
			System.out.println(strServ);
			
			taskNumber = new String("T" + jobNumber); // Create a TaskNumber, consisting of a T and the below incremented Job Number
			jobNumber++; //  Increment Job Number
			
			// Create a new RequestJob object from the request variables and offer to the in queue
			inQueue.offer(new RequestJob(algo, s, t, taskNumber));
			
			// Put the TaskNumber and the result object Id into the out queue
			outQueue.put(taskNumber, res);
			
			// Using a Resultator object, make the pass by reference RMI call, to use the string comparison service. 
			res = strServ.compare(s, t, algo);
			
			System.out.println("Result is : " + res.getResult());
			System.out.println("Is Job Processed : " + res.isProcessed());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
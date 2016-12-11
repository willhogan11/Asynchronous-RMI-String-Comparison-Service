package ie.gmit.sw;

import java.io.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.Queue;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * @author Will Hogan
 * This class is responsible for displaying the results of the String Comparison on a separate page and communicating with the RMI server.
 * The page will be created using the html elements that are nested / hardcoded in the java code.
 * As the name suggest, it's handling a Service and this service is responsible for communicating with the various tiers of the application. 
 * ie, Requester and Provider. 
 * 
 * I am using a Thread executor service, to manage threading that deals with checking the returned resultator object for a result.
 * Similar to the StringServiceimpl class, the thread service will continue to check for a result until one becomes available. 
 * Then the finished result will be stored in a local variable and displayed the next time the page refreshes. 
 * [See below, the page is triggered to refresh every 10 seconds]. The current request object, is then polled(taken) from the queue. 
 * 
 * I used a LinkedBlocking Queue to store the RequestJob instance, which itself contains the request variables associated with the request.
 * A linkedBlockingQueue needs to be used to allow for concurrency, when dealing with threads. It also won't throw a queue exception, 
 * if trying to poll from an empty queue. 
 * 
 * I used a ConcurrentHashMap to hold the the Key[taskNumber] and Value[resultator obj] associated with each request that's enetered into the outQueue. 
 * As well as being able to deal with concurrency, this keeps the resultator object in scope. 
 */
public class ServiceHandler extends HttpServlet {

	// Declaration of the instance variables used within this class. 
	private static final long serialVersionUID = 1L;
	private String remoteHost = null;
	private static long jobNumber = 0;
	private static Queue<RequestJob> inQueue = new LinkedBlockingQueue<RequestJob>();
	private static Map<String, Resultator> outQueue = new ConcurrentHashMap<String, Resultator>();
	private static final int NUMBER_OF_THREADS = 1;
	private ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
	Resultator res;
	

	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
		remoteHost = ctx.getInitParameter("RMI_SERVER"); //Reads the value from the <context-param> in web.xml
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		// Initialise some request variables with the submitted form info. These are local to this method and thread safe... 
		String algorithm = req.getParameter("cmbAlgorithm");
		String s = req.getParameter("txtS");
		String t = req.getParameter("txtT");
		String taskNumber = req.getParameter("frmTaskNumber");
		
		String finalResult = null;
		
		out.print("<html><head><title>Distributed Systems Assignment</title>");		
		out.print("</head>");		
		out.print("<body>");
		
		
		// Create a Singleton RequestJob instance that's being created in the RequestJobFactory, as we only want one request per comparison. 
		RequestJobFactory rjf = RequestJobFactory.getInstance();
		RequestJob requestJob = rjf.getRequestJob();
		
		if (taskNumber == null) {
			
			// Create and increment a Tasknumber
			taskNumber = new String("T" + jobNumber);
			jobNumber++;
			
			// Us getters and setters to populate the various parts of the Request object
			requestJob.setAlgorithm(algorithm);
			requestJob.setStr1(s);
			requestJob.setStr2(t);
			requestJob.setTaskNumber(taskNumber);
			
			// Add to a Queue
			inQueue.add(requestJob);
			
			// Send the created request object as a parameter to the below method [See Declaration]
			initialise(requestJob);
			
		} else {
			
			// If result is processed, Store it in a local variable called finalResult
			if(res.isProcessed() == true) {
				finalResult = res.getResult();
			}
		}
		
		out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
		out.print("<div id=\"r\"></div>");
		
		out.print("<font color=\"#993333\"><b>");
		out.print("RMI Server is located at " + remoteHost);
		out.print("<br>Algorithm: " + algorithm);		
		out.print("<br>String <i>s</i> : " + s);
		out.print("<br>String <i>t</i> : " + t);
		
		
		// Display the OutQueue Key Value pair information for the Result.
		// This queue will be updated when the result returns. 
		out.print("<font color='black'><h1>OUT QUEUE DETAILS:</h1></font>");
		out.println("<h3>Display the more meaningful Result instead of full Resultator Object</h3>");
		
		for ( Entry<String, Resultator> entry : outQueue.entrySet()) {
		    String key = entry.getKey();
		    Resultator value = entry.getValue();
		    out.println("Key : " + key + " >=======> Value : " + value.getResult());
		}
		
		// Clear the queue, in case another request is made. 
		outQueue.clear();
		
		// Result output details.
		out.print("<br><font color='black'><h1>RESULT STATUS:</h1></font>");
		
		// If the Result is still pending, display pending.
		// If processed, display the result on the page. 
		if(finalResult == null) { 
			out.print("Result for Job# " + taskNumber + " Pending....");
		} else {
			out.print(finalResult);
			out.print("<br>Job# " + taskNumber +  " is complete.");
		}
		out.print("</b></font>");
		out.print("<br><br>");
		
		out.print("<form name=\"frmRequestDetails\">");
		out.print("<input name=\"cmbAlgorithm\" type=\"hidden\" value=\"" + algorithm + "\">");
		out.print("<input name=\"txtS\" type=\"hidden\" value=\"" + s + "\">");
		out.print("<input name=\"txtT\" type=\"hidden\" value=\"" + t + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("</form>");								
		out.print("</body>");	
		out.print("</html>");	
		
		out.print("<script>");
		
	
		// If the result has not yet been returned, keep refreshing the page every 10 seconds.
		if (finalResult == null) {
			out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 10000);");
		}
		
		out.print("</script>");
	}
	
	
	// Method that starts the thread executor service, performs the remote method invocation and adds the result to the outQueue
	public void initialise(RequestJob requestJob) {
		
		executorService.submit(new Runnable(){
			public void run() {
		
				try {
					
					// Loop until the result is processed
					do {
						
						//Ask the registry running on localhost and listening in port 1099 for the instance of
						//the StringSevice object that is bound to the RMI registry with the name StringCompareService.
						StringService strServ = (StringService) Naming.lookup("rmi://localhost:1099/StringCompareService");
						
						// Remove the requestJob from the inQueue
						inQueue.poll(); 
						
						// Add (Update the second time around) the resultator object with the details obtained from comparing the strings
						res = strServ.compare(requestJob.getStr1(), requestJob.getStr2(), requestJob.getAlgorithm());
						
						// Add (Update the second time around) the outQueue with task number and Resultator object
						outQueue.put(requestJob.getTaskNumber(), res); 
						
					} while(res.isProcessed() == false);
					
				} catch (NotBoundException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}
}
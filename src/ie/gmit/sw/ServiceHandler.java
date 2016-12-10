package ie.gmit.sw;

import java.io.*;
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
import javax.servlet.*;
import javax.servlet.http.*;


public class ServiceHandler extends HttpServlet {

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
		
		/** Initialise some request variables with the submitted form info. These are local to this method and thread safe... */
		String algorithm = req.getParameter("cmbAlgorithm");
		String s = req.getParameter("txtS");
		String t = req.getParameter("txtT");
		String taskNumber = req.getParameter("frmTaskNumber");
		
		String finalResult = null;
		
		out.print("<html><head><title>Distributed Systems Assignment</title>");		
		out.print("</head>");		
		out.print("<body>");
		
		
		// Create a Singleton from the RequestJobFactory, as we only want one request per turn. 
		RequestJobFactory rjf = RequestJobFactory.getInstance();
		RequestJob requestJob = rjf.getRequestJob();
		
		if (taskNumber == null) {
			
			taskNumber = new String("T" + jobNumber);
			jobNumber++;
			
			requestJob.setAlgorithm(algorithm);
			requestJob.setStr1(s);
			requestJob.setStr2(t);
			requestJob.setTaskNumber(taskNumber);
			
			inQueue.add(requestJob);
			
			initialise(requestJob);
			
		} else {
			
			// Check out-queue for finished job
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
		
		out.print("<br><h1>RESULT STATUS:</h1>");
		
		if(finalResult == null) { 
			out.print("Result for Job# " + taskNumber + " Pending....");
		} else {
			out.print(finalResult);
			out.print("<br>Job# " + taskNumber +  " is complete.");
		}
		out.print("</b></font>");
		
		out.print("<form name=\"frmRequestDetails\">");
		out.print("<input name=\"cmbAlgorithm\" type=\"hidden\" value=\"" + algorithm + "\">");
		out.print("<input name=\"txtS\" type=\"hidden\" value=\"" + s + "\">");
		out.print("<input name=\"txtT\" type=\"hidden\" value=\"" + t + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("</form>");								
		out.print("</body>");	
		out.print("</html>");	
		
		out.print("<script>");
		
	
		// If the result has not yet been returned, keep refreshing the page */
		if (finalResult == null) {
			out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 10000);");
		}
		
		out.print("</script>");
	}
	
	
	
	public void initialise(RequestJob requestJob) {
		
		executorService.submit(new Runnable(){
			public void run() {
							
				while(true) {
				
					try {
						
						StringService strServ = (StringService) Naming.lookup("rmi://localhost:1099/StringCompareService");
						System.out.println(strServ); 
						
						inQueue.poll(); // Remove the requestJob from the inQueue
						
						res = strServ.compare(requestJob.getStr1(), requestJob.getStr2(), requestJob.getAlgorithm());
						
						outQueue.put(requestJob.getTaskNumber(), res); 

						if(res.isProcessed() == true) {
							outQueue.put(requestJob.getTaskNumber(), res);
							break;
						}
						
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
			}
		});
	}
	

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}
}
package ie.gmit.sw;

import java.io.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;

import javax.servlet.*;
import javax.servlet.http.*;

public class ServiceHandler extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String remoteHost = null;
	private static long jobNumber = 0;
	

	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
		remoteHost = ctx.getInitParameter("RMI_SERVER"); //Reads the value from the <context-param> in web.xml
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		//Initialise some request varuables with the submitted form info. These are local to this method and thread safe...
		String algorithm = req.getParameter("cmbAlgorithm");
		String s = req.getParameter("txtS");
		String t = req.getParameter("txtT");
		String taskNumber = req.getParameter("frmTaskNumber");
		
		boolean isProcessed = false;


		out.print("<html><head><title>Distributed Systems Assignment</title>");		
		out.print("</head>");		
		out.print("<body>");
		
		
		/**
		 * Create a new Instance of the RequestJob class to hold the request variables
		 */
		RequestJob requestJob = new RequestJob();
		
//		RequestJobProcess rjb = null;
//		
//		try {
//		
//			rjb = new RequestJobProcess();
//		} catch (NotBoundException e1) {
//			e1.printStackTrace();
//		}
		
		
		
		if (taskNumber == null){
			taskNumber = new String("T" + jobNumber);
			jobNumber++;
			
			
			//Add job to in-queue
			requestJob.setAlgorithm(algorithm);
			requestJob.setStr1(s);
			requestJob.setStr2(t);
			requestJob.setTaskNumber(taskNumber);
			
			// rjb.addRequest(requestJob);
			
		}else{
			//Check out-queue for finished job
		}
		
		
		out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
		out.print("<div id=\"r\"></div>");
		
		out.print("<font color=\"#993333\"><b>");
		out.print("RMI Server is located at " + remoteHost);
		out.print("<br>Algorithm: " + algorithm);		
		out.print("<br>String <i>s</i> : " + s);
		out.print("<br>String <i>t</i> : " + t);
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
		// out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 10000);");
		out.print("</script>");
				
		
		//You can use this method to implement the functionality of an RMI client
			try {
				
				StringService strServ = (StringService) Naming.lookup("rmi://localhost:1099/StringCompareService");
				System.out.println(strServ); 
				
				Resultator res = strServ.compare(requestJob.getStr1(), requestJob.getStr2(), requestJob.getAlgorithm());
				out.print("<h1>THE RESULT DISTANCE FOR " + requestJob.getAlgorithm() + " = " + res.getResult() + "</h1>");
				
				
			} catch (NotBoundException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		//
		
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}
}
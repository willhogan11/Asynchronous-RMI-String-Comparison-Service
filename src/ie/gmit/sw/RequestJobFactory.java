package ie.gmit.sw;

/**
 * @author Will Hogan
 * A Factory class that creates a Single instance of the RequestJob class
 * 
 * I chose the requestJob class to use to implement the Factory Pattern as
 * it's only dealing is with the ServiceHandler class. Upon successful creation
 * of the RequestJob object, it's then put into an in queue. So at any one time, there
 * will only be one instance of the RequestJob.  
 */
public class RequestJobFactory {

	// Create an instance of this class 
	private static RequestJobFactory fact = new RequestJobFactory();
	
	// Create a private constructor that disallows anyone from creating their own instance of this class. 
	private RequestJobFactory(){
	}
	
	// Return the Singleton instance from this getInstance method
	public static RequestJobFactory getInstance(){
		return fact;
	}
	
	// Return a new RequestJob instance
	public RequestJob getRequestJob(){
		return new RequestJob();
	}
}
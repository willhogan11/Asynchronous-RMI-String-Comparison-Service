package ie.gmit.sw;

public class RequestJobFactory {

	private static RequestJobFactory fact = new RequestJobFactory();
	
	private RequestJobFactory(){
	}
	
	public static RequestJobFactory getInstance(){
		return fact;
	}
	
	public RequestJob getRequestJob(){
		return new RequestJob();
	}
}
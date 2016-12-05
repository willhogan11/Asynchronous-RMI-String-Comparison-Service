package ie.gmit.sw.StringCompService;

import java.rmi.RemoteException;

public class StringComp {
	
	private static String algo = "HAMMING_DISTANCE";
	private static String s = "will";
	private static String t = "will";
	private static Resultator res;
	private static final int THREAD_SLEEP = 5000;
	
	
	public static void main(String[] args) throws InterruptedException, RemoteException {
		
		int distance;
		StringAlgoFacade saf = new StringAlgoFacade();
		
		res = new ResultatorImpl();
		
		if(algo.equals("HAMMING_DISTANCE")) {
			
			System.out.println("Processing...");
			Thread.sleep(THREAD_SLEEP);
			distance = saf.hammingDistanceAlgo(s, t);
			
			res.setResult("Result for Hamming Distance Comparison is : " + distance);
			res.setProcessed();
			
			// Testing
			System.out.println(res.getResult());
			System.out.println(res.isProcessed());
		}
	}
	
	public StringComp(String algo, String s, String t, Resultator res) {
		super();
		this.algo = algo;
		this.s = s;
		this.t = t;
		this.res = res;
	}
}
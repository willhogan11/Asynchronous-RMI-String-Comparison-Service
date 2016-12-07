package ie.gmit.sw.StringCompService;

import java.rmi.RemoteException;

public class StringComp implements Runnable {
	
	private String algo;
	private String s;
	private String t;
	private Resultator res;
	private static final int THREAD_SLEEP = 5000;
	
	
	public StringComp(String algo, String s, String t, Resultator res) {
		super();
		this.algo = algo;
		this.s = s;
		this.t = t;
		this.res = res;
	}
	
	// For Testing Purposes 
	@Override
	public String toString() {
		return "StringComp [algo=" + algo + ", s=" + s + ", t=" + t + ", res=" + res + "]";
	}

	// Put the Actual Comparing of the strings into it's own thread
	@Override
	public void run() {
		
		int distance;
		StringAlgoFacade saf = new StringAlgoFacade();
		
		try {
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
			else if(algo.equals("DAMERAU_LEVENSHTEIN_DISTANCE")) {
				
				System.out.println("Processing...");
				Thread.sleep(THREAD_SLEEP);
				distance = saf.hammingDistanceAlgo(s, t);
				
				res.setResult("Result for Damerau Levenshtein Distance Comparison is : " + distance);
				res.setProcessed();
				
				// Testing
				System.out.println(res.getResult());
				System.out.println(res.isProcessed());
			}
			else if(algo.equals("LEVENSHTEIN_DISTANCE")) {
				
				System.out.println("Processing...");
				Thread.sleep(THREAD_SLEEP);
				distance = saf.hammingDistanceAlgo(s, t);
				
				res.setResult("Result for Levenshtein Distance Comparison is : " + distance);
				res.setProcessed();
				
				// Testing
				System.out.println(res.getResult());
				System.out.println(res.isProcessed());
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
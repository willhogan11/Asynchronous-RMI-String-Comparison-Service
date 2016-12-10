package ie.gmit.sw;

import java.rmi.RemoteException;

public class StringComp {
	
	private String algo;
	private String s;
	private String t;
	private Resultator res;
	
	
	public StringComp(String algo, String s, String t, Resultator res) throws InterruptedException {

		this.algo = algo;
		this.s = s;
		this.t = t;
		this.res = res;
		
		compResult();
	}
	
	
	public void compResult() {
		
		int distance;
		StringAlgoFacade saf = new StringAlgoFacade();
		
		try {
			
			if(algo.equals("HAMMING_DISTANCE")) {
				
				System.out.println("Processing...");
				distance = saf.hammingDistanceAlgo(s, t);
				
				res.setResult("Result for Hamming Distance Comparison is : " + distance);
				res.setProcessed();
				
				// Testing
				System.out.println("Result Is: " + res.getResult());
				System.out.println("Is Processed? " + res.isProcessed());
			}
			else if(algo.equals("DAMERAU_LEVENSHTEIN_DISTANCE")) {
				
				System.out.println("Processing...");
				distance = saf.hammingDistanceAlgo(s, t);
				
				res.setResult("Result for Damerau Levenshtein Distance Comparison is : " + distance);
				res.setProcessed();
				
				// Testing
				System.out.println("Result Is: " + res.getResult());
				System.out.println("Is Processed? " + res.isProcessed());
			}
			else if(algo.equals("LEVENSHTEIN_DISTANCE")) {
				
				System.out.println("Processing...");
				distance = saf.hammingDistanceAlgo(s, t);
				
				res.setResult("Result for Levenshtein Distance Comparison is : " + distance);
				res.setProcessed();
				
				// Testing
				System.out.println("Result Is: " + res.getResult());
				System.out.println("Is Processed? " + res.isProcessed());
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} 
	}
	
	
	// For Testing Purposes 
	@Override
	public String toString() {
		return "StringComp [algo=" + algo + ", s=" + s + ", t=" + t + ", res=" + res + "]";
	}
}
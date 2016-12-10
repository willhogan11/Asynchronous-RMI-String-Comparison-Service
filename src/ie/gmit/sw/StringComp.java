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
	
	
	public void compResult() throws InterruptedException {
		
		int distance; // Could change this to a boolean and display if equal as a second option
		
		StringAlgoFacade saf = new StringAlgoFacade();
		
		try {
			
			if(algo.equals("HAMMING_DISTANCE")) {
				
				distance = saf.hammingDistanceAlgo(s, t);
				res.setResult("Result for Hamming Distance Comparison is : " + distance);
				res.setProcessed();
			}
			else if(algo.equals("DAMERAU_LEVENSHTEIN_DISTANCE")) {
				
				distance = saf.hammingDistanceAlgo(s, t);
				res.setResult("Result for Damerau Levenshtein Distance Comparison is : " + distance);
				res.setProcessed();
			}
			else if(algo.equals("LEVENSHTEIN_DISTANCE")) {
				
				distance = saf.hammingDistanceAlgo(s, t);
				res.setResult("Result for Levenshtein Distance Comparison is : " + distance);
				res.setProcessed();
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
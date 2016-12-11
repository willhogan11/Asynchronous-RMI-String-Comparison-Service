package ie.gmit.sw;

import java.rmi.RemoteException;


/**
 * @author Will Hogan
 * This class is responsible for delegating calls to the StringAlgoFacade class, which in turn calls the actual
 * Algorithm class to perform the string comparison, depending on which Algorithm was selected on the Client side. 
 */
public class StringComp {
	
	private String algo;
	private String s;
	private String t;
	private Resultator res;

	
	// A Constructor that takes all Request variables as parameters. 
	// The comp result will run in the constructor, when an instance of this class is called. 
	public StringComp(String algo, String s, String t, Resultator res) throws InterruptedException {

		this.algo = algo;
		this.s = s;
		this.t = t;
		this.res = res;
		
		compResult();
	}	
	
	
	// Perform the 
	public void compResult() throws InterruptedException {
		
		// A local variable that holds the returned distance result from the Algorithm selected. 
		int distance;
		
		// Create an instance of the StringAlgoFacade structural design pattern class. [For more info, read the comments in this class]
		StringAlgoFacade saf = new StringAlgoFacade();
		
		try {
			
			// Using a Switch case is tidier to look at but also, no .equals method is required. 
			switch (algo) {
			
				// : Check which Algorithm was selected
				// : Do the comparison check
				// : Using the Resultator object, set the result as the distance
				// : Set the Result as processed and break out of the switch statement
				case ("HAMMING_DISTANCE"): 
					distance = saf.hammingDistanceAlgo(s, t);
					res.setResult("Result for Hamming Distance Comparison is : " + distance);
					res.setProcessed();
					break;
					
				case ("DAMERAU_LEVENSHTEIN_DISTANCE"):
					distance = saf.hammingDistanceAlgo(s, t);
					res.setResult("Result for Damerau Levenshtein Distance Comparison is : " + distance);
					res.setProcessed();
					break;
					
				case ("LEVENSHTEIN_DISTANCE"):
					distance = saf.hammingDistanceAlgo(s, t);
					res.setResult("Result for Levenshtein Distance Comparison is : " + distance);
					res.setProcessed();
					break;
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
package ie.gmit.sw.StringCompService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class StringCompareService extends UnicastRemoteObject implements StringService {

	protected StringCompareService() throws RemoteException {
		super();
	}
	
	// For testing the below only. 
/*	public static void main(String[] args) {
		
		Algorithms algo = Algorithms.HAMMING_DISTANCE;
		String s = "will";
		String t = "will";
		
		
		AlgoFacade af = new AlgoFacade();

		if(algo == Algorithms.HAMMING_DISTANCE) {
			System.out.println(af.hammingDistanceAlgo(s, t));
		}
	}*/
	

	public Resultator compare(String s, String t, String algo) throws RemoteException {
		
		Algorithms algoSelection = Algorithms.HAMMING_DISTANCE;
		
		Map<Resultator, AlgoFacade> map = new HashMap<Resultator, AlgoFacade>();
		
		Resultator res = new ResultatorImpl();
		
		AlgoFacade af = new AlgoFacade();
		
		if(algo.equals(Algorithms.DAMERAU_LEVENSHTEIN_DISTANCE))
			af.damerauLevenshteinAlgo(s, t);
			map.put(res, af);
		if(algo.equals(Algorithms.HAMMING_DISTANCE))
			af.hammingDistanceAlgo(s, t);
		if(algo.equals(Algorithms.LEVENSHTEIN_DISTANCE))
			af.levenshteinAlgo(s, t);
		
		return res;
	}	
}
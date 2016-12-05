package ie.gmit.sw.Facade;

import java.rmi.RemoteException;

import ie.gmit.sw.StringCompService.StringAlgoFacade;

public class Runner {

	public static void main(String[] args) throws RemoteException {
		
		StringAlgoFacade algoFacadeMaker = new StringAlgoFacade();
		
		System.out.println(algoFacadeMaker.levenshteinAlgo("willi", "will")); 
		System.out.println(algoFacadeMaker.damerauLevenshteinAlgo("will", "will"));
		System.out.println(algoFacadeMaker.hammingDistanceAlgo("will", "will"));
	}
}
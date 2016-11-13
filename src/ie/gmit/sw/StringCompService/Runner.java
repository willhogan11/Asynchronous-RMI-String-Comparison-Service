package ie.gmit.sw.StringCompService;

public class Runner {

	public static void main(String[] args) {
		
		AlgoFacade algoFacadeMaker = new AlgoFacade();
		
		System.out.println(algoFacadeMaker.levenshteinAlgo("will", "will")); 
		System.out.println(algoFacadeMaker.damerauLevenshteinAlgo("will", "will"));
		System.out.println(algoFacadeMaker.hammingDistanceAlgo("will", "will"));
	}
}
package ie.gmit.sw.Facade;

public class Runner {

	public static void main(String[] args) {
		
		AlgoFacade algoFacadeMaker = new AlgoFacade();
		
		System.out.println(algoFacadeMaker.levenshteinAlgo("willi", "will")); 
		System.out.println(algoFacadeMaker.damerauLevenshteinAlgo("will", "will"));
		System.out.println(algoFacadeMaker.hammingDistanceAlgo("will", "will"));
	}
}
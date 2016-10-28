package ie.gmit.sw;

public class AlgoFacade {
	
	private Levenshtein levenshtein;
	private DamerauLevenshtein damerauLevenshtein;
	private HammingDistance hammingDistance;

	public AlgoFacade() {
		levenshtein = new Levenshtein();
		damerauLevenshtein = new DamerauLevenshtein();
		hammingDistance = new HammingDistance();
	}
	
	public boolean levenshteinAlgo(String s, String t) {
		int result = levenshtein.distance(s, t);
		if(result != 0)
			return false;
		else
			return true;
	}
	
	public boolean damerauLevenshteinAlgo(String s, String t){
		int result = damerauLevenshtein.distance(s, t);
		if(result != 0)
			return false;
		else
			return true;
	}
	
	public boolean hammingDistanceAlgo(String s, String t){
		int result = hammingDistance.distance(s, t);
		if(result != 0)
			return false;
		else
			return true;
	}
}
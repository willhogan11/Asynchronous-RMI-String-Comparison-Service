package ie.gmit.sw.StringCompService;

import ie.gmit.sw.algorithms.DamerauLevenshtein;
import ie.gmit.sw.algorithms.HammingDistance;
import ie.gmit.sw.algorithms.Levenshtein;

public class StringAlgoFacade implements Algorithmable {
	
	private Levenshtein levenshtein;
	private DamerauLevenshtein damerauLevenshtein;
	private HammingDistance hammingDistance;
	
	public StringAlgoFacade() {
		levenshtein = new Levenshtein();
		damerauLevenshtein = new DamerauLevenshtein();
		hammingDistance = new HammingDistance();
	}
	
	public int levenshteinAlgo(String s, String t) {
		int result = levenshtein.distance(s, t);
		return result;
	}
	
	public int damerauLevenshteinAlgo(String s, String t){
		int result = damerauLevenshtein.distance(s, t);
		return result;
	}
	
	public int hammingDistanceAlgo(String s, String t){
		int result = hammingDistance.distance(s, t);
		return result;
	}
}
package ie.gmit.sw.StringCompService;

public interface Algorithmable {

	public int levenshteinAlgo(String s, String t);
	public int damerauLevenshteinAlgo(String s, String t);
	public int hammingDistanceAlgo(String s, String t);
}
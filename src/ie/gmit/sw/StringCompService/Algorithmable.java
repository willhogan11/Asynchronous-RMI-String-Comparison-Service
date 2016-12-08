package ie.gmit.sw.StringCompService;

public interface Algorithmable {

	public boolean levenshteinAlgo(String s, String t);
	public boolean damerauLevenshteinAlgo(String s, String t);
	public boolean hammingDistanceAlgo(String s, String t);
}
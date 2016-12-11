package ie.gmit.sw;

/**
 * @author Will Hogan
 * {@link} https://www.tutorialspoint.com/design_pattern/facade_pattern.htm
 * 
 * I based this class on a Structural Facade Design pattern structure, mentioned in the above link. 
 * Facade pattern hides the complexities of the system and provides an interface to the client using 
 * which the client can access the system. This type of design pattern comes under structural pattern as 
 * this pattern adds an interface to existing system to hide its complexities.
 * This pattern involves a single class which provides simplified methods 
 * required by client and delegates calls to methods of existing system classes.
 * 
 * I thought this was really useful as the implementation for this actually doesn't call an instance of the Class
 * as each Algorithm class is instantiated inside the constructor. 
 * 
 * I used this approach because it adds a further layer of Abstraction / Encapsulation between the Algorithm classes and how they are called upon. 
 */
public class StringAlgoFacade {
	
	// Declare private instance variables of each Algorithm class
	private Levenshtein levenshtein;
	private DamerauLevenshtein damerauLevenshtein;
	private HammingDistance hammingDistance;
	
	// Initialise them inside the constructor
	public StringAlgoFacade() {
		
		levenshtein = new Levenshtein();
		damerauLevenshtein = new DamerauLevenshtein();
		hammingDistance = new HammingDistance();
	}
	
	/* A method that takes both strings as parameters
	 * The result is calculated within the method using the instance of the Algorithm and the check is performed.
	 * This is the same for the other Algorithm classes. */
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
package ie.gmit.sw;

/**
 * @author Will Hogan
 * An Abstraction that has a simple int distance as a return value. 
 * I created this interface because, all 3 of the Algorithms used in this Application
 * return a distance as an innteger and also take in both strings as parameters.
 */
public interface StringComparable {

	public abstract int distance(String s, String t);
}
package ie.gmit.sw.Facade;

public class HammingDistance implements StringComparable {
	
	public int distance(String s, String t) {
		if (s.length() != t.length()) return -1; //Similar length strings only
		int counter = 0;
		
		for (int i = 0; i < s.length(); ++i){
			if (s.charAt(i) != t.charAt(i)) counter++;
		}
		return  counter;
	}
}
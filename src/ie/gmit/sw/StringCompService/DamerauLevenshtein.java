package ie.gmit.sw.StringCompService;

public class DamerauLevenshtein implements StringComparable {

	public int distance(String s, String t) {
        int[][] distance = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i <= s.length(); i++) distance[i][0] = i;
        for (int j = 0; j <= t.length(); j++) distance[0][j] = j;

        for (int i = 1; i <= s.length(); i++){
            for (int j = 1; j <= t.length(); j++){
                distance[i][j] = Math.min(distance[i - 1][j] + 1, Math.min(distance[i][j - 1] + 1, distance[i - 1][j - 1] + ((s.charAt(i - 1) == t.charAt(j - 1)) ? 0 : 1)));
            
                if ((i > 1) && (j > 1) && (s.charAt(i-1) == t.charAt(j-2)) && (s.charAt(i-2) == t.charAt(j-1))){
                    distance[i][j] = Math.min(distance[i][j], distance[i-2][j-2] + ((s.charAt(i - 1) == t.charAt(j - 1)) ? 0 : 1));
                }
            }
        }
        return distance[s.length()][t.length()];
    }
}
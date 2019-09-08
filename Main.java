import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    static boolean[] visited;
    
    static boolean isMatched(TreeMap<Character, String> map, char current, char equals) {
        if (!map.containsKey(current)) return false;
        visited[Math.abs('a' - current)] = true;
        for (char c : map.get(current).toCharArray()) {
            if (visited[Math.abs('a' - c)]) continue;
            if (c == equals) return true;
            else if (isMatched(map, c, equals)) return true;
        }
        return false;
    }

    public static void main(String[] args) {

        final Scanner s = new Scanner(System.in);

        int numLetters, numTranslates;
        String p1, p2;
        TreeMap<Character, String> map;

        while (s.hasNext()) {

            numLetters = s.nextInt();
            numTranslates = s.nextInt();

            map = new TreeMap<>();

            for (int i = 0; i < numLetters; i++) {
                char k = s.next().charAt(0);
                char v = s.next().charAt(0);
                if (map.containsKey(k)) {
                    map.put(k, map.get(k) + v);
                }
                else map.put(k, String.valueOf(v));
            }

            for (int i = 0; i < numTranslates; i++) {
                p1 = s.next(); p2 = s.next();
                if (p1.length() != p2.length()) System.out.println("no");
                else {
                    boolean good = true;
                    for (int j = 0; j < p2.length() && good; j++) {
                        if (p1.charAt(j) != p2.charAt(j)) {
                            visited = new boolean[26];
                            if (!isMatched(map, p1.charAt(j), p2.charAt(j))) good = false;
                        }
                    }
                    System.out.println( (good) ? "yes" : "no" );
                }
            }

        }

    }

}

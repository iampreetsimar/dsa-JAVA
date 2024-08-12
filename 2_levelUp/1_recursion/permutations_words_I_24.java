// Given a word(may have one character repeat more than once), generate and print all arrangements
// of these characters. 

// INPUT
// aabb

// OUTPUT
// aabb
// abab
// abba
// baab
// baba
// bbaa

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class permutations_words_I_24 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();

        HashMap<Character, Integer> map = new HashMap<>();  // freq map of unique chars of i/p
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(map.containsKey(ch)) 
                map.put(ch, map.get(ch) + 1);
            else 
                map.put(ch, 1);

        }

        solve(1, word.length(), map, "");
    }

    // similar to PERMUTATIONS II - BOX CHOOSES but no excluding choice as # of boxes == # of items -> all boxes will be filled
    // use unique chars of i/p str as options to ignore duplicate arrangements
    // unique chars becomes valid options only when the char being considered has freq > 0
    // map: freq map of unique chars of i/p str
    public static void solve(int currBox, int totalBoxes, HashMap<Character, Integer> map, String asf) {
        if(currBox > totalBoxes) {  // BASE CASE
            System.out.println(asf);
            return;
        }

        for(Character ch : map.keySet()) {  // unique chars as options | use a unique str as well since keysey will be unordered
            if(map.get(ch) > 0) {   // valid options only if char's freq > 0 -> char can be used as an item
                map.put(ch, map.get(ch) - 1);
                solve(currBox + 1, totalBoxes, map,  asf + ch);
                map.put(ch, map.get(ch) + 1);
            }
        }
    }
}

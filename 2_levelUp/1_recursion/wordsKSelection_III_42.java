// Given a word(may have one character more than once) and an integer k, generate and print all the ways
// you can select k characters of the word.

// NOTE: -> words k selection I and II selects k DISTINCT characters. 
//       -> here, characters can repeat!!!

// INPUT
// aabbac 3

// OUTPUT
// aaa
// aab
// aac
// abb
// abc
// bbc

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class wordsKSelection_III_42 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        String word = parts[0];
        int k = Integer.parseInt(parts[1]);

        HashMap<Character, Integer> map = new HashMap<>();  // to build freq map of i/p str
        StringBuilder sb = new StringBuilder();     // to build unique char str from i/p str
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
                sb.append(ch);
            } 
        }

        // use Words K Selection I(box chooses) approach for both
        solve_I(0, sb.toString(), map, 0, k, "");   
        solve_II(0, sb.toString(), map, 0, k, "");
    }

    // use unique str chars to give including choices based on char's freq and an excluding choice as well
    // move to next char at next level irrespective of the choice made
    public static void solve_I(int idx, String uniqueStr, HashMap<Character, Integer> freqMap, 
                             int ssf, int ts, String asf) {
        if(ssf > ts) return;    // more # of selections than needed
                                
        if(idx == uniqueStr.length()) {     // BASE CASE
            if(ssf == ts) System.out.println(asf);      // # selection == k
            return;
        }
        
        char ch = uniqueStr.charAt(idx);
        int freq = freqMap.get(ch);
        for(int i = freq; i >= 1; i--) {    // including choices based on freq of char
            String str = "";
            for(int k = 0; k < i; k++) {    // add char to asf freq times
                str += ch;
            }
            solve_I(idx + 1, uniqueStr, freqMap, ssf + i, ts, asf + str);   // move to next char
        }

        solve_I(idx + 1, uniqueStr, freqMap, ssf, ts, asf); // excluding choice - move to next char
    }

    // use unique str chars to give including choices based on char's freq and an excluding choice as well
    // including choice if char has freq > 0 but stay at same char to give it choices again
    // excluding choice and move to next char
    public static void solve_II(int idx, String uniqueStr, HashMap<Character, Integer> freqMap, 
                             int ssf, int ts, String asf) {
        if(ssf > ts) return;    // # of selection > k
        
        if(idx == uniqueStr.length()) {     // BASE CASE
            if(ssf == ts) System.out.println(asf);  // # of selections == k
            return;
        }
        
        char ch = uniqueStr.charAt(idx);
        if(freqMap.get(ch) > 0) {    // including choice only if char freq > 0
            freqMap.put(ch, freqMap.get(ch) - 1);
            solve_II(idx, uniqueStr, freqMap, ssf + 1, ts, asf + ch);   // stay at same char to give it choices again
            freqMap.put(ch, freqMap.get(ch) + 1);
        }

        solve_II(idx + 1, uniqueStr, freqMap, ssf, ts, asf);    // excluding choice but move to next char
    }
}

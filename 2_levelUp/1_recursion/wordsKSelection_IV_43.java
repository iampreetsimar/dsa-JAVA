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

public class wordsKSelection_IV_43 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        String word = parts[0];
        int k = Integer.parseInt(parts[1]);

        HashMap<Character, Integer> map = new HashMap<>();  // to build freq map of chars.
        StringBuilder sb = new StringBuilder();     // to build unique str
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
                sb.append(ch);
            } 
        }

        solve(sb.toString(), map, 0, k, "", 0);
    }

    // similar to Words K Selection II(item chooses)
    // instead of all chars. after lastFilledChar, we also include lastFilledChar this time 
    // so that same char can have a choice if its freq > 0
    public static void solve(String uniqueStr, HashMap<Character, Integer> freqMap, 
                             int cs, int ts, String asf, int lastFilledCharIdx) {
        if(cs == ts) {  // BASE CASE
            System.out.println(asf);
            return;
        }

        for(int i = lastFilledCharIdx; i < uniqueStr.length(); i++) {   // all chars. from lastFilledChar to last as options
            char ch = uniqueStr.charAt(i);
            if(freqMap.get(ch) > 0) {   // only chars with freq > 0 are valid options
                freqMap.put(ch, freqMap.get(ch) - 1);
                solve(uniqueStr, freqMap, cs + 1, ts, asf + ch, i);     // increment ssf by 1
                freqMap.put(ch, freqMap.get(ch) + 1);
            }
        }
    }
}

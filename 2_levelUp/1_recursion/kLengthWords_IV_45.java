// Given a word(may have once character more than once) and an integer k, generate and print all k length 
// words by using characters of the word.

// NOTE: -> k length words I and II selects and permutes k DISTINCT characters. 
//       -> here, characters can repeat!!!

// INPUT
// aabbc 2

// OUTPUT
// aa
// ab
// ac
// ba
// bb
// bc
// ca
// cb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class kLengthWords_IV_45 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        String word = parts[0];
        int k = Integer.parseInt(parts[1]);

        HashMap<Character, Integer> fMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for(char ch: word.toCharArray()) {
            if(fMap.containsKey(ch)) {
                fMap.put(ch, fMap.get(ch) + 1);
            } else {
                fMap.put(ch, 1);
                sb.append(ch);
            }
        }

        solve(0, k, sb.toString(), fMap, "");
    }

    // similar to k length words - II(item chooses) but since repeated chars. are allowed, use a freq map
    // level: ssf(0 -> k) | options: all chars. of unique str w/c have freq > 0
    public static void solve(int cs, int ts, String ustr, HashMap<Character, Integer> fMap, String asf) {
        if(cs == ts) {      // BASE CASE
            System.out.println(asf);
            return;
        }

        for(int i = 0; i < ustr.length(); i++) {    // all chars. of unique str as options
            char ch = ustr.charAt(i);
            if(fMap.get(ch) > 0) {      // valids options if freq(ch) > 0
                fMap.put(ch, fMap.get(ch) - 1);
                solve(cs + 1, ts, ustr, fMap, asf + ch);    // move to next level
                fMap.put(ch, fMap.get(ch) + 1);
            }
        }
    }
}

// Given a word(may have one character more than once) and an integer k, generate and print all the ways
// you can select k distinct character of the word.

// INPUT
// aabbbccdde 3

// OUTPUT
// abc
// abd
// abe
// acd
// ace
// ade
// bcd
// bce
// bde
// cde

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class wordsKSelection_I_26 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        String word = parts[0];
        int k = Integer.parseInt(parts[1]);

        HashSet<Character> uniqSet = new HashSet<>();   // to build unique chars. str from i/p str
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(!uniqSet.contains(ch)) {
                uniqSet.add(ch);
                sb.append(ch);
            }
        }

        solve(0, sb.toString(), 0, k, "");
    }

    // selecting k distinct chars from N chars(unique) -> selecting k items to place in N boxes -> selecting k boxes out of N
    // use COMBINATIONS - I(box chooses) approach
    // level: unique str chars | options: include/exclude char
    public static void solve(int chIdx, String uniqueStr, int ssf, int ts, String asf) {
        if(chIdx == uniqueStr.length()) {   // BASE CASE: all chars choices completed
            if(ssf == ts) {     // choose selections where k chars were selected
                System.out.println(asf);
            }
            return;
        }

        solve(chIdx + 1, uniqueStr, ssf + 1, ts, asf + uniqueStr.charAt(chIdx));    // include char
        solve(chIdx + 1, uniqueStr, ssf, ts, asf);  // exclude char
    }
}

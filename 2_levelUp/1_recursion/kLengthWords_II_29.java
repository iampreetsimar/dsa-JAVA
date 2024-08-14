// Given a word(may have once character more than once) and an integer k, generate and print all k length 
// words(of distinct characters) by using characters of the word.

// INPUT
// aabbcc 2

// OUTPUT
// ab
// ac
// ba
// ca
// bc
// cb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class kLengthWords_II_29 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        String word = parts[0];
        int k = Integer.parseInt(parts[1]);

        HashSet<Character> uniqSet = new HashSet<>();   // to build unique str
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(!uniqSet.contains(ch)) {
                uniqSet.add(ch);
                sb.append(ch);
            }
        }

        solve(0, k, sb.toString(), new HashSet<Character>(), "");
    }

    // use PERMUTATIONS I(item chooses) approach
    // level: ssf(0 -> k) | options: any unused char of unique str
    // use HashSet to track w/c char of unique str can be a valid option
    public static void solve(int cs, int ts, String uniqueStr, HashSet<Character> set, String asf) {
        if(cs == ts) {      // BASE CASE
            System.out.println(asf);
            return;
        }

        for(int i = 0; i < uniqueStr.length(); i++) {   // any char of unique str
            char ch = uniqueStr.charAt(i);
            if(!set.contains(ch)) {     // valid only unused one
                set.add(ch);
                solve(cs + 1, ts, uniqueStr, set, asf + ch);
                set.remove(ch);
            }
        }
    }
}

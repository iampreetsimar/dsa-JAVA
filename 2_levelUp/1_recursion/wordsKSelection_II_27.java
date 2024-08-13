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

public class wordsKSelection_II_27 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        String word = parts[0];
        int k = Integer.parseInt(parts[1]);

        HashSet<Character> uniqSet = new HashSet<>();   // to build unique chars str from i/p
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(!uniqSet.contains(ch)) {
                uniqSet.add(ch);
                sb.append(ch);
            }
        }

        solve(sb.toString(), 1, k, -1, "");
    }

    // use COMBINATIONS II(item chooses) approach
    // need unique chars from i/p str to select k distinct chars
    // level: chars selected so far | options: all chars after the last selected one from unique str
    public static void solve(String uniqueStr, int cs, int ts, int lastFilledCharIdx, String asf) {
        if(cs > ts) {   // BASE CASE
            System.out.println(asf);
            return;
        }
        
        for(int i = lastFilledCharIdx + 1; i < uniqueStr.length(); i++) {   // all chars after last selected once
            char ch = uniqueStr.charAt(i);
            solve(uniqueStr, cs + 1, ts, i, asf + ch);
        }
    }
}

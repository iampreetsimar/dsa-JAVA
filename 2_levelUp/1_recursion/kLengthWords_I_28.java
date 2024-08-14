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

public class kLengthWords_I_28 {
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

        solve(sb.toString(), 0, 0, k, new Character[k]);
    }

    // use PERMUTATIONS II(box chooses) approach
    // level: chars of unique str | options: including choice for all unused idx of spots[] of len: k / excluding choice
    // excluding choice as k < # of unique chars -> there were always be some chars left
    public static void solve(String uniqueStr, int chIdx, int ssf, int ts, Character[] spots) {
        if(chIdx == uniqueStr.length()) {   // BASE CASE
            if(ssf == spots.length) {    
                for(char ch: spots) System.out.print(ch);
                System.out.println();
            }
            return;
        }

        for(int i = 0; i < spots.length; i++) {     // including choice
            if(spots[i] == null) {      // valid including options
                spots[i] = uniqueStr.charAt(chIdx);     // fill curr char at level in ith spot option
                solve(uniqueStr, chIdx + 1, ssf + 1, ts, spots);    // move to next char at next level
                spots[i] = null;    // unfill while backtracking
            }
        }

        solve(uniqueStr, chIdx + 1, ssf, ts, spots);    // excluding choice and move to next char at next level
    }
}

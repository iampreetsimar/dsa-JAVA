// Given a word(may have once character more than once) and an integer k, generate and print all k length 
// words by using characters of the word.

// NOTE: -> k length words I and II selects and permutes k DISTINCT characters. 
//       -> here, characters can repeat!!!

// INPUT
// aabb 2

// OUTPUT
// aa
// ab
// ba
// bb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class kLengthWords_III_44 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        String word = parts[0];
        int k = Integer.parseInt(parts[1]);

        HashMap<Character, Integer> lastFilledIdxMap = new HashMap<>(); // to prevent repeated branches
        for(char ch: word.toCharArray()) {
            lastFilledIdxMap.put(ch, -1);
        }

        solve(word, 0, 0, k, lastFilledIdxMap, new Character[k]);
    }

    // level: chars of i/p str | options: inc. choice for empty spots after last filled spot by same char, exc. choice only if no last filled spot by same char
    public static void solve(String word, int idx, int ssf, int ts, 
                             HashMap<Character,Integer> lastFilledIdxMap, Character[] spots) {
        if(idx == word.length()) {  // BASE CASE
            if(ssf == ts) {     // generated word length == k
                for(char ch: spots) System.out.print(ch);
                System.out.println();
            }
            return;
        }

        char ch = word.charAt(idx);
        int lp = lastFilledIdxMap.get(ch);  // last filled spot idx of char at curr level

        for(int i = lp + 1; i < spots.length; i++) {    // incl. choice for spots after last filled spot by char
            if(spots[i] == null) {      // options spots must be empty to be valid options
                spots[i] = ch;
                lastFilledIdxMap.put(ch, i);
                solve(word, idx + 1, ssf + 1, ts, lastFilledIdxMap, spots);     // move to next char at next level
                lastFilledIdxMap.put(ch, lp);
                spots[i] = null;
            }
        }

        if(lp == -1)    // excl. choice only if no last filled spot by char at curr level - to prevent duplicate branches
            solve(word, idx + 1, ssf, ts, lastFilledIdxMap, spots);     // move to next char at next level
    }
}

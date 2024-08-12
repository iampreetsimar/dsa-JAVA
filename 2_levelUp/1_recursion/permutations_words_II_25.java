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

public class permutations_words_II_25 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();

        HashMap<Character, Integer> lastOccurMap = new HashMap<>(); // last occurence map for each unique char
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(!lastOccurMap.containsKey(ch)) {
                lastOccurMap.put(ch, -1);
            }
        }

        solve(0, word, lastOccurMap, new Character[word.length()]);
    }

    // PERMUTATIONS I + COMBINATIONS II approach
    // valid options are boxes w/c are empty + that are ofter the boxes where curr char was last placed
    // tracking last occurence prevents duplicate arrangements
    public static void solve(int currItem, String word, HashMap<Character, Integer> lastOccurMap, Character[] res) {
        if(currItem == word.length()) { // BASE CASE
            for(char ch: res) {
                System.out.print(ch);
            }
            System.out.println();
            return;
        }

        char ch = word.charAt(currItem);
        int lo = lastOccurMap.get(ch);
        
        for(int i = lo + 1; i < res.length; i++) {  // spots after the one w/c was last filled by curr char
            if(res[i] == null) {    // spot must be empty
                lastOccurMap.put(ch, i);    // curr spot becomes last occurence spot for next time same char is considered
                res[i] = ch;
                solve(currItem + 1, word, lastOccurMap,  res);
                res[i] = null;
                lastOccurMap.put(ch, lo);   // add OG last occurence pos while backtracking
            }
        }
    }
}

// Given N space separated strings representing a dictionary of words. Also given a string which
// represents a sentence. 
// Print all possible sentences from the string such that words of the sentences are present in dictionary. 

// INPUT
// i like pep coding pepper eating mango man go in pepcoding
// ilikepeppereatingmangoinpepcoding

// OUTPUT
// i like pepper eating man go in pep coding
// i like pepper eating man go in pepcoding
// i like pepper eating mango in pep coding
// i like pepper eating mango in pepcoding

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class wordBreak_1_16 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] words = br.readLine().split(" ");
        HashSet<String> dict = new HashSet<>();
        for(String word: words) {
            dict.add(word);
        }
        String str = br.readLine();

        solve(str, dict, "");
    }

    // somewhat similar to PATTERN MATCHING - no mapping done yet case
    // at each level, find all substrings of str starting from 0th char
    // check if substr is present in dict, if it is -> add it to possible sentence
    // move to next level using remaining string
    public static void solve(String str, HashSet<String> dict, String ans) {
        if(str.length() == 0) { // BASE CASE
            System.out.println(ans);
            return;
        }

        for(int i = 0; i < str.length(); i++) { // check all substrs starting from 0th char
            String prefix = str.substring(0, i + 1);
            if(dict.contains(prefix)) { // if dictionary contains substr, add substr to ans
                solve(str.substring(i + 1), dict, ans + prefix + " "); // move to next level
            }
        }
    }
}

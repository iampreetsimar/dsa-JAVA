// Given a string and pattern, check the string is of the same structure as the pattern without using 
// any regular expressions.

// Print all valid configurations.

// INPUT
// graphtreesgraph     // string
// pep     // pattern

// OUTPUT
// p -> graph, e -> trees, .

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class patternMatching_15 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String pattern = br.readLine();

        solve(str, pattern, new HashMap<Character, String>(), new HashSet<String>(), pattern);
    }

    // level-options approach: levels on 0th char of pattern
    // options on all substrings of string starting from 0th char -> valid only those which are not already mapped
    public static void solve(String str, String pattern, HashMap<Character, String> patMap, 
                             HashSet<String> strSet, String patOrig) {
        if(pattern.length() == 0) {     // BASE CASE
            if(str.length() == 0) {     // empty pattern and empty string -> config possible
                HashSet<Character> alreadyPrinted = new HashSet<>();    // to not print duplicates
                for(int i = 0; i < patOrig.length(); i++) {     // loop of OG pattern str
                    char ch = patOrig.charAt(i);
                    if(!alreadyPrinted.contains(ch)) {
                        alreadyPrinted.add(ch);
                        System.out.print(ch + " -> " + patMap.get(ch) + ", ");  // print mapping
                    }
                }
                System.out.println(".");
            }
            return;
        }

        Character ch = pattern.charAt(0);   // curr level
        String rop = pattern.substring(1);  // remaining pattern

        if(patMap.containsKey(ch)) {    // curr level char already mapped
            String mappedStr = patMap.get(ch);  
            if(str.length() >= mappedStr.length()) {    // mapped value can be made from curr string
                if(str.substring(0, mappedStr.length()).equals(mappedStr)) { // mapped value equals substr
                    solve(str.substring(mappedStr.length()), // if yes, move to next level using remaining string
                          rop, patMap, strSet, patOrig); 
                }
            }
        } else {    // no mapping done yet
            for(int i = 0; i < str.length(); i++) {     // find all substrings starting from 0th char
                String prefix = str.substring(0, i + 1);
                String ros = str.substring(i + 1);
                if(!strSet.contains(prefix)) {  // if substring not already mapped to some char
                    patMap.put(ch, prefix);     // add mapping for curr ch usinf substring to map
                    strSet.add(prefix);         // add substring to set
                    solve(ros, rop, patMap, strSet, patOrig);  // move to next level using remaining string
                    strSet.remove(prefix);  // remove mapping while backtracking
                    patMap.remove(ch);  // remove substring from set while backtracking
                }
            }
        }
    }
}

// Given a string of length n, print all palindromic permutation of the string.
// If no palindromic permutation exists for the string, print -1.

// INPUT
// aaabb

// OUTPUT
// ababa
// baaab

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class palindromicPermutations_12 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        HashMap<Character, Integer> freqMap = new HashMap<>();
        for(int i = 0; i < str.length(); i++) {     // freq map of all chars of string
            if(freqMap.containsKey(str.charAt(i))) 
                freqMap.put(str.charAt(i), freqMap.get(str.charAt(i)) + 1);
            else    
                freqMap.put(str.charAt(i), 1);
        }

        int len = 0;    // total length of halved freq string
        Character oddCh = null;     // initial value of odd char in OG string
        for(Character ch: freqMap.keySet()) {   // half freq of chars in map
            int freq = freqMap.get(ch);
            if(freq % 2 != 0) {   // if OG freq of a char is odd
                if(oddCh != null) {  // check if odd char is already set -> we have another odd char in OG string 
                    System.out.println(-1);     // no palindromic permutation possible
                    return;
                }

                oddCh = ch;     // otherwise set curr char as odd 
            }

            freqMap.put(ch, freq/2);    // half OG freq of char
            len += freq/2;  // total length of halved freq string
        }
        
        solve(0, len, freqMap, oddCh, "");  
    }

    // level-options approach: levels on idx of halved len string, valid options on chars in map whose freq > 0
    // make subseq using map char.
    // in base case, add odd char to subseq if it is present, then add reverse of subseq 
    // to make in palindromic permutation of OG string
    // since map keyset() returns unordered keys, we can use a ordered halved string and loop on it for options
    public static void solve(int idx, int len, HashMap<Character, Integer> freqMap, Character oddCh, String asf) {
        if(idx == len) {    // BASE CASE
            System.out.print(asf);  // first half
            if(oddCh != null) System.out.print(oddCh);  // odd char
            for(int i = asf.length() - 1; i >= 0; i--) {    // reverse of first half
                System.out.print(asf.charAt(i));
            }
            System.out.println();
            return;
        }

        for(Character ch: freqMap.keySet()) {   // options on each char in map
            if(freqMap.get(ch) > 0) {   // valid option: if freq > 0
                freqMap.put(ch, freqMap.get(ch) - 1);   // decrement freq of ch
                solve(idx + 1, len, freqMap, oddCh, asf + ch);  // move to next idx of halved len
                freqMap.put(ch, freqMap.get(ch) + 1);   // incrmeent freq of ch while backtracking
            }
        }
    }
}
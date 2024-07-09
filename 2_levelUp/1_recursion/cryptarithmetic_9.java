// Given 3 strings s1, s2 and s3, first two are supposed to add and form third, s1 + s2 = s3.
// Map each individual character to a digit so that above equation holds true.
// Print the config in increasing order of characters.

// INPUT
// send more money

// OUTPUT
// d-7 e-8 m-0 n-1 o-3 r-6 s-2 y-5 } one such config
// ......

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class cryptarithmetic_9 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = br.readLine().split(" ");

        // stores all unique chars(key) from 3 strings and value being the digit it represents
        HashMap<Character, Integer> map = new HashMap<>();

        // stores all unique chars from 3 strings
        StringBuilder unique = new StringBuilder(); 

        for(String s: strings) {
            for(int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if(!map.containsKey(ch)) {
                    map.put(ch, -1);    // initial digit of ch is -1
                    unique.append(ch);
                }
            }
        }
        
        solve(map, unique.toString(), strings, new boolean[10], 0);
    }

    // use level-options recursive approach : BRUTE FORCE 
    // TC: options ^ levels -> approx 10^unique.length()
    // levels : unique str chars | idx parameter and base case on unique str char
    // possible options: for loop on digits 0-9 but valid options: usedNumbers[digit] == false
    // usedNumbers: idx represents the digits & arr[idx] represents if digit has been used for config or not
    public static void solve(HashMap<Character, Integer> map, String unique, 
                             String[] strings, boolean[] usedNumbers, int chIdx) {
        if(chIdx == unique.length()) {  // BASE CASE
            long num1 = getNumber(map, strings[0]); // possibility of long numbers
            long num2 = getNumber(map, strings[1]);
            long num3 = getNumber(map, strings[2]);

            if(num1 + num2 == num3) {   // check if equation holds true
                for(int i = 0; i < 26; i++) {   // to print in increasing order: check all alphabets
                    char ch = (char)('a' + i);  
                    if(map.containsKey(ch)) {   // if alphabets in map - print it with it value
                        System.out.print(ch + "-" + map.get(ch) + " ");
                    }
                }
                System.out.println();
            }
            return;
        }

        for(int num = 0; num < 10; num++) { // loop on possible options
            if(usedNumbers[num] == false) { // valid options
                map.put(unique.charAt(chIdx), num);  // mark curr ch's digit in map
                usedNumbers[num] = true;  // mark curr valid option has been used
                solve(map, unique, strings, usedNumbers, chIdx + 1);  // move to next level
                usedNumbers[num] = false;   // unmark while backtracking
                map.put(unique.charAt(chIdx), -1);  // unmark curr ch's digit
            }
        }
    }

    // convert string to a number based on values in map and returns it
    public static long getNumber(HashMap<Character, Integer> map, String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length();i ++) {
            sb.append(map.get(s.charAt(i)));
        }
        return Long.parseLong(sb.toString());
    }
}

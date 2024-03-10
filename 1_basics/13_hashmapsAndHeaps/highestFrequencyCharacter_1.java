// 1. You are given a string str.
// 2. You are required to find the character with maximum frequency.

// Input Format
// A string str

// Output Format
// The character with highest frequency

// Constraints
// 0 < str.length() <= 100
// There will be a single character with highest frequency

// Sample Input
// zmszeqxllzvheqwrofgcuntypejcxovtaqbnqyqlmrwitc

// Sample Output
// q

import java.util.HashMap;
import java.util.Scanner;

public class highestFrequencyCharacter_1 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();

        highestFreqChar(str);   // prints highest frequency occuring character
    }

    public static void highestFreqChar(String str) {
        // hashmap stores frequency of corresponding char
        HashMap<Character, Integer> freqMap = new HashMap<>();

        // traverse str to store each char with its freq in hashmap - O(N)
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if(freqMap.containsKey(ch)) {
                freqMap.put(ch, freqMap.get(ch) + 1);
            } else {
                freqMap.put(ch, 1);
            }
        }

        char maxFreqCh = str.charAt(0); // setting default max char at 0th char of str

        // traverse over hashmap keys to find max freq and set max freq char - O(N)
        for(Character ch : freqMap.keySet()) {
            if(freqMap.get(ch) > freqMap.get(maxFreqCh)) {
                maxFreqCh = ch;
            }
        }

        System.out.println(maxFreqCh);  // prints max freq char

        // T.C: 2[O(N)] ~ O(N)
    }
}

// 1. You are given a string. 
// 2. You have to print all palindromic substrings of the given string.

// Input Format
// A String

// Output Format
// All palindromic substrings(one in a line).
// First, all palindromic substrings starting from first character of string will be printed, 
// then from second character and so on.

// Constraints
// 1 <= length of string <= 500

// Sample Input
// abcc

// Sample Output
// a
// b
// c
// cc
// c

import java.util.*;

public class printAllPalindromicSubstrings_1 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        String str = scn.nextLine();

        printAllPalindromicSubstrings(str);
    }

    public static boolean isPalindrome(String s) {
        int startIdx = 0;
        int endIdx = s.length() - 1;

        while(startIdx < endIdx) {
            if(s.charAt(startIdx) != s.charAt(endIdx))
                return false;
            
            startIdx++;
            endIdx--;
        }

        // string is palindromic
        return true;
    }

    public static void printAllPalindromicSubstrings(String str) {
        for(int i = 0; i < str.length(); i++) {
            for(int j = i + 1; j <= str.length(); j++) {

                // gets all substrings
                String substr = str.substring(i, j);
                if(isPalindrome(substr))    // returns true if substring is palindromic
                    System.out.println(substr);
            }
        }
    }
}

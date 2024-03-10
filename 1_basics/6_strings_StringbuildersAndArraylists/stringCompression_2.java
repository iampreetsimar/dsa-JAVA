// 1. You are given a string. 
// 2. You have to compress the given string in the following two ways - 
//    First compression -> The string should be compressed such that consecutive duplicates of characters are replaced 
//    with a single character. For "aaabbccdee", the compressed string will be "abcde".
//    Second compression -> The string should be compressed such that consecutive duplicates of characters are replaced 
//    with the character and followed by the number of consecutive duplicates.
//    For "aaabbccdee", the compressed string will be "a3b2c2de2".

// Input Format
// A String

// Output Format
// Two strings representing first compressed string and second compressed string respectively.

// Constraints
// 1 <= length of string <= 1000

// Sample Input
// wwwwaaadexxxxxx

// Sample Output
// wadex
// w4a3dex6

import java.util.*;

public class stringCompression_2 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        String str = scn.nextLine();

        System.out.println(firstCompression(str));
        System.out.println(secondCompression(str));
    }

    public static String firstCompression(String s) {
        String res = "" + s.charAt(0);
        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i) != s.charAt(i - 1)) {
                res += s.charAt(i);
            }
        }
        return res;
    }

    public static String secondCompression(String s) {
        String res = "" + s.charAt(0);
        int count = 1;

        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i) != s.charAt(i - 1)) {
                if(count > 1) {
                    res += count;
                    count = 1;
                }

                res += s.charAt(i);
                continue;
            }

            count++;    // if char(i) == char(i - 1)
        }

        // checks last count > 1
        if(count > 1)
            res += count;

        return res;
    }
}

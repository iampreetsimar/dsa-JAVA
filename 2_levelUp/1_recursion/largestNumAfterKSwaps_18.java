// Given a string representing digits of a number, create the maximum number by performing
// ATMOST K swap operations on its digits.

// INPUT
// 1234567
// 4

// 38164
// 2

// OUTPUT
// 7654321
// 86134

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class largestNumAfterKSwaps_18 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int k = Integer.parseInt(br.readLine());
        maxNum = str;
        solve(str, k);
        System.out.println(maxNum);
    }

    static String maxNum;

    // use BRUTE FORCE and level-options approach
    // levels: k swaps, 1 swap at each level
    // options: str.length()^2 options -> valid swap options where jth num > ith num in str
    // numbers made after swapping are checked at each level since any of those could be max
    // including the once from the base case
    public static void solve(String str, int k) {
        if(Integer.parseInt(str) > Integer.parseInt(maxNum)) 
            maxNum = str;

        if(k == 0)  return; // BASE CASE -> but numbers are checked before 

        // str.length()^2 options
        for(int i = 0; i < str.length(); i++) {
            for(int j = i + 1; j < str.length(); j++) {
                if(str.charAt(j) > str.charAt(i)) { // ASCII value of jth num  > ith num in str
                    String swappedStr = swap(str, i, j);    // swap jth and ith nums -> k-1 swaps left
                    solve(swappedStr, k - 1);   
                }
            }
        }
    }

    // returns a string after swapping ith and jth char in str
    public static String swap(String str, int i, int j) {
        // using String
        // String left = str.substring(0, i);
        // String mid = str.substring(i + 1, j);
        // String right = str.substring(j + 1);
        // char ith = str.charAt(i);
        // char jth = str.charAt(j);
        // return left + jth + mid + ith + right;

        // using StringBuilder
        StringBuilder sb = new StringBuilder(str);
        char ith = str.charAt(i);
        char jth = str.charAt(j);
        sb.setCharAt(i, jth);
        sb.setCharAt(j, ith);
        return sb.toString();
    }
}

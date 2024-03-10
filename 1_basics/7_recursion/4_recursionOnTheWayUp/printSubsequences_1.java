// 1. You are given a string str.
// 2. Complete the body of printSS function - without changing signature - to calculate and print all subsequences of str.
// Use sample input and output to take idea about subsequences.

// Note -> The online judge can't force you to write the function recursively but that is what the spirit of question is. 
// Write recursive and not iterative logic. The purpose of the question is to aid learning recursion and not test you.

// Input Format
// A string str

// Output Format
// Subsequences of str in order hinted by Sample output

// Constraints
// 0 <= str.length <= 7

// Sample Input
// yvTA

// Sample Output
// yvTA
// yvT
// yvA
// yv
// yTA
// yT
// yA
// y
// vTA
// vT
// vA
// v
// TA
// T
// A
// ""

import java.util.*;

public class printSubsequences_1 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();
        String ans = "";

        // answer so far empty initially
        printSS(str, "");
    }

    public static void printSS(String str, String asf) {
        if(str.length() == 0) {
            // BASE CASE
            // no need to store asf in an arraylist
            // only printing - no storage issue
            System.out.println(asf);
            return;
        }

        // choice present - append 0th char to asf
        printSS(str.substring(1), asf + str.charAt(0));

        // choice absent
        printSS(str.substring(1), asf);
    }
}

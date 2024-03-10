// 1. You are given a string str.
// 2. Complete the body of printPermutations function - without changing signature - to calculate and 
// print all permutations of str.
// Use sample input and output to take idea about permutations.

// Note -> The online judge can't force you to write the function recursively but that is what the spirit of question is. 
// Write recursive and not iterative logic. The purpose of the question is to aid learning recursion and not test you.

// Input Format
// A string str

// Output Format
// Permutations of str in order hinted by Sample output

// Constraints
// 0 <= str.length <= 7

// Sample Input
// abc

// Sample Output
// abc
// acb
// bac
// bca
// cab
// cba

import java.util.*;

public class printPermutations_6 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();

        printPermutations(str, "");
    }

    public static void printPermutations(String str, String asf) {
        if(str.length() == 0) {
            // BASE CASE
            // str is empty -> print asf
            System.out.println(asf);
            return;
        }

        // extract each char of str
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            // str.substring(0, i) - gives left substring before char ch
            // str.substring(i + 1) - gives right substring after char ch
            
            // substring SPECIAL CASE : 
            // str.substring(i, i) -> gives empty string
            // str.substring(str.length()) -> gives empty string
            String ros = str.substring(0, i) + str.substring(i + 1);

            // append char ch to asf
            printPermutations(ros, asf + ch);
        }
    }
}

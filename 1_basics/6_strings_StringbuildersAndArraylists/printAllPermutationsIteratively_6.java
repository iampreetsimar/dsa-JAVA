// 1. You are given a string. 
// 2. You have to print all permutations of the given string iteratively.

// Input Format
// A String

// Output Format
// All permutations of the given string(one in a line). 

// Constraints
// 1 <= length of string <= 15

// Sample Input
// abc

// Sample Output
// abc
// bac
// cab
// acb
// bca
// cba

import java.util.*;

public class printAllPermutationsIteratively_6 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();

        printAllPermutations(str);
    }

    public static void printAllPermutations(String s) {
        int n = s.length();
        int fact = getFactorial(n);

        for(int i = 0; i < fact; i++) {
            StringBuilder sb = new StringBuilder(s);
            int temp = i;
            for(int div = n; div >= 1; div--) {
                int q = temp / div;
                int r = temp % div;

                System.out.print(sb.charAt(r));
                sb.deleteCharAt(r);
                temp = q;
            }

            System.out.println();
        }
    }

    public static int getFactorial(int n) {
        int val = 1;
        for(int i = 2; i <= n; i++) {
            val *= i;
        }

        return val;
    }
}

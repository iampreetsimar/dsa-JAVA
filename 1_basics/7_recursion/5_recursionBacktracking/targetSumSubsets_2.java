// 1. You are given a number n, representing the count of elements.
// 2. You are given n numbers.
// 3. You are given a number "tar".
// 4. Complete the body of printTargetSumSubsets function - without changing signature - to calculate and 
// print all subsets of given elements, the contents of which sum to "tar". Use sample input and output to get more idea.

// Note -> The online judge can't force you to write the function recursively but that is what the spirit of question is. 
// Write recursive and not iterative logic. The purpose of the question is to aid learning recursion and not test you.

// Input Format
// A number n
// n1
// n2
// .. n number of elements
// A number tar

// Output Format
// Comma separated elements of the subset, the contents of which add to "tar"
// .. all such subsets, each in a single line (the elements of each subset should be comma separated)

// Constraints
// 1 <= n <= 30
// 0 <= n1, n2, .. n elements <= 20
// 0 <= tar <= 50

// Sample Input
// 5
// 10 20 30 40 50
// 60

// Sample Output
// 10, 20, 30, .
// 10, 50, .
// 20, 40, .

import java.util.*;

public class targetSumSubsets_2 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }

        int tar = scn.nextInt();

        printTargetSumSubsets(arr, 0, "", 0, tar);
    }

    // set is the subset
    // sos is sum of subset
    // tar is target
    public static void printTargetSumSubsets(int[] arr, int idx, String set, int sos, int tar) {
        if(idx == arr.length) {
            if(sos == tar) {
                // BASE CASE
                System.out.println(set + ".");
            }
            return;
        }

        // element included in set
        printTargetSumSubsets(arr, idx + 1, set + arr[idx] + ", ", sos + arr[idx], tar);

        // element excluded from set
        printTargetSumSubsets(arr, idx + 1, set, sos, tar);
    }
}

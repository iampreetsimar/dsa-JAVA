// 1. You are given a number n, representing the count of elements.
// 2. You are given n numbers.
// 3. You are given a number x. 
// 4. You are required to find the all indices at which x occurs in array a.
// 5. Return an array of appropriate size which contains all indices at which x occurs in array a.

// Note -> The online judge can't force you to write the function recursively but that is what the spirit of question is. 
// Write recursive and not iterative logic. The purpose of the question is to aid learning recursion and not test you.

// Input Format
// A number n
// n1
// n2
// .. n number of elements
// A number x

// Output Format
// Return the array of indices from the allIndices function. Display is managed for you.

// Constraints
// 1 <= n <= 10^4
// 0 <= n1, n2, .. n elements <= 10 ^ 3
// 0 <= x <= 10 ^ 3

// Sample Input
// 6
// 15 11 40 4 4 9
// 4

// Sample Output
// 3
// 4

import java.util.*;

public class allIndices_6 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }

        int x = scn.nextInt();

        int[] allIndices = allIndices(arr, x, 0, 0);

        // display all indices array

        if(allIndices.length == 0){     // no key found in array
            System.out.println(-1);
            return;
        }

        for(int i = 0; i < allIndices.length; i++){
            System.out.println(allIndices[i]);
        }
    }

    public static int[] allIndices(int[] arr, int x, int idx, int fsf) {
        if(idx == arr.length)           // BASE CASE
            return new int[fsf];        // returns a new array of size fsf(found x so far)

        if(arr[idx] == x) {             // x found at idx 
            int[] allIndices = allIndices(arr, x, idx + 1, fsf + 1);    // increment fsf
            allIndices[fsf] = idx;      // update all indices array with idx
            return allIndices;          // return updated all indices array
        }

        return allIndices(arr, x, idx + 1, fsf);        // returns all indices array as it is
    }
}

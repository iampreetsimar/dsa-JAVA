// 1. You are given a number n, representing the size of array a.
// 2. You are given n numbers, representing elements of array a.
// 3. You are required to print the longest sequence of consecutive elements in the array (ignoring duplicates).

// Note -> In case there are two sequences of equal length (and they are also the longest), 
// then print the one for which the starting point of which occurs first in the array.

// Input Format
// A number n
// n1
// n2
// .. n number of elements

// Output Format
// Elements of longest sequence of consecutive elements of array in separate lines (ignoring duplicates)

// Constraints
// 1 <= n <= 30
// 0 <= n1, n2, .. n elements <= 15

// Sample Input
// 17
// 12 5 1 2 10 2 13 7 11 8 9 11 8 9 5 6 11

// Sample Output
// 5
// 6
// 7
// 8
// 9
// 10
// 11
// 12
// 13

import java.util.HashMap;
import java.util.Scanner;

public class longestConsecutiveSeq_4 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] input = new int[n];
        for(int i = 0; i < n; i++) {
            input[i] = scn.nextInt();
        }

        longestConsecutiveSeq(input);
    }

    public static void longestConsecutiveSeq(int[] input) {
        // key: {val} , value: {true - if seq starting point | false - not a seq starting point}
        HashMap<Integer, Boolean> map = new HashMap<>();    

        for(int val: input) {   // traverse input - O(N)
            map.put(val, true);     // set all values as seq starting point
        }

        for(int val: input) {   // traverse input - O(N)
            if(map.containsKey(val - 1)) {  // check if key: val - 1 present in map 
                map.put(val, false);    // if present, val cannot be seq starting point
            }
        }

        int maxSeqLen = 0;
        int maxSeqStart = 0;
        for(int val: input) {   // traverse input - O(N)
            if(map.get(val)) {  // check if key: val is seq starting point -> find curr seq length
                int currSeqLen = 1;
                while(map.containsKey(val + currSeqLen)) {  // max O(N) - combining all iterations 
                    currSeqLen++;
                }

                if(currSeqLen > maxSeqLen) {    // check if curr seq length > max seq length
                    maxSeqLen = currSeqLen;     // set max seq length and max seq starting point
                    maxSeqStart = val;
                }
            }
        }   // for - O(N) | while - <= O(N) combining for all iteration -> Total: 2 * O(N) ~ O(N)

        for(int i = 0; i < maxSeqLen; i++) {    // loop max seq length # of times starting from max starting point
            System.out.println(maxSeqStart + i);
        }

        // T.C : 3 * O(N) ~ O(N)
    }
}

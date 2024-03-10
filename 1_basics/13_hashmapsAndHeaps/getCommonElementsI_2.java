// 1. You are given a number n1, representing the size of array a1.
// 2. You are given n1 numbers, representing elements of array a1.
// 3. You are given a number n2, representing the size of array a2.
// 4. You are given n2 numbers, representing elements of array a2.
// 5. You are required to print all elements of a2 which are also present in a1 (in order of their occurence in a2). 
// Make sure to not print duplicates (a2 may have same value present many times).

// Input Format
// A number n1
// n1 number of elements line separated
// A number n2
// n2 number of elements line separated

// Output Format
// All relevant elements of a2 in separate lines (no duplicacy)

// Constraints
// 1 <= n1, n2 <= 100
// 0 <= a1[i], a2[i] < 10
// Time complexity should be O(n)

// Sample Input
// 9
// 5 5 9 8 5 5 8 0 3
// 18 
// 9 7 1 0 3 6 5 9 1 1 8 0 2 4 2 9 1 5

// Sample Output
// 9 0 3 5 8

import java.util.HashMap;
import java.util.Scanner;

public class getCommonElementsI_2 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n1 = scn.nextInt();
        int[] arr1 = new int[n1];
        for(int i = 0; i < n1; i++) {
            arr1[i] = scn.nextInt();
        }

        int n2 = scn.nextInt();
        int[] arr2 = new int[n2];
        for(int i = 0; i < n2; i++) {
            arr2[i] = scn.nextInt();
        }

        getCommonElements(arr1, arr2);
    }

    // print elements of input2 which are common with input1
    // print elements in in order of input2
    // print elements only once - no duplicates
    public static void getCommonElements(int[] input1, int[] input2) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();

        // traverse input1 to store in freq hashmap - O(N)
        for(int val : input1) {
            if(freqMap.containsKey(val)) {
                freqMap.put(val, freqMap.get(val) + 1);
            } else {
                freqMap.put(val, 1);
            }
        }

        // traverse input2 - O(N)
        // if input2 val in hashmap:  
        // print val 
        // remove val from hashmap(so that val cannot be present in map in case of duplicate val comes in input2)
        for(int val : input2) {
            if(freqMap.containsKey(val)) {
                System.out.println(val);
                freqMap.remove(val);
            }
        }

        // T.C.: O(N)
    }
}

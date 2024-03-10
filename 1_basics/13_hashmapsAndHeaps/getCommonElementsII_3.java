// 1. You are given a number n1, representing the size of array a1.
// 2. You are given n1 numbers, representing elements of array a1.
// 3. You are given a number n2, representing the size of array a2.
// 4. You are given n2 numbers, representing elements of array a2.
// 5. You are required to find the intersection of a1 and a2. To get an idea check the example below:
 
// if a1 -> 1 1 2 2 2 3 5
// and a2 -> 1 1 1 2 2 4 5
// intersection is -> 1 1 2 2 5

// Note -> Don't assume the arrays to be sorted. Check out the question video.

// Input Format
// A number n1
// n1 number of elements line separated
// A number n2
// n2 number of elements line separated

// Output Format
// All relevant elements of intersection in separate lines
// The elements of intersection should be printed in order of their occurence in a2.

// Constraints
// 1 <= n1, n2 <= 100
// 0 <= a1[i], a2[i] < 10
// Time complexity should be O(n)

// Sample Input
// 7
// 1 1 2 2 2 3 5
// 7
// 1 1 1 2 2 4 5

// Sample Output
// 1
// 1
// 2
// 2
// 5

import java.util.HashMap;
import java.util.Scanner;

public class getCommonElementsII_3 {
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

        getCommonElementsIntersection(arr1, arr2);
    }

    // print elements of intersection - common in both input1 and input2
    // print elements in in order of input2
    // print elements intersection # of times(min occurence common in both - can have duplicates)
    public static void getCommonElementsIntersection(int[] input1, int[] input2) {
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
        // if input2 val in hashmap and freq of val > 0:  
        // print val 
        // decrease freq of val in map -> this way val is printed minimum # of occurence times in both input1 and input2
        for(int val : input2) {
            if(freqMap.containsKey(val) && freqMap.get(val) > 0) {
                System.out.println(val);
                freqMap.put(val, freqMap.get(val) - 1);
            }
        }

        // T.C.: O(N)
    }
}

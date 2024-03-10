// 1. You are given a number n, representing the size of array a.
// 2. You are given n numbers, representing elements of array a.
// 3. You are given a number k.
// 4. You are required to find and print the k largest elements of array in increasing order.

// Input Format
// Input is managed for you

// Output Format
// Print the k largest elements of array in increasing order in separate lines.

// Constraints
// 1 <= n <= 30
// 0 <= n1, n2, .. n elements <= 100
// 0 < k <= n

// Sample Input
// 13
// 12 62 22 15 37 99 11 37 98 67 31 84 99
// 4

// Sample Output
// 84
// 98
// 99
// 99

import java.util.PriorityQueue;
import java.util.Scanner;

public class kLargestElements_5 {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
           arr[i] = scn.nextInt();
        }
        int k = scn.nextInt();

        kLargestElements(arr, k);
    }

    // use heap(min-priority queue)
    // Similar to Team Selection Process
    // In a team of k players, if a new player arrives and is stronger than the curr weakest player of team
    // The weakest player is removed and new player is added to team instead making it k players again
    // After going through N players, we'll have k players who are the strongest out of N 
    public static void kLargestElements(int[] input, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < input.length; i++) {     // -> O(NlogK)
            if(i < k) {     // make a k sized team
                pq.add(input[i]);
            } else {
                if(input[i] > pq.peek()) {      // compare if new player is stronger that curr weakest
                    pq.remove();    // remove curr weakest
                    pq.add(input[i]);   // add new player -> diff player becomes weakest -> team size is k
                }
            }
        }

        while(pq.size() > 0) {  // k size team of strongest players
            System.out.println(pq.remove());    // removed in increasing order
        }

        // Each addition/deletion takes logK as size of heap is at max O(K)
        // For N items, TC: O(NlogK), SC: O(K) as heap size is atmost K
    }
}

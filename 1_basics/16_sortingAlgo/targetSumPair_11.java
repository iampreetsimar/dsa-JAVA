// Given an array of integers of size: N and tar, print all pairs whose sum equals tar such that
// for valid pairs: (a1, b1), (a2, b2), (a3, b3)
// 1. a1 < b1 and a2 < b2 and a3 < b3
// 2. a1 < a2 < a3

// Achieve this in O(NlogN).

// INPUT
// 11
// 7 15 3 18 6 4 19 2 12 11 9
// 15

// OUTPUT
// 3 - 12
// 4 - 11
// 6 - 9

import java.io.*;
import java.util.Arrays;

public class targetSumPair_11 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(row[i]);
        }
        int tar = Integer.parseInt(br.readLine());

        printTargetSumPairs(arr, tar);
    }

    public static void printTargetSumPairs(int[] arr, int tar) {
        Arrays.sort(arr);   // O(NlogN) -> i has smaller numbers now | j has larger numbers now
        int i = 0, j = arr.length - 1;

        while(i < j) {  // O(N)
            if(arr[i] + arr[j] > tar) {
                j--; // reducing j will reduce pair sum | cannot increment i as that will increase pair sum
            } else if(arr[i] + arr[j] < tar) {
                i++; // increasing i will increase sum | cannot decrement j as that will decrease pair sum
            } else {    // pair sum == tar
                System.out.println(arr[i] + " - " + arr[j]);
                i++; j--;  // find next pair by incrementing i and decrementing j
            }
        }
    }
}

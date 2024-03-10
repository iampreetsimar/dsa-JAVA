// 1. You are given a number n, representing the size of array a.
// 2. You are given n numbers, representing elements of array a.
// 3. You are given a number k.
// 4. Rotate the array a, k times to the right (for positive values of k), and to
// the left for negative values of k.

// Input Format
// Input is managed for you

// Output Format
// Output is managed for you

// Constraints
// 0 <= n < 10^4
// -10^9 <= a[i], k <= 10^9

// Sample Input
// 5

// 1
// 2
// 3
// 4
// 5

// 3

// Sample Output
// 3 4 5 1 2

import java.util.*;

public class rotateArray_8 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }

        int k = scn.nextInt();

        rotateArray(arr, k);
        displayArray(arr);
    }

    public static void displayArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for(int val : arr) {
            sb.append(val + " ");
        }

        System.out.println(sb);
    }

    public static void rotateArray(int[] arr, int k) {
        // rebalance k
        k %= arr.length;
        if(k < 0)
            k += arr.length;
        
        reverseArray(arr, 0, arr.length - k - 1);
        reverseArray(arr, arr.length - k, arr.length - 1);
        reverseArray(arr, 0, arr.length - 1);
    }

    public static void reverseArray(int[] arr, int startIdx, int endIdx) {
        while(startIdx < endIdx) {
            int temp = arr[startIdx];
            arr[startIdx] = arr[endIdx];
            arr[endIdx] = temp;

            startIdx++; endIdx--;
        }
    }
}

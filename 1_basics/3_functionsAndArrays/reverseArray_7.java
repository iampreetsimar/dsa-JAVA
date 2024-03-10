// 1. You are given a number n, representing the size of array a.
// 2. You are given n numbers, representing elements of array a.
// 3. You are required to reverse the contents of array a.

// Input Format
// Input is managed for you

// Output Format
// Output is managed for you

// Constraints
// 0 <= n < 10^4
// -10^9 <= a[i] <= 10^9

// Sample Input
// 5

// 1
// 2
// 3
// 4
// 5

// Sample Output
// 5 4 3 2 1

import java.util.*;

public class reverseArray_7 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }

        reverseArray(arr);
        printArray(arr);
    }

    public static void reverseArray(int[] arr) {
        int i = 0;
        int j = arr.length - 1;
        while(i < j) {
            //swap
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            i++; j--;
        }
    }

    public static void printArray(int[] arr) {
        for(int val : arr) {
            System.out.println(val);
        }
    }
}

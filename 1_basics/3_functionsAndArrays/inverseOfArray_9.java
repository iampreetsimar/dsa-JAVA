// 1. You are given a number n, representing the size of array a.
// 2. You are given n numbers, representing elements of array a.
// 3. You are required to calculate the inverse of array a.

// For definition and constraints check this link
// https://www.pepcoding.com/resources/online-java-foundation/getting-started/inverse-of-a-number/ojquestion
// The only difference is the range of values is from 0 to n - 1, instead of 1 to n.

// Input Format
// Input is managed for you

// Output Format
// Output is managed for you

// Constraints
// 0 <= n < 10^7
// For more constraints check this
// https://www.pepcoding.com/resources/online-java-foundation/getting-started/inverse-of-a-number/ojquestion
// The only difference is the range of values is from 0 to n - 1, instead
// of 1 to n

// Sample Input
// 5

// 4
// 0
// 2
// 3
// 1

// Sample Output
// 1
// 4
// 2
// 3
// 0

import java.util.*;

public class inverseOfArray_9 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }

        arr = inverseArray(arr);
        displayArray(arr);
    }

    public static int[] inverseArray(int[] arr) {
        int[] newArr = new int[arr.length];
        
        for(int i = 0; i < arr.length; i++) {
            newArr[arr[i]] = i;
        }

        return newArr;
    }

    public static void displayArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for(int val : arr) {
            sb.append(val + "\n");
        }

        System.out.println(sb);
    }
}

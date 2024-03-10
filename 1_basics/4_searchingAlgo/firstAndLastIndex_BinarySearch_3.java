// 1. You are given a number n, representing the size of array a.
// 2. You are given n numbers, representing elements of array a.

// Asssumption - Array is sorted. Array may have duplicate values.

// Input Format
// A number n
// n1
// n2
// .. n number of elements
// A number d

// Output Format
// A number representing first index
// A number representing last index

// Constraints
// 1 <= n <= 1000
// 1 <= n1, n2, .. n elements <= 100
// 1 <= d <= 100

// Sample Input
// 15

// 1
// 5
// 10
// 15
// 22
// 33
// 33
// 33
// 33
// 33
// 40
// 42
// 55
// 66
// 77

// 33

// Sample Output
// 5
// 9

import java.util.*;

public class firstAndLastIndex_BinarySearch_3 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }

        int key = scn.nextInt();

        firstIndex(arr, key);
        lastIndex(arr, key);
    }

    public static void firstIndex(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int firstIdx = -1;

        while(low <= high) {
            int mid = (low + high)/2;

            if(key < arr[mid])
                high = mid - 1;
            else if(key > arr[mid])
                low = mid + 1;
            else {
                firstIdx = mid;
                high = mid - 1;
            }
        }

        System.out.println("First Idx: " + firstIdx);
    }

    public static void lastIndex(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int lastIdx = -1;

        while(low <= high) {
            int mid = (low + high)/2;

            if(key < arr[mid])
                high = mid - 1;
            else if(key > arr[mid])
                low = mid + 1;
            else {
                lastIdx = mid;
                low = mid + 1;
            }
        }

        System.out.println("First Idx: " + lastIdx);
    }
}

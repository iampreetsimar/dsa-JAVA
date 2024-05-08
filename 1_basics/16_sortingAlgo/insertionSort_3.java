// Given N: array size, and N numbers: an array of integers, 
// sort the array in increasing order using Insertion Sort algorithm.

import java.io.*;

public class insertionSort_3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(row[i]);
        }

        insertionSort(arr);
        System.out.print("Sorted array: ");
        displayArr(arr);
    }

    public static void displayArr(int[] arr) {
        for(int num: arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Assume item at 0th idx is sorted initially
    // At ith idx, compare items starting from ith idx till 0th idx
    // and check if item in front is smaller than item at its prev idx.
    // Like a reverse bubble, move smaller item to sorted portion of array
    // but swap also happens in sorted portion so that item reaches it correct pos
    // For N items -> N-1 iters : 1 to N-1
    // Each iter idx tries to reach its correct pos in sorted portion
    // For each iter -> j : iter - 1 to 0
    // As soon as item reaches it correct pos, we stop the iteration
    // This makes TC: O(N) for BEST CASE scenario
    public static void insertionSort(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            for(int j = i - 1; j >= 0; j--) {
                if(isSmaller(arr, j + 1, j))
                    swap(arr, j + 1, j);
                else
                    break;  
            }
            System.out.print("Array after curr iteration: ");
            displayArr(arr);
        }
    }

    // checks if item[j+1] < item[j] -> return true
    public static boolean isSmaller(int[] arr, int jPlus1, int j) {
        System.out.println("Comparing " + arr[jPlus1] + " with " + arr[j]);
        return arr[jPlus1] < arr[j];
    }

    public static void swap(int[] arr, int jPlus1, int j) {
        System.out.println("Swapping " + arr[jPlus1] + " with " + arr[j]);
        int temp = arr[jPlus1];
        arr[jPlus1] = arr[j];
        arr[j] = temp;
    }
}

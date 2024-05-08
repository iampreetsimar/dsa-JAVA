// Given N: array size, and N numbers: an array of integers, 
// sort the array in increasing order using Selection Sort algorithm.

import java.io.*;

public class selectionSort_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(row[i]);
        }

        selectionSort(arr);
        System.out.print("Sorted array: ");
        displayArr(arr);
    }

    public static void displayArr(int[] arr) {
        for(int num: arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Each iter selects a place where item has to be placed correctly
    // For N items -> N-1 iters: 0 -> < N-1
    // Assume the item at iter idx is smallest
    // Move from j: iter + 1 to end of array -> to find idx of smallest item
    // Swap smallest item with item at iter idx
    // This ensures items before iter idx are sorted and the next greater item to it is found
    // from rest of the items and swapped with iter idx to place at correct pos
    public static void selectionSort(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;

            for(int j = i + 1; j < arr.length; j++) {
                if(isSmaller(arr, j, minIdx))
                    minIdx = j;
            }

            swap(arr, minIdx, i);   // happens only after min item is foundfor curr iter
            System.out.print("Array after item at idx: " + i + " is sorted: ");
            displayArr(arr);
        }
    }

    // checks if item[j] is smaller than item[minIdx] -> new smaller item found
    public static boolean isSmaller(int[] arr, int j, int minIdx) {
        System.out.println("Comparing " + arr[j] + " with " + arr[minIdx]);
        return arr[j] < arr[minIdx];
    }

    public static void swap(int[] arr, int minIdx, int i) {
        System.out.println("Swapping " + arr[minIdx] + " with " + arr[i]);
        int temp = arr[minIdx];
        arr[minIdx] = arr[i];
        arr[i] = temp;
    }
}

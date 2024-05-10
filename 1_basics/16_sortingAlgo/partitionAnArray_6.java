// Given N: array size, N numbers: an array of integers and a pivot.
// Re-arrange given array in such a way that all items smaller or equal to pivot lie on its left side
// and all items greater than it lie on its right side.
// Achieve this in linear time.

import java.io.*;

public class partitionAnArray_6 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(row[i]);
        }
        int pivot = Integer.parseInt(br.readLine());

        partitionArr(arr, pivot);
        System.out.print("Partitioned array: ");
        displayArr(arr);
    }

    public static void displayArr(int[] arr) {
        for(int num: arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Range: i to [] end -> unknown items
    // Range: 0 to j-1 -> items <= pivot
    // Range: j to i-1 -> items > pivot
    // Output is not necessarily sorted(it may or may not) but ensures pivot property
    public static void partitionArr(int[] arr, int pivot) {
        int i = 0, j = 0;
        while(i < arr.length) { // curr item[i]: initial point of unknown range
            if(arr[i] <= pivot) {  // curr item <= pivot
                swap(arr, i, j); // swap curr item with item at initial point of larger range
                i++; j++;   // extend initial point of larger and unknown range
            } else  // curr item > pivot
                i++;   // extend initial point of unknown range
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

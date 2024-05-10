// Given N: array size, and N numbers: an array of integers, 
// sort the array in increasing order using Quick Sort algorithm.

import java.io.*;

public class quickSort_7 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(row[i]);
        }

        quickSort(arr, 0, arr.length - 1);
        System.out.print("Sorted array: ");
        displayArr(arr);
    }

    public static void displayArr(int[] arr) {
        for(int num: arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // uses partition to place pivot to correct pos and divides array on pivotIdx
    public static void quickSort(int[] arr, int low, int high) {
        if(low >= high)    // either 1 or 0 items -> no need to partition
            return;
        
        int pivotIdx = partition(arr, low, high);

        // divide array virtually based on pivotIdx
        quickSort(arr, low, pivotIdx - 1);
        quickSort(arr, pivotIdx + 1, high);
    }

    // Range: i(low) to end(high) -> unknown items
    // Range: 0 to j-1 -> items <= pivot
    // Range: j to i-1 -> items > pivot
    // returns pivotIdx after partition: j-1 
    // (j-1) ensures item <= pivot. Since pivot is always from array itself, it will be at j-1.
    // ensures pivot is at its correct pos
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        System.out.println("Pivot is: " + pivot);

        int i = low, j = low;

        while(i <=high) {
            if(arr[i] <= pivot) {
                swap(arr, i, j);
                i++; j++;
            } else {
                i++;
            }
        }

        System.out.println("Pivot Idx: " + (j-1));
        return j-1;
    }

    public static void swap(int[] arr, int i, int j) {
        System.out.println("Swapping " + arr[i] + " with " + arr[j]);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

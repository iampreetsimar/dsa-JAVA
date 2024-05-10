// Given N: array size, N numbers: array items and k, find kth smallest item of array using
// Quick Select algorithm.

import java.io.*;

public class quickSelect_8 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(row[i]);
        }
        int k = Integer.parseInt(br.readLine());

        System.out.println(quickSelect(arr, k - 1, 0, arr.length - 1));
    }


    // variation of Quick Sort: uses only 1 recursive call
    // initial k: k - 1 as kth smallest item will be at idx: k-1 if array is sorted
    // use parition to find pivotIdx
    // if k > pivotIdx -> kth smallest item will be on right side of pivot
    // else if k < pivotIdx -> kth smallest item will be on left side of pivot
    // else: k == pivotIdx -> curr item is kth smallest
    public static int quickSelect(int[] arr, int k, int low, int high) {
        int pivotIdx = partition(arr, low, high);

        if(k > pivotIdx) 
            return quickSelect(arr, k, pivotIdx + 1, high);
        else if(k < pivotIdx)
            return quickSelect(arr, k, low, pivotIdx - 1);
        else
            return arr[pivotIdx];
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low, j = low;

        while(i <= high) {
            if(arr[i] <= pivot) {
                swap(arr, i, j);
                i++; j++;
            } else  
                i++;
        }
        return j-1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

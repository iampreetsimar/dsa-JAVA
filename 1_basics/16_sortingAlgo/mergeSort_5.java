// Given N: array size, and N numbers: an array of integers, 
// sort the array in increasing order using Merge Sort algorithm.

import java.io.*;

public class mergeSort_5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(row[i]);
        }

        int[] sortedArr = mergeSort(arr, 0, arr.length - 1);
        System.out.print("Sorted array: ");
        displayArr(sortedArr);
    }

    public static void displayArr(int[] arr) {
        System.out.print("[ ");
        for(int num: arr) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }

    // initial: low = 0, high = arr.length-1
    // use recursion - divide and conquer approach
    // divide array into two halfs which are sorted by recursion and merge into
    // a new array which is sorted
    // not actually dividing the array, using indexes to divide -> merge happens in a new array
    public static int[] mergeSort(int[] arr, int low, int high) {
        if(low == high) {   // BASE CASE: only 1 element remains
            int[] baseArr = new int[1]; // create a new array of size:1
            baseArr[0] = arr[low];  // add curr element to new array
            return baseArr; // return new array
        }

        int mid = (low + high) / 2; // divides array into two: low to mid, mid+1 to high
        int[] firstHalfSortedArr = mergeSort(arr, low, mid);    // returns a sorted array from low to mid
        int[] secondHalfSortedArr = mergeSort(arr, mid + 1, high);  // returns a sorted array from mid+1 to high
        return mergeTwoSortedArrays(firstHalfSortedArr, secondHalfSortedArr);   // merges both into one sorted []
    }

    // using mergeTwoSortedArrays approach
    public static int[] mergeTwoSortedArrays(int[] a, int[] b) {
        int[] res = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        System.out.println("Merging arrays: ");
        displayArr(a);
        displayArr(b);

        while(i < a.length && j < b.length) {
            res[k++] = a[i] < b[j] ? a[i++] : b[j++];
        }

        while(i < a.length) {
            res[k++] = a[i++];
        }

        while(j < b.length) {
            res[k++] = b[j++];
        }

        return res;
    }
}

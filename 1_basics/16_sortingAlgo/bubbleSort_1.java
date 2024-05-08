// Given N: array size, and N numbers: an array of integers, 
// sort the array in increasing order using Bubble Sort algorithm.

import java.io.*;

public class bubbleSort_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(row[i]);
        }

        bubbleSort(arr);
        System.out.print("Sorted array: ");
        displayArr(arr);
    }

    public static void displayArr(int[] arr) {
        for(int num: arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // largest item from unsorted part of array moves to front of sorted part(at back)
    // like the largest bubble moves to top and second largest moves below it
    // For N items: N-1 iterations
    // In each iteration: [N - iteration] operations(compare/swap or both)
    // total iter(N-1) : 1 -> N-1
    // oper at each iter(N-iter) : 0 -> < N - iter
    // For each oper, if item at next idx to curr is smaller -> items are swapped
    // -> making larger item move to back of array like a bubble floats up
    // TC: O(N^2)
    public static void bubbleSort(int[] arr) {
        for(int iter = 1; iter < arr.length; iter++) {
            for(int idx = 0; idx < arr.length - iter; idx++) {
                if(isSmaller(arr, idx + 1, idx)) {
                    swap(arr, idx + 1, idx);
                }
            }
            System.out.print("Arr after iteration " + iter + " : ");
            displayArr(arr);
        }
    }

    // if item[nextIdx] < item[currIdx] -> return true
    public static boolean isSmaller(int[] arr, int nextIdx, int currIdx) {
        System.out.println("Comparing " + arr[nextIdx] + " with " + arr[currIdx]);
        return arr[nextIdx] < arr[currIdx];
    }

    public static void swap(int[] arr, int nextIdx, int currIdx) {
        System.out.println("Swapping " + arr[nextIdx] + " with " + arr[currIdx]);
        int temp = arr[nextIdx];
        arr[nextIdx] = arr[currIdx];
        arr[currIdx] = temp;
    }
}

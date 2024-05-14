// Given N numbers having multiple digits, sort it in increasing order using Radix Sort algorithm.

import java.io.*;

public class radixSort_13 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int max = Integer.MIN_VALUE;
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(row[i]);
            max = Math.max(max, arr[i]);
        }

        radixSort(arr, max);
        display(arr);
    }

    // uses COUNT SORT for stable sorting at each digits place(1s, 10s, 100s,...)
    public static void radixSort(int[] arr, int max) {
        int exp = 1;

        while(exp <= max) { // loop runs max's count of digit times
            countSort(arr, exp);
            exp *= 10;
        }
    }

    // exp represents the digits place using which comparisons for sorting happens
    // executes max's count of digit times for each digit's place
    // No need of mapping idx to values as min is 0 so each digit is mapped to its own idx
    public static void countSort(int[] arr, int exp) {
        int[] freqToPrefixSumArr = new int[10]; // range:[0,9] as only digits
        for(int val: arr) { // compute freq of curr place digit from all numbers
            freqToPrefixSumArr[(val/exp) % 10]++; // extract digit: (number/exp) % 10
        }

        for(int i = 1; i < freqToPrefixSumArr.length; i++) {    // compute prefix sum array
            freqToPrefixSumArr[i] += freqToPrefixSumArr[i - 1];
        }

        int[] res = new int[arr.length];    // res[] of size same as arr

        for(int i = arr.length - 1; i >= 0; i--) {  // traverse arr in reverse for stable sort
            int pos = freqToPrefixSumArr[(arr[i] / exp) % 10]--;    // last pos of digit(curr number) in sorted[]
            int idx = pos - 1; // idx of last pos
            res[idx] = arr[i];  // number placed at sorted idx in res[]
        }

        for(int i = 0; i < arr.length; i++) {   // res[] copied to OG []
            arr[i] = res[i];
        }
    }

    public static void display(int[] arr) {
        for(int val: arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}

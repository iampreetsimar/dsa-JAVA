// Given N: array size, and N numbers: an array of integers, 
// sort the array in increasing order using Count Sort algorithm.

import java.io.*;

public class countSort_12 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(row[i]);
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }

        countSort(arr, min, max);
        display(arr);
    }

    // Count Sort is a STABLE SORT -> used for shorter range
    // 1. Find the max and min of elements
    public static void countSort(int[] arr, int min, int max) {
        int range = max - min + 1;  // 2. Find range
        int[] freqToPreFixSumArr = new int[range];  // freq array of size: range

        for(int i = 0; i < arr.length; i++) {  // 3. compute frequency of each element
            int val = arr[i];   // value
            int freqIdx = val - min;    // values's mapped idx
            freqToPreFixSumArr[freqIdx]++;  // incrementing freq based on its occurent in arr
        }

        for(int i = 1; i < freqToPreFixSumArr.length; i++) { // 4. convert to prefix sum array 
            freqToPreFixSumArr[i] += freqToPreFixSumArr[i - 1];
        }

        int[] res = new int[arr.length];    // 5. create new array to store sorted items 

        for(int i = arr.length - 1; i >= 0; i--) {  // 6. traverse given array in reverse order
            int freqIdx = arr[i] - min;  // mapped idx of curr val
            // last pos of curr val from prefix sum array
            int pos = freqToPreFixSumArr[freqIdx]--;  // also decrement last pos for next occurence of curr val
            int idx = pos - 1;  // idx of curr val(using last pos) in sorted res[]
            res[idx] = arr[i];  // place curr val in sorted res[]
        }

        for(int i = 0; i < arr.length; i++) {   // 7. update given array using sorted res[]
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

// Given an array of 0s, 1s and 2s, rearrange given array such that all 0s are before all 1s and all 1s before all 2s.
// Achieve this in linear time in single pass.

import java.io.*;

public class sort012_10 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(row[i]);
        }

        sort012Opt(arr);
        display(arr);
    }

    // 0s range: 0 -> j - 1
    // 1s range: j -> i - 1
    // unknown range: i to k
    // 2s range: k + 1 -> arr.length - 1
    // <---0s---><---1s---><---un/kn---><---2s--->
    public static void sort012Opt(int[] arr) {
        int i = 0, j = 0, k = arr.length - 1;

        while(i <= k) {
            if(arr[i] == 0) {   
                swap(arr, i, j);    // swap 0 at i with 1 at j
                j++;  // 1s range moves forward by 1
                i++; // unknown range moves forward by 1(size also decreased)
            } else if(arr[i] == 1) {
                i++;    // unknown range moves forward by 1(size also decreased)
            } else {    // 2 at i
                swap(arr, i, k);   // swap 2 at i with unknown from k
                k--;    // 2s range moves backward by 1(size increased)
                // now unknown at i is checked in next iteration
            }
        }
    }

    // 0s range: 0 -> j - 1
    // 1s range: j -> k - 1
    // 2s range: k -> i - 1
    // unknown range: i -> arr.length - 1
    // <---0s---><---1s---><---2s---><---un/kn--->
    public static void sort012(int[] arr) {
        int i = 0, j = 0, k = 0;

        while(i < arr.length) {
            if(arr[i] == 0) {   // swap 0 at i with first 1 at j
                swap(arr, i, j);  // this puts 1 at i becoming unknown again(checked in next iteration)
                j++;    // 1s range moves forward by 1(size also decreased)
            } else if(arr[i] == 1) {
                swap(arr, i, k);    // swap 1 at i with first 2 at k
                i++; k++;
            } else {    // 2 at i
                i++;    // unknown range moves forward by 1(size also decreased)
            }
        }
    }

    public static void display(int[] arr) {
        for(int val: arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}

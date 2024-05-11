// Given an array of 0s and 1s, rearrange given array such that all 0s are before all 1s.
// Achieve this in linear time in single pass.

import java.io.*;

public class sort01_9 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(row[i]);
        }

        sort01(arr);
        display(arr);
    }

    // use partitioning concept with pivot as 0
    // left side of pivot(including pivot): all zeroes
    // right side of pivot: all ones
    // Unknown range: i -> arr.length - 1
    // Zeros range: 0 -> j - 1
    // Ones range: j -> i - 1 
    public static void sort01(int[] arr) {
        int i = 0, j = 0;
        while(i < arr.length) {
            if(arr[i] == 0) {
                swap(arr, i, j);
                i++; j++;
            } else 
                i++;
        }
    }

    public static void display(int[] arr) {
        for(int val: arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

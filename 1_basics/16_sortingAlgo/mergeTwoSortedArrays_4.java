// Given two sorted arrays(a,b) of integers, merge and form one sorted array in linear time complexity.

import java.io.*;

public class mergeTwoSortedArrays_4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        String[] array1 = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(array1[i]);
        }

        int m = Integer.parseInt(br.readLine());
        int[] b = new int[m];
        String[] array2 = br.readLine().split(" ");
        for(int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(array2[i]);
        }

        int[] mergedArray = mergeTwoSortedArrays(a, b);
        System.out.print("Sorted array: ");
        displayArr(mergedArray);
    }

    public static void displayArr(int[] arr) {
        for(int num: arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // input: array a, array b -> output: array res having all items of a and b in sorted order
    public static int[] mergeTwoSortedArrays(int[] a, int[] b) {
        int[] res = new int[a.length + b.length];   // res[] length == a[].length + b[].length
        int pointerA = 0,  pointerB = 0, pointerK = 0;  // pointer starts from idx:0 for all []s

        // if item in a[] < item in b[] -> add item from a[] to res[] -> increment a[] and res[] pointer
        // else -> add item from b[] to res[] -> increment b[] and res[] pointer
        while(pointerA < a.length && pointerB < b.length) {
            res[pointerK++] = a[pointerA] < b[pointerB] ? 
                              a[pointerA++] : b[pointerB++];
        }

        // executes if b[] is read completely and items from a[] remains
        while(pointerA < a.length){
            res[pointerK++] = a[pointerA++];
        }

        // executes if a[] is read completely and items from b[] remains
        while(pointerB < b.length) {
            res[pointerK++] = b[pointerB++];
        }
        return res;
    }
}

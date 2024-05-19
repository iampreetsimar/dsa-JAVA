// Given a sorted and rotated array of size N, find its pivot. 
// Pivot is the smallest element in this case.
// Achieve this in O(logN) and assume that all elements of array are distinct.

// INPUT
// Case 1: 10 20 30 40 50
// Case 2: 50 10 20 30 40
// Case 3: 40 50 10 20 30
// Case 4: 30 40 50 10 20
// Case 5: 20 30 40 50 10

// OUTPUT
// 10

import java.io.*;

public class pivotOfSortedRotatedArray_15 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(row[i]);
        }

        System.out.println(findPivotOfSortedRotatedArr(arr));
    }

    // use Binary Search concept to achieve search in O(logN)
    public static int findPivotOfSortedRotatedArr(int[] arr) {
        int low = 0, high = arr.length - 1;

        // not low == high as for pivot search we need atleast 2 items
        // if only 1 item present -> that item becomes pivot
        while(low < high) {
            int mid = (low + high) / 2;

            if(arr[mid] < arr[high]) {  // all items b/w mid and high in increasing order
                high = mid; // pivot must be in left portion(from low to mid)
            } else {    // arr[mid] > arr[high] -> there is pivot in right portion(dip in present)
                low = mid + 1;  // from mid+1 to high -> low becomes mid+1
            }
        }

        return arr[high];   // low == high -> pivot item at low/high
    }
}

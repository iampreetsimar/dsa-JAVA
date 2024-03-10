// 1. You are given a number n, representing the size of array a.
// 2. You are given n numbers, representing elements of array a.
// 3. The array is nearly sorted. Every element is at-max displaced k spots left or right 
//    to it's position in the sorted array. Hence it is being called k-sorted array.
// 4. You are required to sort and print the sorted array.

// Note -> You can use at-max k extra space and nlogk time complexity.

// Input Format
// Input is managed for you

// Output Format
// Print the elements of sorted array in separate lines.

// Constraints
// 1 <= n <= 30
// 0 <= n1, n2, .. n elements <= 100
// 0 < k <= n

// Sample Input
// 9
// 3 2 4 1 6 5 7 9 8
// 3

// Sample Output
// 1 2 3 4 5 6 7 8 9

import java.util.PriorityQueue;
import java.util.Scanner;

public class sort_K_SortedArray_6 {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
           arr[i] = scn.nextInt();
        }
        int k = scn.nextInt();

        printSortKSorted(arr, k);
        sortKSorted(arr, k);
    }

    // only prints after sorting
    public static void printSortKSorted(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i <= k; i++) {   // add k + 1 elements to heap(min priority)
            pq.add(arr[i]);
        }

        for(int i = k + 1; i < arr.length; i++) {   // for each iteration, 
            System.out.print(pq.remove() + " ");    // top from heap placed at correct pos
            pq.add(arr[i]); // curr element added to heap
        }

        while(pq.size() > 0) {  // after array is read, elements remaining in heap
            System.out.print(pq.remove() + " ");    // will be removed in correct order
        }
        System.out.println();
    }

    // sorts array inplace and prints sorted array
    // element at idx:i can jump atmost k times to left or k times to right
    // atmost pos to left: i - k - 1
    // add k + 1 elements(idx: 0 to k) to heap, after (k+1)th element, we can get correct pos at (i - k - 1)
    public static void sortKSorted(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i <= k; i++) {   // add k + 1 elements to heap(min priority)
            pq.add(arr[i]); 
        }

        int i = k + 1;  // after (k+1)th element pos
        while(i < arr.length) {     // for each iteration, 
            arr[i - k - 1] = pq.remove();   // top from heap placed at correct pos, i - k - 1
            pq.add(arr[i]); // curr element added to heap
            i++;
        }

        while(pq.size() > 0) {  // after array is read, elements remaining in heap
            arr[i - k - 1] = pq.remove();  // top from heap placed at correct pos, i - k - 1
            i++;
        }
        
        // arr is sorted, print it
        for(int val: arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}

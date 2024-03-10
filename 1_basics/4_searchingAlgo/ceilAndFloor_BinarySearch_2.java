// 1. You are given a number n, representing the size of array a.
// 2. You are given n numbers, representing elements of the array a.
// 3. You are given another number d.
// 4. You are required to find the ceil and floor of d in array a.

// Input Format
// A number n
// n1
// n2
// .. n number of elements
// A number d

// Output Format
// A number representing ceil
// A number representing floor

// Constraints
// 1 <= n <= 1000
// -10^9 <= n1, n2, .. n elements <= 10^9
// -10^9 <= d <= 10^9

// Sample Input
// 10

// 1
// 5
// 10
// 15
// 22
// 33
// 40
// 42
// 55
// 66

// 34

// Sample Output
// 40
// 33

import java.util.*;

public class ceilAndFloor_BinarySearch_2 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }

        int key = scn.nextInt();

        ceilAndFloor(arr, key);
    }

    public static void ceilAndFloor(int[] arr, int key) {
        int low = 0; 
        int high = arr.length - 1;
        int floor = 0;
        int ceil = 0;

        while(low <= high) {
            int mid = (low + high)/2;

            if(key < arr[mid]) {
                high = mid - 1;
                ceil = arr[mid];
            } else if(key > arr[mid]) {
                low = mid + 1;
                floor = arr[mid];
            } else {
                ceil = arr[mid];
                floor = arr[mid];
                break;
            }
        }

        System.out.println("Ceil: " + ceil);
        System.out.println("Floor: " + floor);
    }

}

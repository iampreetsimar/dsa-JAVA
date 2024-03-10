// 1.You are given a number n, representing the size of array a.
// 2.You are given n distinct numbers, representing elements of array a.
// 3. You are given another number d.
// 4. You are required to check if d number exists in the array a and at what index (0 based). 
// If found print the index, otherwise print -1.

// Input Format
// A number n
// n1
// n2
// .. n number of elements
// A number d

// Output Format
// A number representing index at which d is found in array a and -1 if not found

// Constraints
// 1 <= n <= 10^7
// -10^9 <= n1, n2 
// .. n elements <= 10^9
// -10^9 <= d <= 10^9

// Sample Input
// 6
// 15
// 30
// 40
// 4
// 11
// 9
// 40

// Sample Output
// 2

import java.util.*;

public class findElementInArray_3 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }

        int ele = scn.nextInt();

        System.out.println(findElement(arr, ele));
    }

    public static int findElement(int[] arr, int ele) {
        int index = -1;

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == ele) {
                index = i;
                break;
            }
        }

        return index;
    }
}

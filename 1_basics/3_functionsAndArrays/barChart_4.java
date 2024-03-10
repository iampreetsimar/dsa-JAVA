// 1. You are given a number n, representing the size of array a.
// 2. You are given n numbers, representing elements of array a.
// 3. You are required to print a bar chart representing value of arr a.

// Input Format
// A number n
// n1
// n2
// .. n number of elements

// Output Format
// A bar chart of asteriks representing value of array a

// Constraints
// 1 <= n <= 30
// 0 <= n1, n2, .. n elements <= 10

// Sample Input
// 5
// 3
// 1
// 0
// 7
// 5

// Sample Output
// 			*		
// 			*		
// 			*	*	
// 			*	*	
// *			*	*	
// *			*	*	
// *	*		*	*	

import java.util.*;

public class barChart_4 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }

        printBarChart(arr);
    }

    public static void printBarChart(int[] arr) {
        // find max
        int max = arr[0];
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] > max)
                max = arr[i];
        }

        //print floors
        for(int floor = max; floor >= 1; floor--) {
            for(int i = 0; i < arr.length; i++) {
                if(arr[i] >= floor)
                    System.out.print("*\t");
                else    
                    System.out.print("\t");
            }

            System.out.println();
        }
    }
}

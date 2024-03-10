// 1. You are given a number n, representing the size of array a.
// 2. You are given n numbers, representing elements of array a.
// 3. You are required to print an inverted bar chart representing value of arr a.

// Input Format
// A number n
// n1
// n2
// .. n number of elements

// Output Format
// An inverted bar chart of asteriks representing value of array a

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
// *	*		*	*	
// *			*	*	
// *			*	*	
// 			    *	*	
// 			    *	*	
// 			    *		
// 			    *	


import java.util.*;

public class invertedBarChart_12 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }

        // find max level
        int max = arr[0];
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] > max)
                max = arr[i];
        }

        // loop to print levels
        for(int floor = 1; floor <= max; floor++) {

            // loop to access array val
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

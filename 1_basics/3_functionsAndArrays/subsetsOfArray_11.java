// 1. You are given a number n, representing the count of elements.
// 2. You are given n numbers.
// 3. You are required to print all subsets of arr. Each subset should be
// on separate line. For more clarity check out sample input and output.

// Input Format
// A number n
// n1
// n2
// .. n number of elements

// Output Format
// [Tab separated elements of subset]
// ..
// All subsets

// Constraints
// 1 <= n <= 10
// 0 <= n1, n2, .. n elements <= 10^3

// Sample Input
// 3

// 10
// 20
// 30

// Sample Output
// -	-	-	
// -	-	30	
// -	20	-	
// -	20	30	
// 10	-	-	
// 10	-	30	
// 10	20	-	
// 10	20	30	

import java.util.*;

public class subsetsOfArray_11 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        
        int limit  = (int)Math.pow(2, arr.length);   // finds no of subsets

        for(int i = 0; i < limit; i++) {    // loops over 0 to limit - 1 to convert them to binary and get the subset in a string
            String subset = "";
            int temp = i;

            // loop over array from the back to get the element based on binary choice
            for(int j = arr.length - 1; j >= 0; j--) {
                int rem = temp % 2;
                temp /= 2;

                if(rem == 0)
                    subset = "-\t" + subset;    // choice is 0 so _ will be added
                else    
                    subset = arr[j] + "\t" + subset;    // choice is 1 so element will be added
            }

            System.out.println(subset);
        }
    }
}

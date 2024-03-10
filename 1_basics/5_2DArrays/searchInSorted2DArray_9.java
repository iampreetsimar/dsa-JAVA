// 1. You are given a number n, representing the number of rows and columns of a square matrix.
// 2. You are given n * n numbers, representing elements of 2d array a. 
// Note - Each row and column is sorted in increasing order.
// 3. You are given a number x.
// 4. You are required to find x in the matrix and 
// print it's location int (row, col) format as discussed in output format below.
// 5. In case element is not found, print "Not Found".

// Input Format
// A number n
// e11
// e12..
// e21
// e22..
// .. n * n number of elements of array a
// A number x

// Output Format
// row
// col of the location where element is found or "Not Found" if element is not in the matrix

// Constraints
// 1 <= n <= 10^2
// -10^9 <= e11, e12, .. n * m elements <= 10^9
// All rows and columns are sorted in increasing order

// Sample Input
// 4

// 11 12 13 14
// 21 22 23 24
// 31 32 33 34
// 41 42 43 44

// 43

// Sample Output
// 3
// 2

import java.util.*;

public class searchInSorted2DArray_9 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int[][] arr = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                arr[i][j] = scn.nextInt();
            }
        }

        int key = scn.nextInt();

        // search in sorted 2D array
        int row = 0;
        int col = arr.length - 1;

        // we enter from top right corner
        while(row < arr.length && col >= 0) {
            if(key < arr[row][col])
                col--;
            else if(key > arr[row][col])
                row++;
            else {
                System.out.println(row);
                System.out.println(col);
                return;
            }
        }

        System.out.println("Not found");
    }
}

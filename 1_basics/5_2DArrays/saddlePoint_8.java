// 1. You are given a square matrix of size 'n'. You are given n*n elements of the square matrix. 
// 2. You are required to find the saddle price of the given matrix and print the saddle price. 
// 3. The saddle price is defined as the least price in the row but the maximum price in the column of the matrix.

// Input Format
// A number n
// e11
// e12..
// e21
// e22..
// .. n * n number of elements of array a

// Output Format
// Saddle point of the matrix if available or "Invalid input" if no saddle point is there.

// Constraints
// 1 <= n <= 10^2
// -10^9 <= e11, e12, .. n * m elements <= 10^9

// Sample Input
// 4

// 11 12 13 14 
// 21 22 23 24 
// 31 32 33 34
// 41 42 43 44

// Sample Output
// 41

import java.util.*;

public class saddlePoint_8 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int[][] arr = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                arr[i][j] = scn.nextInt();
            }
        }

        int saddlePoint = saddlePoint(arr);
        
        if(saddlePoint == -1)
            System.out.println("Invalid input");
        else 
            System.out.println(saddlePoint);
    }

    public static int saddlePoint(int[][] arr) {
        int saddlePoint = -1;

        for(int i = 0; i < arr.length; i++) {
            int minColIdx = 0;

            // finds the minimum element in row i
            for(int col = 1; col < arr.length; col++) {
                if(arr[i][col] < arr[i][minColIdx])
                    minColIdx = col;
            }

            boolean flag = true;

            // checks if element at minColIdx is also the max in its column
            for(int row = 0; row < arr.length; row++) {
                if(arr[row][minColIdx] > arr[i][minColIdx]) {
                    flag = false;
                    break;
                }
            }

            if(flag) {
                saddlePoint = arr[i][minColIdx];
                break;
            }
        }

        return saddlePoint;
    }
}

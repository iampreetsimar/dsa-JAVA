// 1. You are given a number n, representing the number of rows and number of columns.
// 2. You are given n*n numbers, representing elements of 2d array a.
// 3. You are required to rotate the matrix by 90 degree clockwise and then display the contents using display function.
// * Note - you are required to do it in-place i.e. no extra space should be used to achieve it .*

// Input Format
// A number n
// e11
// e12..
// e21
// e22..
// .. n * n number of elements

// Output Format
// output is taken care of by display function

// Constraints
// 1 <= n <= 10^2
// -10^9 <= e1, e2, .. n * n elements <= 10^9

// Sample Input
// 4

// 11 12 13 14
// 21 22 23 24
// 31 32 33 34
// 41 42 43 44

// Sample Output
// 41 31 21 11
// 42 32 22 12
// 43 33 23 13
// 44 34 24 14

import java.util.*;

public class rotateBy90Degrees_5 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int[][] arr = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                arr[i][j] = scn.nextInt();
            }
        }

        // rotates arr by 90 degree in clockwise direction INPLACE
        rotateClockwise90Degree(arr);
        display(arr);
    }

    public static void display(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }

            System.out.println();
        }
    }

    public static void rotateClockwise90Degree(int[][] arr) {
        // transpose - change rows into columns
        for(int i = 0; i < arr.length; i++) {
            for(int j = i + 1; j < arr[i].length; j++) {
                // swap 
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }

        // reverse rows
        for(int r = 0; r < arr.length; r++) {
            int i = 0;
            int j = arr[r].length - 1;
            while(i < j) {
                // swap
                int temp = arr[r][i];
                arr[r][i] = arr[r][j];
                arr[r][j] = temp;

                i++; j--;
            }
        }
    }
}

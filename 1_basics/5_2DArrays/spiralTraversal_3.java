// 1. You are given a number n, representing the number of rows.
// 2. You are given a number m, representing the number of columns.
// 3. You are given n*m numbers, representing elements of 2d array a.
// 4. You are required to traverse and print the contents of the 2d array in form of a spiral.

// Constraints
// 1 <= n <= 10^2
// 1 <= m <= 10^2
// -10^9 <= e1, e2, .. n * m elements <= 10^9

// Sample Input
// 3
// 5

// 11 12 13 14 15
// 21 22 23 24 25
// 31 32 33 34 35

// Sample Output
// 11
// 21
// 31
// 32
// 33
// 34
// 35
// 25
// 15
// 14
// 13
// 12
// 22
// 23
// 24

import java.util.*;

public class spiralTraversal_3 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                arr[i][j] = scn.nextInt();
            }
        }

        // spiral traversal
        spiralTraversal(arr);
    }

    public static void spiralTraversal(int[][] arr) {
        int tCount = arr.length * arr[0].length;
        int count = 0;
        int startRow = 0;
        int endRow = arr.length - 1;
        int startCol = 0;
        int endCol = arr[0].length - 1;

        while(count < tCount) {
            // left wall
            for(int i = startRow; i <= endRow && count < tCount; i++) {
                System.out.println(arr[i][startCol]);
                count++;
            }
            startCol++;

            // bottom wall
            for(int j = startCol; j <= endCol && count < tCount; j++) {
                System.out.println(arr[endRow][j]);
                count++;
            }
            endRow--;

            // right wall
            for(int i = endRow; i >= startRow && count < tCount; i--) {
                System.out.println(arr[i][endCol]);
                count++;
            }
            endCol--;

            // top wall
            for(int j = endCol; j >= startCol && count < tCount; j--) {
                System.out.println(arr[startRow][j]);
                count++;
            }
            startRow++;
        }
    }
}

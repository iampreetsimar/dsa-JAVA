// 1. You are given a number n, representing the number of rows.
// 2. You are given a number m, representing the number of columns.
// 3. You are given n*m numbers (1's and 0's), representing elements of 2d array a.
// 4. Consider this array a maze and a player enters from top-left corner in east direction.
// 5. The player moves in the same direction as long as he meets '0'. On seeing a 1, he takes a 90 deg right turn.
// 6. You are required to print the indices in (row, col) format of the point from where you exit the matrix.

// Input Format
// A number n
// A number m
// e11
// e12..
// e21
// e22..
// .. n * m number of elements

// Output Format
// row
// col (of the point of exit)

// Constraints
// 1 <= n <= 10^2
// 1 <= m <= 10^2
// e1, e2, .. n * m elements belongs to the set (0, 1)

// Sample Input
// 4
// 4

// 0 0 1 0
// 1 0 0 0
// 0 0 0 0
// 1 0 1 0

// Sample Output
// 1
// 3

import java.util.*;

public class exitPointOfMatrix_4 {
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

        // exit point of matrix
        exitPoint(arr);
    }

    public static void exitPoint(int[][] arr) {
        int dir = 0;
        int row = 0;
        int col = 0;

        while(true) {
            dir = (dir + arr[row][col]) % 4;

            if(dir == 0) col++;                      // east
            else if(dir == 1) row++;                 // south
            else if(dir == 2) col--;                 // west
            else if(dir == 3) row--;                 // north

            if(row < 0) {       // exit point in top side
                row++;
                break;
            } else if(row == arr.length) {      // exit point in bottom side
                row--;
                break;
            } else if(col < 0) {                // exit point in left side
                col++;
                break;
            } else if(col == arr[0].length) {   // exit point in right side
                col--;
                break;
            }
        }

        // Exit point
        System.out.println(row);
        System.out.println(col);
    }
}

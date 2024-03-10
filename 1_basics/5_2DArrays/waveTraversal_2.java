// 1. You will be given a number n, representing the number of rows.
// 2. You will be given a number m, representing the number of columns.
// 3. You will be given n*m numbers, representing elements of 2d arrays.

// Constraints
// 1 <= n <= 10^2
// 1 <= m <= 10^2
// -10^9 <= e1, e2, .. n * m elements <= 10^9

// Sample Input
// 3
// 4

// 11 12 13 14
// 21 22 23 24
// 31 32 33 34

// Sample Output
// 11
// 21
// 31
// 32
// 22
// 12
// 13
// 23
// 33
// 34
// 24
// 14

import java.util.*;

public class waveTraversal_2 {
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

        // wave traversal
        for(int col = 0; col < arr[0].length; col++) {
            if(col % 2 == 0) {
                // rows in increasing order
                for(int row = 0; row < arr.length; row++) {
                    System.out.println(arr[row][col]);
                }
            } else {
                // rows in decreasing order
                for(int row = arr.length - 1; row >= 0; row--) {
                    System.out.println(arr[row][col]);
                }
            }
        }
    }
}

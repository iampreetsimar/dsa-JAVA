// You are given a n*m matrix where n are the number of rows and m are the number of columns. 
// You are also given n*m numbers representing the elements of the matrix.
// You will be given a ring number 's' representing the ring of the matrix.

// You will be given a number 'r' representing number of rotations in an anti-clockwise manner of the specified ring.
// You are required to rotate the 's'th ring by 'r' rotations and display the rotated matrix.

// Input Format
// A number n
// A number m
// e11
// e12..
// e21
// e22..
// .. n * m number of elements of array a
// A number s
// A number r

// Output Format
// output is handled by display function

// Constraints
// 1 <= n <= 10^2
// 1 <= m <= 10^2
// -10^9 <= e11, e12, .. n * m elements <= 10^9
// 0 < s <= min(n, m) / 2
// -10^9 <= r <= 10^9

// Sample Input
// 5
// 7

// 11 12 13 14 15 16 17
// 21 22 23 24 25 26 27
// 31 32 33 34 35 36 37
// 41 42 43 44 45 46 47
// 51 52 53 54 55 56 57

// 2
// 3

// Sample Output
// 11 12 13 14 15 16 17
// 21 25 26 36 46 45 27
// 31 24 33 34 35 44 37
// 41 23 22 32 42 43 47
// 51 52 53 54 55 56 57

import java.util.*;

public class ringRotate_6 {
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

        int s = scn.nextInt();  // shell
        int r = scn.nextInt();  // rotations

        // rotates shell s by rotations r
        ringRotate(arr, s, r);
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

    public static void ringRotate(int[][] arr, int shell, int rotation) {
        int[] arrOneD = fillOneDArrayFromShell(arr, shell);
        rotateOneDArray(arrOneD, rotation);
        fillShellFromOneDArray(arr, arrOneD, shell);
    }

    public static void rotateOneDArray(int[] arr, int r) {
        // rebalance rotations
        r = r % arr.length;
        if(r < 0)   r += arr.length;

        reverseArray(arr, 0, arr.length - r - 1);
        reverseArray(arr, arr.length - r, arr.length - 1);
        reverseArray(arr, 0, arr.length - 1);
    }

    public static void reverseArray(int[] arr, int startIdx, int endIdx) {
        while(startIdx < endIdx) {
            int temp = arr[startIdx];
            arr[startIdx] = arr[endIdx];
            arr[endIdx] = temp;

            startIdx++;
            endIdx--;
        }
    }

    public static int[] fillOneDArrayFromShell(int[][] arr, int s) {
        int minRow = s - 1;
        int minCol = s - 1;
        int maxRow = arr.length - s;
        int maxCol = arr[0].length - s;

        int totalCount = 2 * (maxRow - minRow + 1) + 2 * (maxCol - minCol + 1) - 4;
        int[] arrOneD = new int[totalCount];
        int idx = 0;

        // left wall
        for(int i = minRow; i <= maxRow; i++) {
            arrOneD[idx] = arr[i][minCol];
            idx++;
        }
        minCol++;

        // bottom wall
        for(int j = minCol; j <= maxCol; j++) {
            arrOneD[idx] = arr[maxRow][j];
            idx++;
        }
        maxRow--;

        // right wall
        for(int i = maxRow; i >= minRow; i--) {
            arrOneD[idx] = arr[i][maxCol];
            idx++;
        }
        maxCol--;

        // top wall
        for(int j = maxCol; j >= minCol; j--) {
            arrOneD[idx] = arr[minRow][j];
            idx++;
        }

        return arrOneD;
    }

    public static void fillShellFromOneDArray(int[][] arr, int[] arrOneD, int s) {
        int minRow = s - 1;
        int minCol = s - 1;
        int maxRow = arr.length - s;
        int maxCol = arr[0].length - s;

        int idx = 0;

        // left wall
        for(int i = minRow; i <= maxRow; i++) {
            arr[i][minCol] = arrOneD[idx];
            idx++;
        }
        minCol++;

        // bottom wall
        for(int j = minCol; j <= maxCol; j++) {
            arr[maxRow][j] = arrOneD[idx];
            idx++;
        }
        maxRow--;

        // right wall
        for(int i = maxRow; i >= minRow; i--) {
            arr[i][maxCol] = arrOneD[idx];
            idx++;
        }
        maxCol--;

        // top wall
        for(int j = maxCol; j >= minCol; j--) {
             arr[minRow][j] = arrOneD[idx];
            idx++;
        }
    }
}

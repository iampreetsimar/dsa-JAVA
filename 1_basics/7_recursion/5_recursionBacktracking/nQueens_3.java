// 1. You are given a number n, the size of a chess board.
// 2. You are required to place n number of queens in the n * n cells of board such that no queen can kill another.
// Note - Queens kill at distance in all 8 directions
// 3. Complete the body of printNQueens function - without changing signature - to calculate and 
// print all safe configurations of n-queens. Use sample input and output to get more idea.

// Note -> The online judge can't force you to write the function recursively but that is what the spirit of question is. 
// Write recursive and not iterative logic. The purpose of the question is to aid learning recursion and not test you.

// Input Format
// A number n

// Output Format
// Safe configurations of queens as suggested in sample output

// Constraints
// 1 <= n <= 10

// Sample Input
// 4

// Sample Output
// 0-1, 1-3, 2-0, 3-2, .
// 0-2, 1-0, 2-3, 3-1, .

import java.util.*;

public class nQueens_3 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[][] arr = new int[n][n];

        printNQueens(arr, "", 0);
    }

    // qsf - queen so far placed at
    public static void printNQueens(int[][] chess, String qsf, int row) {
        if(row == chess.length) {
            // BASE CASE
            System.out.println(qsf + ".");
            return;
        }

        for(int col = 0; col < chess.length; col++) {
            // place queen only if its safe to place it
            if(canQueenBePlaced(chess, row, col)) {
                // queen placed
                chess[row][col] = 1;

                // cell added in config and proceed to next row
                printNQueens(chess, qsf + row + "-" + col + ", ", row + 1);

                // unplace queen while backtracking
                chess[row][col] = 0;
            }
        }
    }

    public static boolean canQueenBePlaced(int[][] chess, int row, int col) {
        // check vertically upwards
        for(int i = row - 1; i >= 0; i--) {
            if(chess[i][col] == 1)
                return false;
        }

        // check left upper diagonal
        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if(chess[i][j] == 1)
                return false;
        }

        // check right upper diagonal
        for(int i = row - 1, j = col + 1; i >= 0 && j < chess.length; i--, j++) {
            if(chess[i][j] == 1)
                return false;
        }

        // all 3 directions checked - safe to place queen in current row,col
        return true;
    }
}

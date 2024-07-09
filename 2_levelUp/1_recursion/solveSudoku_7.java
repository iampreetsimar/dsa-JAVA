// Given a partially filled (9 * 9) 2D array which represents an incomplete sudoku state.
// Assign digits 1 - 9 to the empty cells based on following rules:
//     RULE 1: digits 1 - 9 must occur exactly once in each row
//     RULE 2: digits 1 - 9 must occur exactly once in each col
//     RULE 3: digits 1 - 9 must occur exactly once in each 3*3 subarray of given 2D array

// Assume the give sudoku puzzle has a single unique solution.

// INPUT
// 3 0 6 5 0 8 4 0 0
// 5 2 0 0 0 0 0 0 0
// 0 8 7 0 0 0 0 3 1
// 0 0 3 0 1 0 0 8 0
// 9 0 0 8 6 3 0 0 5
// 0 5 0 0 9 0 6 0 0
// 1 3 0 0 0 0 2 5 0
// 0 0 0 0 0 0 0 7 4
// 0 0 5 2 0 6 3 0 0

// OUTPUT
// 3 1 6 5 7 8 4 9 2 
// 5 2 9 1 3 4 7 6 8 
// 4 8 7 6 2 9 5 3 1 
// 2 6 3 4 1 5 9 8 7 
// 9 7 4 8 6 3 1 2 5 
// 8 5 1 7 9 2 6 4 3 
// 1 3 8 9 4 7 2 5 6 
// 6 9 2 3 5 1 8 7 4 
// 7 4 5 2 8 6 3 1 9

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class solveSudoku_7 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[9][9];

        for(int i = 0; i < board.length; i++) {
            String[] row = br.readLine().split(" ");
            for(int j = 0; j < board[0].length; j++) {
                board[i][j] = Integer.parseInt(row[j]);
            }
        }

        solve(board, 0, 0);
    }

    public static void displayBoard(int[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void solve(int[][] board, int row, int col) {
        if(row == board.length) {   // BASE CASE
            System.out.println("Solved Sudoku: ");
            displayBoard(board);
            return;
        }

        int nextRow, nextCol;

        if(col == board[0].length - 1) {    // if last col: move to next row
            nextRow = row + 1; nextCol = 0;
        } else {    // otherwise move to next col in same row
            nextRow = row; nextCol = col + 1;
        }

        if(board[row][col] != 0) {  // if non-zero # already on board: move to next 
            solve(board, nextRow, nextCol);
            return;
        }

        // Otherwise cell with 0 found -> check if any digit from 1-9 can be placed
        for(int digit = 1; digit < 10; digit++) {
            if(canDigitBePlaced(board, row, col, digit)) {
                board[row][col] = digit;    // mark curr cell with digit
                solve(board, nextRow, nextCol); // move to next
                board[row][col] = 0;    // unmark curr cell while backtracking
            }
        }
    }

    public static boolean canDigitBePlaced(int[][] board, int row, int col, int digit) {
        // check row and col
        for(int k = 0; k < board.length; k++) {
            if(board[row][k] == digit || board[k][col] == digit)
                return false;   // if digit is already placed in either row or col
        }

        // check 3*3 subarray
        int subRowInit = row / 3 * 3;   // gives initial row of subarray
        int subColInit = col / 3 * 3;   // gives initial col of subarray

        for(int  i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[subRowInit + i][subColInit + j] == digit)
                    return false;   // if digit in any cell of subarray
            }
        }

        // digit not in row/col/subarray -> can be placed
        return true;    
    }
}

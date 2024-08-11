// Given N: N * N chess board, calculate and print the permutations in which N queens can be placed
// on the N * N chess board. 

// NOTE: -> Queens are distinct ,i.e, (q1, q2, q3, ...). 
//       -> No queen should be able to kill another, thus, need to check valid positions.

// INPUT
// 4

// OUTPUT
// -q1--
// ---q2 
// q3---
// --q4-

// -q2--
// ---q1 
// q3---
// --q4-

// .... 46 other permutations

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class nQueensPerm_2D_As_1D_QueenChooses_36 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        solve(0, n, new int[n][n]);
    }

    // similar to Queens Permutations I - queen chooses(use cell # for 1D loop)
    // a cell becomes valid options: if it is empty and it is valid pos(no queen can kill each other) for queen
    public static void solve(int qpsf, int tq, int[][] board) {
        if(qpsf == tq) {    // BASE CASE
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[0].length; j++) {
                    if(board[i][j] != 0) 
                        System.out.print("q" + board[i][j] + " ");
                    else
                        System.out.print("-" + " ");
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for(int i = 0; i < board.length * board.length; i++) {  // loop over cell # in 1D - extrapolate (row, col)
            int row = i / board.length;
            int col = i % board.length;

            if(board[row][col] == 0 && canQueenBePlaced(board, row, col)) { // check if cell is empty && valid
                board[row][col] = qpsf + 1; // place queen
                solve(qpsf + 1, tq, board);
                board[row][col] = 0;    // unplace queen while backtracking
            }
        }
    }

    // for a cell to be valid: check in all 8 directions as cell could be before last filled cell
    public static boolean canQueenBePlaced(int[][] board, int row, int col) {
        for(int i = 0; i < board.length; i++) {     // same col
            if(board[i][col] != 0) return false;
        }

        for(int j = 0; j < board.length; j++) {     // same row
            if(board[row][j] != 0) return false;
        }

        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {     // upper left diagonal
            if(board[i][j] != 0) return false;
        }

        for(int i = row + 1, j = col + 1; i < board.length && j < board.length; i++, j++) { // lower right diagonal
            if(board[i][j] != 0) return false;
        }

        for(int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {   // upper right diagonal
            if(board[i][j] != 0) return false;
        }

        for(int i = row + 1, j = col - 1; i < board.length && j >= 0; i++, j--) {   // lower left diagonal
            if(board[i][j] != 0) return false;
        }

        return true;    // no queen placed in cells where curr queen can kill them, curr queen can be placed
    }
}

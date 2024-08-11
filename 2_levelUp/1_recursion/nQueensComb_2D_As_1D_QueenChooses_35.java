// Given N: N * N chess board, calculate and print the combinations in which N queens can be placed
// on the N * N chess board. 

// NOTE: -> Queens are similar ,i.e, (q, q, q, ...). 
//       -> No queen should be able to kill another, thus, need to check valid positions.

// INPUT
// 4

// OUTPUT
// -q--
// ---q 
// q---
// --q-

// --q-
// q---
// ---q
// -q--

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class nQueensComb_2D_As_1D_QueenChooses_35 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        solve(0, n, new boolean[n][n], -1);
    }

    // similar to Queens Combinations III - 2D as 1D but additional check so that queens cannot kill each other
    public static void solve(int qpsf, int tq, boolean[][] board, int lastFilledCell) {
        if(qpsf == tq) {        // BASE CASE: n queen placed
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[0].length; j++) {
                    if(board[i][j]) 
                        System.out.print("q" + " ");
                    else
                        System.out.print("-" + " ");
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for(int i = lastFilledCell + 1; i < board.length * board.length; i++) {     // options are cells after lastFilledCell - always empty
            int row = i / board.length;
            int col = i % board.length;

            if(canQueenBePlaced(board, row, col)) {     // valid options are only those where queen cannot kill annother queen
                board[row][col] = true;
                solve(qpsf + 1, tq, board, (row + 1) * board.length - 1);   // since queen placed in row, next queen can only be placed in next row
                board[row][col] = false;
            }
        }
    }

    // since options are always empty, only need to check if queen is already placed before in 3 directions - up, left upper, right upper
    public static boolean canQueenBePlaced(boolean[][] board, int row, int col) {
        for(int i = row - 1; i >= 0; i--) {     // cells in same col above
            if(board[i][col]) return false;
        }

        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {     // cells in same left upper diagonal
            if(board[i][j]) return false;
        }

        for(int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {   // cells in same right upper diagonal
            if(board[i][j]) return false;
        }

        return true;    // no queen placed in cells where curr queen can kill them, curr queen can be placed
    }
}

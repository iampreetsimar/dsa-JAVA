// Given N, the size of a chess board, place N queens in N * N cells of the board such that
// no queen can kill each other.

// NOTE: Queens kill in all 8 directions.

// Print all safe configurations.

// INPUT
// 4

// OUTPUT
// 0-1, 1-3, 2-0, 3-2, .
// 0-2, 1-0, 2-3, 3-1, .

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class nQueens_BranchBound_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        solve(new boolean[n], new boolean[2 * n - 1], new boolean[2 * n - 1], 0, "");
    }

    // use BRANCH n BOUND algorithm: bound recursive branches that were being made in normal approach
    // On placing a queen at a cell, following are blocked for future queens:
    //      -> same col, same diagonal and same reverse diagonal
    public static void solve(boolean[] cols, boolean[] diag,
                             boolean[] revDiag, int row, String psf) {
        if(row == cols.length) {
            System.out.println(psf + ".");
            return;
        }

        for(int col = 0; col < cols.length; col++) {
            // queen can be placed if same cols, diag and revDiag for curr cell(row, col) is unvisited
            if(cols[col] == false && diag[row + col] == false
               && revDiag[row - col + cols.length - 1] == false) {  // curr cell is safe to place queen
                // mark curr col, diag and revDiag to block them for next queen placement
                cols[col] = true;
                diag[row + col] = true;
                revDiag[row - col + cols.length - 1] = true;

                solve(cols, diag, revDiag, row + 1, psf + row + "-" + col + ", ");  // move to next row

                // unmark curr col, diag and revDiag while backtracking
                cols[col] = false;
                diag[row + col] = false;
                revDiag[row - col + cols.length - 1] = false;
            }
        }
    }
}

// Given N: N * N chess board, calculate and print the combinations in which N queens can be placed
// on the N * N chess board. 

// NOTE: -> Queens are similar ,i.e, (q, q, q, ...). No need to check valid positions here. Just place queen. 

// INPUT
// 2

// OUTPUT
// q q 
// - -

// q - 
// q - 

// q - 
// - q 

// - q 
// q - 

// - q 
// - q 

// - - 
// q q

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class queensComb_II_2D_As_2D_QueenChooses_33 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        solve(0, n, new boolean[n][n], 0, -1);
    }

    // similar to COMBINATIONS - II - ITEM CHOOSES(reduces arrangements from PERMUTATIONS - I )
    // use level-options: -> queen placed so far on levels | -> valid options are the cells after the last filled cell by a queen
    // cells after the last filled one will always be empty 
    // using lastFilledRow, lastFilledCol position generates only single arrangment/selection
    public static void solve(int qpsf, int tq, boolean[][] board, int lastFilledRow, int lastFilledCol) {
        if(qpsf == tq) {    // BASE CASE: n queens placed
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

        for(int j = lastFilledCol + 1; j < board[0].length; j++) {  // valid options are cells after last filled cell in same row
            board[lastFilledRow][j] = true; // place queen in cell
            solve(qpsf + 1, tq, board, lastFilledRow, j);   // lastFilledRow remains same, j becomes new lastFilledCol
            board[lastFilledRow][j] = false; // unplace queen while backtracking
        }

        for(int i = lastFilledRow + 1; i < board.length; i++) { // valid options are all the cells from next row onwards
            for(int j = 0; j < board[0].length; j++) {
                board[i][j] = true; // place queen in cell
                solve(qpsf + 1, tq, board, i, j);   // i becomes new lastFilledRow, j becomes new lastFilledCol
                board[i][j] = false;    // unplace queen while backtracking
            }
        }
    }
}
